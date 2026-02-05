package com.klid.api.logs.connect.controller;

import com.klid.api.logs.connect.dto.PeriodSearchReqDTO;
import com.klid.api.logs.connect.dto.UserConnectLogPeriodResDTO;
import com.klid.api.logs.connect.service.UserConnectLogPeriodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs/user-connect/period")
public class UserConnectLogPeriodController {

    private final UserConnectLogPeriodService userConnectLogPeriodService;

    @PostMapping("/data")
    public ResponseEntity<?> getData(@RequestBody final PeriodSearchReqDTO searchReqDTO) {
        log.info("{}", searchReqDTO);

        // 날짜 범위 검증 (1년 이하)
        try {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            final LocalDate startDate = LocalDate.parse(searchReqDTO.getStartYmd(), formatter);
            final LocalDate endDate = LocalDate.parse(searchReqDTO.getEndYmd(), formatter);
            final LocalDate fiveYearsAgo = LocalDate.now().minusYears(5);

            final long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

            // 5년 전 데이터 조회 제한
            if (startDate.isBefore(fiveYearsAgo) || endDate.isBefore(fiveYearsAgo)) {
                return ResponseEntity.badRequest()
                        .body("5년 이전 데이터는 조회할 수 없습니다.");
            }

            if (daysBetween > 365) {
                return ResponseEntity.badRequest()
                        .body("조회 기간은 1년을 초과할 수 없습니다.");
            }

            if (daysBetween < 0) {
                return ResponseEntity.badRequest()
                        .body("시작일은 종료일보다 늦을 수 없습니다.");
            }

        } catch (Exception e) {
            log.error("날짜 형식 오류: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body("날짜 형식이 올바르지 않습니다.");
        }

        final List<UserConnectLogPeriodResDTO> data =
                userConnectLogPeriodService.getData(searchReqDTO.getSystemType(), searchReqDTO.getStartYmd(), searchReqDTO.getEndYmd());
        return ResponseEntity.ok(data);
    }
}

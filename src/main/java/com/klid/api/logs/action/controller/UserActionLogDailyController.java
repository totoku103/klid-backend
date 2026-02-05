package com.klid.api.logs.action.controller;

import com.klid.api.logs.action.dto.DailySearchReqDTO;
import com.klid.api.logs.action.dto.UserActionLogDailyResDTO;
import com.klid.api.logs.action.service.UserActionLogDailyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs/user-action/daily")
public class UserActionLogDailyController {

    private final UserActionLogDailyService userActionLogDailyService;

    @PostMapping("/data")
    public ResponseEntity<?> getData(@RequestBody final DailySearchReqDTO searchReqDTO) {
        log.info("{}", searchReqDTO);

        // 날짜 검증 (5년 전 데이터 조회 제한)
        try {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            final LocalDate searchDate = LocalDate.parse(searchReqDTO.getYyyyMMdd(), formatter);
            final LocalDate fiveYearsAgo = LocalDate.now().minusYears(5);

            if (searchDate.isBefore(fiveYearsAgo)) {
                return ResponseEntity.badRequest()
                        .body("5년 이전 데이터는 조회할 수 없습니다.");
            }
        } catch (Exception e) {
            log.error("날짜 형식 오류: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body("날짜 형식이 올바르지 않습니다.");
        }

        final List<UserActionLogDailyResDTO> data =
                userActionLogDailyService.getData(searchReqDTO.getYyyyMMdd(), searchReqDTO.getSystemType());
        return ResponseEntity.ok(data);
    }
}

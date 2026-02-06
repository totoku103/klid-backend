package com.klid.api.logs.action.controller;

import com.klid.api.logs.action.dto.SummaryCounterResDTO;
import com.klid.api.logs.action.dto.UserActionGridReqDTO;
import com.klid.api.logs.action.dto.UserActionGridResDTO;
import com.klid.api.logs.action.service.UserActionLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs/user-action")
public class UserActionLogController {

    private final UserActionLogService userActionLogService;

    @GetMapping("/summary")
    public ResponseEntity<SummaryCounterResDTO> getSummaryCounter() {
        log.info("call getSummaryCounter");
        final SummaryCounterResDTO summaryInfo = userActionLogService.getSummaryInfo();
        return ResponseEntity.ok(summaryInfo);
    }

    @GetMapping("/grid")
    public ResponseEntity<?> getActionGridList(final UserActionGridReqDTO reqMap) {
        // 날짜 범위 검증 (1년 이하, 5년 전 데이터 제한)
        try {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            final LocalDate startDate = LocalDate.parse(reqMap.getDate1(), formatter);
            final LocalDate endDate = LocalDate.parse(reqMap.getDate2(), formatter);
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

        final List<UserActionGridResDTO> gridList = userActionLogService.getGridList(reqMap);
        return ResponseEntity.ok(gridList);
    }
}

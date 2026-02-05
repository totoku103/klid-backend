package com.klid.api.report.daily.controller;

import com.klid.api.report.daily.service.ReportDailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 일일 보고서 REST API Controller
 *
 * - 일일 통계 조회
 * - 일일 보고서 HWP 다운로드
 */
@RestController("apiReportDailyController")
@RequiredArgsConstructor
@RequestMapping("/api/report/daily")
public class ReportDailyController {

    private final ReportDailyService service;

    /**
     * 일일 통계 조회
     */
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getDayStatistics(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getDayStatistics(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 일일 보고서 HWP 다운로드
     *
     * Note: HWP 파일 생성 로직이 복잡하여 향후 마이그레이션 필요
     */
    @GetMapping("/download")
    public ResponseEntity<Map<String, Object>> downloadDailyReport(
            @RequestParam final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.downloadDailyReport(params, response);
        return ResponseEntity.ok(result);
    }
}

package com.klid.api.report.incident.controller;

import com.klid.api.report.incident.service.ReportInciDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 사고 상세 현황 보고서 REST API Controller
 *
 * - 사고 상세 목록 조회
 * - 일일 보고서 HWP 다운로드
 */
@RestController("apiReportInciDetailController")
@RequiredArgsConstructor
@RequestMapping("/api/report/incident/detail")
public class ReportInciDetailController {

    private final ReportInciDetailService service;

    /**
     * 사고 상세 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getDetailList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getDetailList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 일일 보고서 HWP 다운로드
     */
    @GetMapping("/export/daily")
    public ResponseEntity<Map<String, Object>> exportDailyReport(
            @RequestParam final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.exportDailyReport(params, response);
        return ResponseEntity.ok(result);
    }
}

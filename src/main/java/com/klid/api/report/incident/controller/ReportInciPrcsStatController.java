package com.klid.api.report.incident.controller;

import com.klid.api.report.incident.service.ReportInciPrcsStatService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 사고 처리상태별 보고서 REST API Controller
 *
 * - 처리상태별 목록 조회
 * - 차트 이미지 저장
 * - 처리상태별 보고서 HWP 출력
 */
@RestController("apiReportInciPrcsStatController")
@RequiredArgsConstructor
@RequestMapping("/api/report/incident/process-status")
public class ReportInciPrcsStatController {

    private final ReportInciPrcsStatService service;

    /**
     * 처리상태별 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getProcessStatusList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getProcessStatusList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 하이차트 이미지 저장
     */
    @PostMapping("/save-chart-image")
    public ResponseEntity<Map<String, Object>> saveHighChartImage(
            @RequestBody final Map<String, Object> params,
            final HttpServletRequest request,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.saveHighChartImage(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 처리상태별 보고서 HWP 출력
     */
    @PostMapping("/export")
    public ResponseEntity<Map<String, Object>> exportReport(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.exportReport(params, response);
        return ResponseEntity.ok(result);
    }
}

package com.klid.api.report.incident.controller;

import com.klid.api.report.incident.service.ReportInciTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 사고 유형별 보고서 REST API Controller
 *
 * - 사고유형별 목록 조회
 * - 차트 이미지 저장
 * - 사고유형별 보고서 HWP 출력
 */
@RestController("apiReportInciTypeController")
@RequiredArgsConstructor
@RequestMapping("/api/report/incident/type")
public class ReportInciTypeController {

    private final ReportInciTypeService service;

    /**
     * 사고유형별 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getTypeList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getTypeList(params);
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
     * 사고유형별 보고서 HWP 출력
     */
    @PostMapping("/export")
    public ResponseEntity<Map<String, Object>> exportReport(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.exportReport(params, response);
        return ResponseEntity.ok(result);
    }
}

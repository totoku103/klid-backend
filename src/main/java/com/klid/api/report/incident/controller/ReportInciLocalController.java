package com.klid.api.report.incident.controller;

import com.klid.api.report.incident.service.ReportInciLocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 사고 지역별 보고서 REST API Controller
 *
 * - 지역별(시도) 목록 조회
 * - 시군구별 목록 조회
 * - 차트 이미지 저장
 * - 지역별 보고서 HWP 출력
 */
@RestController("apiReportInciLocalController")
@RequiredArgsConstructor
@RequestMapping("/api/report/incident/local")
public class ReportInciLocalController {

    private final ReportInciLocalService service;

    /**
     * 지역별(시도) 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getLocalList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getLocalList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 시군구별 목록 조회
     */
    @GetMapping("/sido-list")
    public ResponseEntity<Map<String, Object>> getSidoList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getSidoList(params);
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
     * 지역별 보고서 HWP 출력
     */
    @PostMapping("/export")
    public ResponseEntity<Map<String, Object>> exportReport(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.exportReport(params, response);
        return ResponseEntity.ok(result);
    }
}

package com.klid.api.report.daily.controller;

import com.klid.api.report.daily.service.ReportDailyInciStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 일일 사고 현황 보고서 REST API Controller
 *
 * - 일일 사고 목록 조회
 * - 일일 누적 목록 조회
 * - 차트 이미지 저장
 * - HWP 보고서 생성 및 다운로드
 */
@RestController("apiReportDailyInciStateController")
@RequiredArgsConstructor
@RequestMapping("/api/report/daily/incident-state")
public class ReportDailyInciStateController {

    private final ReportDailyInciStateService service;

    /**
     * 일일 사고 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getDailyList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getDailyList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 일일 누적 목록 조회
     */
    @GetMapping("/total-list")
    public ResponseEntity<Map<String, Object>> getDailyTotalList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getDailyTotalList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 하이차트 이미지 저장
     *
     * Base64 인코딩된 차트 이미지를 서버에 저장
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
     * 일일 사고 현황 보고서 HWP 다운로드 생성
     */
    @PostMapping("/download")
    public ResponseEntity<Map<String, Object>> makeReportDownload(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.makeReportDownload(params, response);
        return ResponseEntity.ok(result);
    }
}

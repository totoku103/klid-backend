package com.klid.api.report.daily.controller;

import com.klid.api.report.daily.service.ReportDailyStateService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 일일 상태 보고서 REST API Controller
 *
 * - 근무자 순번 목록 조회
 * - 일일 목록 조회
 * - 일일 누적 목록 조회
 * - 사고 유형별 목록 조회
 * - 탐지 목록 조회
 * - 일일 상태 보고서 HWP 생성
 */
@RestController("apiReportDailyStateController")
@RequiredArgsConstructor
@RequestMapping("/api/report/daily/state")
public class ReportDailyStateController {

    private final ReportDailyStateService service;

    /**
     * 근무자 순번 목록 조회
     */
    @GetMapping("/rotation-list")
    public ResponseEntity<Map<String, Object>> getRotationList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getRotationList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 일일 목록 조회
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
     * 사고 유형별 목록 조회
     */
    @GetMapping("/type-accident-list")
    public ResponseEntity<Map<String, Object>> getTypeAccidentList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getTypeAccidentList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 탐지 목록 조회
     */
    @GetMapping("/detection-list")
    public ResponseEntity<Map<String, Object>> getDetectionList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getDetectionList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 일일 상태 보고서 HWP 다운로드 생성
     */
    @PostMapping("/download")
    public ResponseEntity<Map<String, Object>> makeReportDownload(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.makeReportDownload(params, response);
        return ResponseEntity.ok(result);
    }
}

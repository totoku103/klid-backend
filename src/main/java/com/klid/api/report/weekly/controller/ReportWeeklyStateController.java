package com.klid.api.report.weekly.controller;

import com.klid.api.report.weekly.service.ReportWeeklyStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 주간 상태 보고서 REST API Controller
 *
 * - 근무자 순번 목록 조회
 * - 주간 목록 조회
 * - 주간 이전 목록 조회
 * - 주간 누적 목록 조회
 * - 사고 유형별 목록 조회
 * - 탐지 목록 조회
 * - 주간 보고서 HWP 다운로드
 */
@RestController("apiReportWeeklyStateController")
@RequiredArgsConstructor
@RequestMapping("/api/report/weekly/state")
public class ReportWeeklyStateController {

    private final ReportWeeklyStateService service;

    /**
     * 근무자 순번 목록 조회
     */
    @GetMapping("/rotation-list")
    public ResponseEntity<Map<String, Object>> getRotationList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getRotationList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 주간 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getWeeklyList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getWeeklyList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 주간 이전 목록 조회
     */
    @GetMapping("/list-before")
    public ResponseEntity<Map<String, Object>> getWeeklyListBefore(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getWeeklyListBefore(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 주간 누적 목록 조회
     */
    @GetMapping("/total-list")
    public ResponseEntity<Map<String, Object>> getWeeklyTotalList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getWeeklyTotalList(params);
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
     * 주간 보고서 HWP 다운로드 생성
     */
    @PostMapping("/download")
    public ResponseEntity<Map<String, Object>> makeReportDownload(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.makeReportDownload(params, response);
        return ResponseEntity.ok(result);
    }
}

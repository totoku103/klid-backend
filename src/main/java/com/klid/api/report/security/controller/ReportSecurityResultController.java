package com.klid.api.report.security.controller;

import com.klid.api.report.security.service.ReportSecurityResultService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 보안 결과 보고서 REST API Controller
 *
 * - 보안 결과 총계 조회
 * - 보안 결과 목록 조회
 * - 보안 결과 제외 목록 조회
 * - 보안 보고서 HWP 다운로드
 */
@RestController("apiReportSecurityResultController")
@RequiredArgsConstructor
@RequestMapping("/api/report/security/result")
public class ReportSecurityResultController {

    private final ReportSecurityResultService service;

    /**
     * 보안 결과 총계 조회
     */
    @GetMapping("/total")
    public ResponseEntity<Map<String, Object>> getResultTotal(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getResultTotal(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 보안 결과 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getResultList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getResultList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 보안 결과 제외 목록 조회
     */
    @GetMapping("/except-list")
    public ResponseEntity<Map<String, Object>> getResultExceptList(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getResultExceptList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 보안 보고서 HWP 다운로드 생성
     */
    @PostMapping("/download")
    public ResponseEntity<Map<String, Object>> makeReportDownload(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.makeReportDownload(params, response);
        return ResponseEntity.ok(result);
    }
}

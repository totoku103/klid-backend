package com.klid.api.report.collection.controller;

import com.klid.api.report.collection.service.ReportCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 보고서 수집 현황 REST API Controller
 *
 * - 보안 해킹 관리 대장 조회
 * - 보안 취약점 관리 대장 조회
 * - 공지사항 현황 조회
 * - 보안자료실 현황 조회
 * - 사고 처리중 현황 조회
 * - 각종 보고서 엑셀 다운로드
 */
@RestController("apiReportCollectionController")
@RequiredArgsConstructor
@RequestMapping("/api/report/collection")
public class ReportCollectionController {

    private final ReportCollectionService service;

    /**
     * 보안 해킹 관리 대장 상세 조회
     */
    @GetMapping("/security-hacking-detail")
    public ResponseEntity<Map<String, Object>> getSecurityHackingDetail(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getSecurityHackingDetail(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 보안자료실 목록 상세 조회
     */
    @GetMapping("/security-list-detail")
    public ResponseEntity<Map<String, Object>> getSecurityListDetail(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getSecurityListDetail(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 공지사항 목록 상세 조회
     */
    @GetMapping("/notice-list-detail")
    public ResponseEntity<Map<String, Object>> getNoticeListDetail(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getNoticeListDetail(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 보안 취약점 관리 대장 상세 조회
     */
    @GetMapping("/security-vulnerability-detail")
    public ResponseEntity<Map<String, Object>> getSecurityVulnerabilityDetail(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getSecurityVulnerabilityDetail(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 사고 처리중 현황 상세 조회
     */
    @GetMapping("/incident-detail")
    public ResponseEntity<Map<String, Object>> getIncidentDetail(@RequestParam final Map<String, Object> params) {
        final Map<String, Object> result = service.getIncidentDetail(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 공지사항 현황 엑셀 출력
     */
    @PostMapping("/export/notice-list")
    public ResponseEntity<Map<String, Object>> exportNoticeList(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.exportNoticeList(params, response);
        return ResponseEntity.ok(result);
    }

    /**
     * 보안자료실 현황 엑셀 출력
     */
    @PostMapping("/export/security-list")
    public ResponseEntity<Map<String, Object>> exportSecurityList(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.exportSecurityList(params, response);
        return ResponseEntity.ok(result);
    }

    /**
     * 해킹관리대장 엑셀 출력
     */
    @PostMapping("/export/security-hacking")
    public ResponseEntity<Map<String, Object>> exportSecurityHacking(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.exportSecurityHacking(params, response);
        return ResponseEntity.ok(result);
    }

    /**
     * 취약점관리대장 엑셀 출력
     */
    @PostMapping("/export/security-vulnerability")
    public ResponseEntity<Map<String, Object>> exportSecurityVulnerability(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.exportSecurityVulnerability(params, response);
        return ResponseEntity.ok(result);
    }

    /**
     * 처리중현황 엑셀 출력
     */
    @PostMapping("/export/incident-detail")
    public ResponseEntity<Map<String, Object>> exportIncidentDetail(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.exportIncidentDetail(params, response);
        return ResponseEntity.ok(result);
    }

    /**
     * 일일운영현황 엑셀 출력
     */
    @PostMapping("/export/ctrs-daily")
    public ResponseEntity<Map<String, Object>> exportCTRSDaily(
            @RequestBody final Map<String, Object> params,
            final HttpServletResponse response) {
        final Map<String, Object> result = service.exportCTRSDaily(params, response);
        return ResponseEntity.ok(result);
    }
}

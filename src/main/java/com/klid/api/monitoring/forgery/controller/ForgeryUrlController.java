package com.klid.api.monitoring.forgery.controller;

import com.klid.api.monitoring.forgery.dto.ForgeryUrlDTO;
import com.klid.api.monitoring.forgery.service.ForgeryUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 위변조 URL Controller
 * - 위변조 URL 목록 조회
 * - 위변조 URL 이력 조회
 * - 메인 홈페이지 모니터링
 */
@RestController("apiForgeryUrlController")
@RequestMapping("/api/monitoring/forgery-url")
@RequiredArgsConstructor
public class ForgeryUrlController {

    private final ForgeryUrlService forgeryUrlService;

    /**
     * 위변조 URL 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getForgeryUrl(@RequestParam Map<String, Object> params) {
        final List<Map<String, Object>> result = forgeryUrlService.getForgeryUrl(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 위변조 URL 이력 목록 조회
     */
    @GetMapping("/histories")
    public ResponseEntity<List<Map<String, Object>>> getForgeryUrlHist(@RequestParam Map<String, Object> params) {
        final List<Map<String, Object>> result = forgeryUrlService.getForgeryUrlHist(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 메인 홈페이지 모니터링 조회
     */
    @PostMapping("/main-monitoring")
    public ResponseEntity<List<ForgeryUrlDTO>> getMainForgeryHm(@RequestParam Map<String, Object> params) {
        final List<ForgeryUrlDTO> result = forgeryUrlService.getMainForgeryHm(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 메인 홈페이지 모니터링 수치 통계
     */
    @PostMapping("/main-monitoring-statistics")
    public ResponseEntity<Map<String, Object>> getMainForgeryCnt(@RequestParam Map<String, Object> params) {
        final Map<String, Object> result = forgeryUrlService.getMainForgeryCnt(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 기관명으로 조회
     */
    @PostMapping("/by-institution")
    public ResponseEntity<ForgeryUrlDTO> getByInstNm(@RequestBody Map<String, Object> params) {
        final ForgeryUrlDTO result = forgeryUrlService.getByInstNm(params);
        return ResponseEntity.ok(result);
    }
}

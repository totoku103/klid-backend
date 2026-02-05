package com.klid.api.monitoring.health.controller;

import com.klid.api.monitoring.health.dto.HealthCheckUrlDTO;
import com.klid.api.monitoring.health.service.HealthCheckUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 헬스체크 URL Controller
 * - 헬스체크 URL CRUD
 * - 집중관리 설정
 * - 장애이력 조회
 * - 통계 조회
 * - Excel Import/Export
 */
@RestController("apiHealthCheckUrlController")
@RequestMapping("/api/monitoring/health-check-url")
@RequiredArgsConstructor
public class HealthCheckUrlController {

    private final HealthCheckUrlService healthCheckUrlService;

    /**
     * 헬스체크 URL 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<HealthCheckUrlDTO>> getHealthCheckUrl(@RequestParam Map<String, Object> params) {
        final List<HealthCheckUrlDTO> result = healthCheckUrlService.getHealthCheckUrl(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 헬스체크 URL 등록
     */
    @PostMapping
    public ResponseEntity<Integer> addHealthCheckUrl(@RequestBody Map<String, Object> params) {
        final int seqNo = healthCheckUrlService.addHealthCheckUrl(params);
        return ResponseEntity.status(HttpStatus.CREATED).body(seqNo);
    }

    /**
     * 헬스체크 URL 수정
     */
    @PutMapping
    public ResponseEntity<Void> editHealthCheckUrl(@RequestBody Map<String, Object> params) {
        healthCheckUrlService.editHealthCheckUrl(params);
        return ResponseEntity.ok().build();
    }

    /**
     * 헬스체크 URL 집중관리 등록
     */
    @PutMapping("/watch-on")
    public ResponseEntity<Void> editWatchOn(@RequestBody Map<String, Object> params) {
        healthCheckUrlService.editWatchOn(params);
        return ResponseEntity.ok().build();
    }

    /**
     * 헬스체크 URL 집중관리 해제
     */
    @PutMapping("/watch-off")
    public ResponseEntity<Void> editWatchOff(@RequestBody Map<String, Object> params) {
        healthCheckUrlService.editWatchOff(params);
        return ResponseEntity.ok().build();
    }

    /**
     * 헬스체크 URL 상세 조회
     */
    @GetMapping("/detail")
    public ResponseEntity<Map<String, Object>> getDetailHealthCheckUrl(@RequestParam Map<String, Object> params) {
        final Map<String, Object> result = healthCheckUrlService.getDetailHealthCheckUrl(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 헬스체크 URL 삭제
     */
    @DeleteMapping
    public ResponseEntity<Void> delHealthCheckUrl(@RequestBody Map<String, Object> params) {
        healthCheckUrlService.delHealthCheckUrl(params);
        return ResponseEntity.noContent().build();
    }

    /**
     * 헬스체크 장애이력 목록 조회
     */
    @GetMapping("/histories")
    public ResponseEntity<List<HealthCheckUrlDTO>> getHealthCheckHist(@RequestParam Map<String, Object> params) {
        final List<HealthCheckUrlDTO> result = healthCheckUrlService.getHealthCheckHist(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 헬스체크 상태 통계 조회
     */
    @GetMapping("/statistics")
    public ResponseEntity<List<Map<String, Object>>> getHealthCheckStat(@RequestParam Map<String, Object> params) {
        final List<Map<String, Object>> result = healthCheckUrlService.getHealthCheckStat(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 엑셀 출력
     */
    @PostMapping("/export")
    public ResponseEntity<Map<String, String>> export(@RequestBody Map<String, Object> params) {
        final Map<String, String> result = healthCheckUrlService.export(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 엑셀 Import
     * Note: 파일 업로드 처리는 별도의 파일 컨트롤러와 연동 필요
     */
    @PostMapping("/import")
    public ResponseEntity<String> importXls(@RequestParam Map<String, Object> params,
                                             @RequestParam String xlsFilePath,
                                             @RequestParam String xlsFileName) {
        final String result = healthCheckUrlService.importXls(params, xlsFilePath, xlsFileName);
        return ResponseEntity.ok(result);
    }
}

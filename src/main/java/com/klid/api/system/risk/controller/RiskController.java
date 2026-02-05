package com.klid.api.system.risk.controller;

import com.klid.api.system.risk.dto.*;
import com.klid.api.system.risk.service.RiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 위험도 관리 REST API
 * 위험도 설정, 위험도 이력, 위협레벨, 기간 관리 기능 제공
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system/risk")
public class RiskController {

    private final RiskService riskService;

    /**
     * 위험도 정보 조회
     */
    @GetMapping
    public ResponseEntity<RiskResponse> getRiskMgmt(@RequestParam final Map<String, Object> params) {
        final RiskResponse risk = riskService.getRiskMgmt(params);
        return ResponseEntity.ok(risk);
    }

    /**
     * 위험도 정보 수정
     */
    @PutMapping
    public ResponseEntity<Void> editRiskMgmt(@RequestBody final RiskRequest request) {
        riskService.editRiskMgmt(request);
        return ResponseEntity.ok().build();
    }

    /**
     * 위험도 이력 조회
     */
    @GetMapping("/history")
    public ResponseEntity<List<RiskHistoryResponse>> getRiskHistory(@RequestParam final Map<String, Object> params) {
        final List<RiskHistoryResponse> history = riskService.getRiskHistory(params);
        return ResponseEntity.ok(history);
    }

    /**
     * 위험도 이력 등록
     */
    @PostMapping("/history")
    public ResponseEntity<Void> addRiskHistory(@RequestBody final RiskHistoryRequest request) {
        riskService.addRiskHistory(request);
        return ResponseEntity.ok().build();
    }

    /**
     * 위험도 이력 삭제
     */
    @DeleteMapping("/history/{historyId}")
    public ResponseEntity<Void> deleteRiskHistory(@PathVariable final String historyId) {
        riskService.delRiskHistory(historyId);
        return ResponseEntity.ok().build();
    }

    /**
     * 위협레벨 단계 조회
     */
    @GetMapping("/threat/levels")
    public ResponseEntity<List<ThreatLevelResponse>> getThreatLevel(@RequestParam final Map<String, Object> params) {
        final List<ThreatLevelResponse> levels = riskService.getThreatLevel(params);
        return ResponseEntity.ok(levels);
    }

    /**
     * 현재 위협레벨 조회
     */
    @GetMapping("/threat/current")
    public ResponseEntity<ThreatResponse> getThreatNow(@RequestParam final Map<String, Object> params) {
        final ThreatResponse threat = riskService.getThreatNow(params);
        return ResponseEntity.ok(threat);
    }

    /**
     * 위협레벨 이력 조회
     */
    @GetMapping("/threat/history")
    public ResponseEntity<List<ThreatResponse>> getThreatHist(@RequestParam final Map<String, Object> params) {
        final List<ThreatResponse> history = riskService.getThreatHist(params);
        return ResponseEntity.ok(history);
    }

    /**
     * 위협레벨 수정
     */
    @PutMapping("/threat")
    public ResponseEntity<Void> editThreat(@RequestBody final ThreatRequest request) {
        riskService.editThreat(request);
        return ResponseEntity.ok().build();
    }

    /**
     * 현재 기간 조회
     */
    @GetMapping("/period/current")
    public ResponseEntity<PeriodResponse> getPeriodNow(@RequestParam final Map<String, Object> params) {
        final PeriodResponse period = riskService.getPeriodNow(params);
        return ResponseEntity.ok(period);
    }

    /**
     * 기간 수정
     */
    @PutMapping("/period")
    public ResponseEntity<Void> editPeriod(@RequestBody final PeriodRequest request) {
        riskService.editPeriod(request);
        return ResponseEntity.ok().build();
    }
}

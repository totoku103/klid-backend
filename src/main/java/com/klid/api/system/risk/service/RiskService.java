package com.klid.api.system.risk.service;

import com.klid.api.system.risk.dto.*;
import com.klid.api.system.risk.persistence.RiskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 위험도 관리 서비스
 */
@Service
@RequiredArgsConstructor
public class RiskService {

    private final RiskMapper riskMapper;

    /**
     * 위험도 정보 조회
     */
    public RiskResponse getRiskMgmt(final Map<String, Object> params) {
        return riskMapper.selectRiskMgmt(params);
    }

    /**
     * 위험도 정보 수정
     */
    @Transactional
    public void editRiskMgmt(final RiskRequest request) {
        riskMapper.updateRiskMgmt(request);
    }

    /**
     * 위험도 이력 조회
     */
    public List<RiskHistoryResponse> getRiskHistory(final Map<String, Object> params) {
        return riskMapper.selectRiskHistory(params);
    }

    /**
     * 위험도 이력 등록
     */
    @Transactional
    public void addRiskHistory(final RiskHistoryRequest request) {
        riskMapper.insertRiskHistory(request);
    }

    /**
     * 위험도 이력 삭제
     */
    @Transactional
    public void delRiskHistory(final String historyId) {
        riskMapper.deleteRiskHistory(historyId);
    }

    /**
     * 위협레벨 단계 조회
     */
    public List<ThreatLevelResponse> getThreatLevel(final Map<String, Object> params) {
        return riskMapper.selectThreatLevel(params);
    }

    /**
     * 현재 위협레벨 조회
     */
    public ThreatResponse getThreatNow(final Map<String, Object> params) {
        return riskMapper.selectThreatNow(params);
    }

    /**
     * 위협레벨 이력 조회
     */
    public List<ThreatResponse> getThreatHist(final Map<String, Object> params) {
        return riskMapper.selectThreatHist(params);
    }

    /**
     * 위협레벨 수정
     */
    @Transactional
    public void editThreat(final ThreatRequest request) {
        riskMapper.updateThreat(request);
    }

    /**
     * 현재 기간 조회
     */
    public PeriodResponse getPeriodNow(final Map<String, Object> params) {
        return riskMapper.selectPeriodNow(params);
    }

    /**
     * 기간 수정
     */
    @Transactional
    public void editPeriod(final PeriodRequest request) {
        riskMapper.updatePeriod(request);
    }
}

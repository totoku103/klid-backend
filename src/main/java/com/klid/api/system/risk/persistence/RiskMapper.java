package com.klid.api.system.risk.persistence;

import com.klid.api.system.risk.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 위험도 관리 Mapper
 */
@Mapper
public interface RiskMapper {

    RiskResponse selectRiskMgmt(Map<String, Object> params);

    void updateRiskMgmt(RiskRequest request);

    List<RiskHistoryResponse> selectRiskHistory(Map<String, Object> params);

    void insertRiskHistory(RiskHistoryRequest request);

    void deleteRiskHistory(String historyId);

    List<ThreatLevelResponse> selectThreatLevel(Map<String, Object> params);

    ThreatResponse selectThreatNow(Map<String, Object> params);

    List<ThreatResponse> selectThreatHist(Map<String, Object> params);

    void updateThreat(ThreatRequest request);

    PeriodResponse selectPeriodNow(Map<String, Object> params);

    void updatePeriod(PeriodRequest request);
}

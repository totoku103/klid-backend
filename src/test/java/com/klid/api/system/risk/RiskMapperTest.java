package com.klid.api.system.risk;

import com.klid.api.BaseMapperTest;
import com.klid.api.system.risk.dto.*;
import com.klid.api.system.risk.persistence.RiskMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * RiskMapper 통합 테스트
 */
class RiskMapperTest extends BaseMapperTest {

    @Autowired
    private RiskMapper riskMapper;

    @Test
    @DisplayName("selectRiskMgmt - 위험도 정보 조회")
    void selectRiskMgmt_ReturnsResultOrNull() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final RiskResponse result = riskMapper.selectRiskMgmt(params);

        // then
        // 데이터 존재 여부에 관계없이 예외가 발생하지 않으면 성공
    }

    @Test
    @DisplayName("selectRiskMgmt - riskId로 조회")
    void selectRiskMgmt_WithRiskId_ReturnsResult() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("riskId", "RISK001");

        // when
        final RiskResponse result = riskMapper.selectRiskMgmt(params);

        // then
        // 데이터 존재 여부에 관계없이 예외가 발생하지 않으면 성공
    }

    @Test
    @DisplayName("updateRiskMgmt - 위험도 정보 수정")
    void updateRiskMgmt_Success() {
        // given
        final RiskRequest request = new RiskRequest();
        request.setRiskId("RISK001");
        request.setRiskLevel("CRITICAL");
        request.setRiskDesc("심각 위험 상태");
        request.setUseYn("Y");

        // when & then - 예외가 발생하지 않으면 성공
        riskMapper.updateRiskMgmt(request);
    }

    @Test
    @DisplayName("selectRiskHistory - 전체 위험도 이력 조회")
    void selectRiskHistory_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<RiskHistoryResponse> result = riskMapper.selectRiskHistory(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectRiskHistory - 날짜 범위로 필터링")
    void selectRiskHistory_WithDateRange_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("startDate", "20250101");
        params.put("endDate", "20251231");

        // when
        final List<RiskHistoryResponse> result = riskMapper.selectRiskHistory(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectRiskHistory - 위험레벨로 필터링")
    void selectRiskHistory_WithRiskLevel_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("riskLevel", "HIGH");

        // when
        final List<RiskHistoryResponse> result = riskMapper.selectRiskHistory(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("insertRiskHistory - 위험도 이력 등록")
    void insertRiskHistory_Success() {
        // given
        final RiskHistoryRequest request = new RiskHistoryRequest();
        request.setHistoryDate("20250205");
        request.setRiskLevel("MEDIUM");
        request.setRiskDesc("중위험 상태 등록");
        request.setRegUser("MAPPER_TEST");

        // when & then - 예외가 발생하지 않으면 성공
        riskMapper.insertRiskHistory(request);
    }

    @Test
    @DisplayName("deleteRiskHistory - 위험도 이력 삭제")
    void deleteRiskHistory_Success() {
        // given
        final String historyId = "NON_EXISTENT_HIST";

        // when & then - 예외가 발생하지 않으면 성공
        riskMapper.deleteRiskHistory(historyId);
    }

    @Test
    @DisplayName("selectThreatLevel - 위협레벨 단계 조회")
    void selectThreatLevel_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<ThreatLevelResponse> result = riskMapper.selectThreatLevel(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectThreatNow - 현재 위협레벨 조회")
    void selectThreatNow_ReturnsResultOrNull() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final ThreatResponse result = riskMapper.selectThreatNow(params);

        // then
        // 데이터 존재 여부에 관계없이 예외가 발생하지 않으면 성공
    }

    @Test
    @DisplayName("selectThreatHist - 위협레벨 이력 조회")
    void selectThreatHist_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<ThreatResponse> result = riskMapper.selectThreatHist(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectThreatHist - 날짜 범위로 필터링")
    void selectThreatHist_WithDateRange_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("startDate", "20250101");
        params.put("endDate", "20251231");

        // when
        final List<ThreatResponse> result = riskMapper.selectThreatHist(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("updateThreat - 위협레벨 수정")
    void updateThreat_Success() {
        // given
        final ThreatRequest request = new ThreatRequest();
        request.setThreatLevel("4");
        request.setThreatDesc("경계 단계");
        request.setStartDate("20250301");
        request.setEndDate("20250331");

        // when & then - 예외가 발생하지 않으면 성공
        riskMapper.updateThreat(request);
    }

    @Test
    @DisplayName("selectPeriodNow - 현재 기간 조회")
    void selectPeriodNow_ReturnsResultOrNull() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final PeriodResponse result = riskMapper.selectPeriodNow(params);

        // then
        // 데이터 존재 여부에 관계없이 예외가 발생하지 않으면 성공
    }

    @Test
    @DisplayName("selectPeriodNow - periodType으로 조회")
    void selectPeriodNow_WithPeriodType_ReturnsResult() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("periodType", "ANNUAL");

        // when
        final PeriodResponse result = riskMapper.selectPeriodNow(params);

        // then
        // 데이터 존재 여부에 관계없이 예외가 발생하지 않으면 성공
    }

    @Test
    @DisplayName("updatePeriod - 기간 수정")
    void updatePeriod_Success() {
        // given
        final PeriodRequest request = new PeriodRequest();
        request.setPeriodType("MONTHLY");
        request.setStartDate("20250201");
        request.setEndDate("20250228");

        // when & then - 예외가 발생하지 않으면 성공
        riskMapper.updatePeriod(request);
    }
}

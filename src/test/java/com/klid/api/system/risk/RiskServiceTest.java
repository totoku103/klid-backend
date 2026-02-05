package com.klid.api.system.risk;

import com.klid.api.BaseServiceTest;
import com.klid.api.system.risk.dto.*;
import com.klid.api.system.risk.service.RiskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * RiskService 통합 테스트
 */
class RiskServiceTest extends BaseServiceTest {

    @Autowired
    private RiskService riskService;

    @Test
    @DisplayName("위험도 정보 조회 - 빈 파라미터")
    void getRiskMgmt_WithEmptyParams_ReturnsResultOrNull() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final RiskResponse result = riskService.getRiskMgmt(params);

        // then
        // 데이터가 없으면 null, 있으면 객체 반환
        // 단순히 예외가 발생하지 않으면 성공
    }

    @Test
    @DisplayName("위험도 정보 조회 - riskId로 조회")
    void getRiskMgmt_WithRiskId_ReturnsResult() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("riskId", "RISK001");

        // when
        final RiskResponse result = riskService.getRiskMgmt(params);

        // then
        // 데이터 존재 여부에 관계없이 예외가 발생하지 않으면 성공
    }

    @Test
    @DisplayName("위험도 정보 수정")
    void editRiskMgmt_Success() {
        // given
        final RiskRequest request = new RiskRequest();
        request.setRiskId("RISK001");
        request.setRiskLevel("HIGH");
        request.setRiskDesc("고위험 상태로 변경");
        request.setUseYn("Y");

        // when & then - 예외가 발생하지 않으면 성공
        riskService.editRiskMgmt(request);
    }

    @Test
    @DisplayName("위험도 이력 조회 - 빈 파라미터")
    void getRiskHistory_WithEmptyParams_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<RiskHistoryResponse> result = riskService.getRiskHistory(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("위험도 이력 조회 - 날짜 범위로 필터링")
    void getRiskHistory_WithDateRange_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("startDate", "20250101");
        params.put("endDate", "20251231");

        // when
        final List<RiskHistoryResponse> result = riskService.getRiskHistory(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("위험도 이력 등록")
    void addRiskHistory_Success() {
        // given
        final RiskHistoryRequest request = new RiskHistoryRequest();
        request.setHistoryDate("20250205");
        request.setRiskLevel("LOW");
        request.setRiskDesc("저위험 상태");
        request.setRegUser("SYSTEM");

        // when & then - 예외가 발생하지 않으면 성공
        riskService.addRiskHistory(request);
    }

    @Test
    @DisplayName("위험도 이력 삭제")
    void delRiskHistory_Success() {
        // given
        final String historyId = "HIST_FOR_DELETE";

        // when & then - 예외가 발생하지 않으면 성공
        riskService.delRiskHistory(historyId);
    }

    @Test
    @DisplayName("위협레벨 단계 조회 - 빈 파라미터")
    void getThreatLevel_WithEmptyParams_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<ThreatLevelResponse> result = riskService.getThreatLevel(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("현재 위협레벨 조회")
    void getThreatNow_ReturnsResultOrNull() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final ThreatResponse result = riskService.getThreatNow(params);

        // then
        // 데이터 존재 여부에 관계없이 예외가 발생하지 않으면 성공
    }

    @Test
    @DisplayName("위협레벨 이력 조회 - 빈 파라미터")
    void getThreatHist_WithEmptyParams_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<ThreatResponse> result = riskService.getThreatHist(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("위협레벨 수정")
    void editThreat_Success() {
        // given
        final ThreatRequest request = new ThreatRequest();
        request.setThreatLevel("2");
        request.setThreatDesc("관심 단계");
        request.setStartDate("20250201");
        request.setEndDate("20250228");

        // when & then - 예외가 발생하지 않으면 성공
        riskService.editThreat(request);
    }

    @Test
    @DisplayName("현재 기간 조회")
    void getPeriodNow_ReturnsResultOrNull() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final PeriodResponse result = riskService.getPeriodNow(params);

        // then
        // 데이터 존재 여부에 관계없이 예외가 발생하지 않으면 성공
    }

    @Test
    @DisplayName("기간 수정")
    void editPeriod_Success() {
        // given
        final PeriodRequest request = new PeriodRequest();
        request.setPeriodType("QUARTER");
        request.setStartDate("20250101");
        request.setEndDate("20250331");

        // when & then - 예외가 발생하지 않으면 성공
        riskService.editPeriod(request);
    }
}

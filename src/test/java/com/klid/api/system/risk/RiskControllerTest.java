package com.klid.api.system.risk;

import com.klid.api.BaseControllerTest;
import com.klid.api.system.risk.dto.PeriodRequest;
import com.klid.api.system.risk.dto.RiskHistoryRequest;
import com.klid.api.system.risk.dto.RiskRequest;
import com.klid.api.system.risk.dto.ThreatRequest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * RiskController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class RiskControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/system/risk";

    @Test
    @DisplayName("GET /api/system/risk - 위험도 정보 조회")
    void getRiskMgmt_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/system/risk - 파라미터로 위험도 조회")
    void getRiskMgmt_WithParams_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("riskId", "RISK001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /api/system/risk - 위험도 정보 수정")
    void editRiskMgmt_ReturnsOk() throws Exception {
        final RiskRequest request = new RiskRequest();
        request.setRiskId("RISK001");
        request.setRiskLevel("HIGH");
        request.setRiskDesc("고위험 상태");
        request.setUseYn("Y");

        mockMvc.perform(put(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/system/risk/history - 위험도 이력 조회")
    void getRiskHistory_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/history")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/system/risk/history - 날짜 범위로 이력 조회")
    void getRiskHistory_WithDateRange_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/history")
                        .param("startDate", "20250101")
                        .param("endDate", "20251231")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/system/risk/history - 위험도 이력 등록")
    void addRiskHistory_ReturnsOk() throws Exception {
        final RiskHistoryRequest request = new RiskHistoryRequest();
        request.setHistoryDate("20250205");
        request.setRiskLevel("MEDIUM");
        request.setRiskDesc("중위험 상태로 변경");
        request.setRegUser("ADMIN");

        mockMvc.perform(post(BASE_URL + "/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /api/system/risk/history/{historyId} - 위험도 이력 삭제")
    void deleteRiskHistory_ReturnsOk() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/history/HIST001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/system/risk/threat/levels - 위협레벨 단계 조회")
    void getThreatLevel_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/threat/levels")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/system/risk/threat/current - 현재 위협레벨 조회")
    void getThreatNow_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/threat/current")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/system/risk/threat/history - 위협레벨 이력 조회")
    void getThreatHist_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/threat/history")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("PUT /api/system/risk/threat - 위협레벨 수정")
    void editThreat_ReturnsOk() throws Exception {
        final ThreatRequest request = new ThreatRequest();
        request.setThreatLevel("3");
        request.setThreatDesc("주의 단계");
        request.setStartDate("20250201");
        request.setEndDate("20250228");

        mockMvc.perform(put(BASE_URL + "/threat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/system/risk/period/current - 현재 기간 조회")
    void getPeriodNow_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/period/current")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /api/system/risk/period - 기간 수정")
    void editPeriod_ReturnsOk() throws Exception {
        final PeriodRequest request = new PeriodRequest();
        request.setPeriodType("ANNUAL");
        request.setStartDate("20250101");
        request.setEndDate("20251231");

        mockMvc.perform(put(BASE_URL + "/period")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }
}

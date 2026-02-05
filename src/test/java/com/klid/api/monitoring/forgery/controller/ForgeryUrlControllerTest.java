package com.klid.api.monitoring.forgery.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ForgeryUrlController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class ForgeryUrlControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/monitoring/forgery-url - 위변조 URL 목록 조회")
    void getForgeryUrl_shouldReturnList() throws Exception {
        mockMvc.perform(get("/api/monitoring/forgery-url")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/monitoring/forgery-url/histories - 위변조 URL 이력 목록 조회")
    void getForgeryUrlHist_shouldReturnHistoryList() throws Exception {
        mockMvc.perform(get("/api/monitoring/forgery-url/histories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /api/monitoring/forgery-url/main-monitoring - 메인 홈페이지 모니터링 조회")
    void getMainForgeryHm_shouldReturnMonitoringList() throws Exception {
        mockMvc.perform(post("/api/monitoring/forgery-url/main-monitoring")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /api/monitoring/forgery-url/main-monitoring-statistics - 메인 홈페이지 모니터링 수치 통계")
    void getMainForgeryCnt_shouldReturnStatistics() throws Exception {
        mockMvc.perform(post("/api/monitoring/forgery-url/main-monitoring-statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /api/monitoring/forgery-url/by-institution - 기관명으로 조회")
    void getByInstNm_shouldReturnByInstitution() throws Exception {
        final Map<String, Object> params = new HashMap<>();
        params.put("instNm", "테스트기관");

        mockMvc.perform(post("/api/monitoring/forgery-url/by-institution")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isOk());
    }
}

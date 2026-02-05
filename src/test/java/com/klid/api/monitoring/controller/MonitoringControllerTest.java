package com.klid.api.monitoring.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * MonitoringController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class MonitoringControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/monitoring/stats - 모니터링 통계 조회")
    void getMonitoringStats_shouldReturnStats() throws Exception {
        mockMvc.perform(get("/api/monitoring/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/monitoring/stats - 기관코드로 모니터링 통계 조회")
    void getMonitoringStats_withInstCd_shouldReturnStats() throws Exception {
        mockMvc.perform(get("/api/monitoring/stats")
                        .param("sInstCd", "1000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/monitoring/stats - 권한 메인으로 모니터링 통계 조회")
    void getMonitoringStats_withAuthMain_shouldReturnStats() throws Exception {
        mockMvc.perform(get("/api/monitoring/stats")
                        .param("sAuthMain", "AUTH_MAIN_1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/monitoring/detail - 모니터링 상세 조회")
    void getMonitoringDetail_shouldReturnDetailList() throws Exception {
        mockMvc.perform(get("/api/monitoring/detail")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/monitoring/detail - 시간 범위로 모니터링 상세 조회")
    void getMonitoringDetail_withTimeRange_shouldReturnDetailList() throws Exception {
        mockMvc.perform(get("/api/monitoring/detail")
                        .param("sInstCd", "1000")
                        .param("time1", "2024-01-01 00:00:00")
                        .param("time2", "2024-12-31 23:59:59")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

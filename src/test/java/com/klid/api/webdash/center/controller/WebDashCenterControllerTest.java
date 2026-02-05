package com.klid.api.webdash.center.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * WebDashCenterController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class WebDashCenterControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/webdash/center/att-nation-top5 - 공격국가 Top5 조회")
    void getAttNationTop5_shouldReturnList() throws Exception {
        mockMvc.perform(get("/api/webdash/center/att-nation-top5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/webdash/center/type-chart - 유형별 차트 데이터 조회")
    void getTypeChart_shouldReturnChartData() throws Exception {
        mockMvc.perform(get("/api/webdash/center/type-chart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/webdash/center/evt-chart - 이벤트 차트 데이터 조회")
    void getEvtChart_shouldReturnChartData() throws Exception {
        mockMvc.perform(get("/api/webdash/center/evt-chart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/webdash/center/evt-all-chart - 전체 이벤트 차트 데이터 조회")
    void getEvtAllChart_shouldReturnChartData() throws Exception {
        mockMvc.perform(get("/api/webdash/center/evt-all-chart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

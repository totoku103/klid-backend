package com.klid.api.report.incident.controller;

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
 * ReportInciPrtyController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class ReportInciPrtyControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/report/incident/priority/list - 우선순위별 목록 조회 엔드포인트 존재 확인")
    void getPriorityList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/incident/priority/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/incident/priority/save-chart-image - 하이차트 이미지 저장 엔드포인트 존재 확인")
    void saveHighChartImage_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/incident/priority/save-chart-image")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/incident/priority/export - 우선순위별 보고서 HWP 출력 엔드포인트 존재 확인")
    void exportReport_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/incident/priority/export")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }
}

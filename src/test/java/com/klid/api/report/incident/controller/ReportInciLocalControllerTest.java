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
 * ReportInciLocalController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class ReportInciLocalControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/report/incident/local/list - 지역별(시도) 목록 조회 엔드포인트 존재 확인")
    void getLocalList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/incident/local/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/incident/local/sido-list - 시군구별 목록 조회 엔드포인트 존재 확인")
    void getSidoList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/incident/local/sido-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/incident/local/save-chart-image - 하이차트 이미지 저장 엔드포인트 존재 확인")
    void saveHighChartImage_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/incident/local/save-chart-image")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/incident/local/export - 지역별 보고서 HWP 출력 엔드포인트 존재 확인")
    void exportReport_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/incident/local/export")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }
}

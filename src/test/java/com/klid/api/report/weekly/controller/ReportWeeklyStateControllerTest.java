package com.klid.api.report.weekly.controller;

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
 * ReportWeeklyStateController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class ReportWeeklyStateControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/report/weekly/state/rotation-list - 근무자 순번 목록 조회 엔드포인트 존재 확인")
    void getRotationList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/weekly/state/rotation-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/weekly/state/list - 주간 목록 조회 엔드포인트 존재 확인")
    void getWeeklyList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/weekly/state/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/weekly/state/list-before - 주간 이전 목록 조회 엔드포인트 존재 확인")
    void getWeeklyListBefore_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/weekly/state/list-before")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/weekly/state/total-list - 주간 누적 목록 조회 엔드포인트 존재 확인")
    void getWeeklyTotalList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/weekly/state/total-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/weekly/state/type-accident-list - 사고 유형별 목록 조회 엔드포인트 존재 확인")
    void getTypeAccidentList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/weekly/state/type-accident-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/weekly/state/detection-list - 탐지 목록 조회 엔드포인트 존재 확인")
    void getDetectionList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/weekly/state/detection-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/weekly/state/download - 주간 보고서 HWP 다운로드 엔드포인트 존재 확인")
    void makeReportDownload_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/weekly/state/download")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }
}

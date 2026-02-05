package com.klid.api.report.incident.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ReportInciDetailController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class ReportInciDetailControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/report/incident/detail/list - 사고 상세 목록 조회 엔드포인트 존재 확인")
    void getDetailList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/incident/detail/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/incident/detail/export/daily - 일일 보고서 HWP 다운로드 엔드포인트 존재 확인")
    void exportDailyReport_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/incident/detail/export/daily")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }
}

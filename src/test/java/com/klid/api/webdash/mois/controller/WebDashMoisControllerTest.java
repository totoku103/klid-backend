package com.klid.api.webdash.mois.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * WebDashMoisController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class WebDashMoisControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/webdash/mois/threat-now - 현재 위협 조회")
    void getThreatNow_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/mois/threat-now")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/mois/hm-hc-url-center - 센터 HM/HC URL 조회")
    void getHmHcUrlCenter_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/mois/hm-hc-url-center")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/mois/hm-hc-url-region - 지역별 HM/HC URL 조회")
    void getHmHcUrlRegion_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/mois/hm-hc-url-region")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/mois/forgery-region - 지역별 위변조 조회")
    void getForgeryRegion_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/mois/forgery-region")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/mois/region-status - 지역 현황 조회")
    void getRegionStatus_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/mois/region-status")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/mois/region-status-auto - 자동 지역 현황 조회")
    void getRegionStatusAuto_shouldReturnSum() throws Exception {
        mockMvc.perform(get("/api/webdash/mois/region-status-auto")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/mois/region-status-manual - 수동 지역 현황 조회")
    void getRegionStatusManual_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/mois/region-status-manual")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/mois/dash-config-list - 대시보드 설정 목록 조회")
    void getDashConfigList_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/mois/dash-config-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/mois/dash-chart-sum - 대시보드 차트 합계 조회")
    void getDashChartSum_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/mois/dash-chart-sum")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

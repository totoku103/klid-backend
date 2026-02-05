package com.klid.api.webdash.admin.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * AdminControlController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class AdminControlControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/webdash/admin/incident-status - 사고 현황 조회")
    void getIncidentStatus_shouldReturnStatus() throws Exception {
        mockMvc.perform(get("/api/webdash/admin/incident-status")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/admin/inci-cnt - 사고 건수 조회")
    void getInciCnt_shouldReturnList() throws Exception {
        mockMvc.perform(get("/api/webdash/admin/inci-cnt")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/webdash/admin/tbzledge-cnt - Tbzledge 건수 조회")
    void getTbzledgeCnt_shouldReturnList() throws Exception {
        mockMvc.perform(get("/api/webdash/admin/tbzledge-cnt")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/webdash/admin/local-inci-cnt - 지역별 사고 건수 조회")
    void getLocalInciCnt_shouldReturnList() throws Exception {
        mockMvc.perform(get("/api/webdash/admin/local-inci-cnt")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/webdash/admin/local-status - 지역별 현황 조회")
    void getLocalStatus_shouldReturnList() throws Exception {
        mockMvc.perform(get("/api/webdash/admin/local-status")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/webdash/admin/url-status - URL 현황 조회")
    void getUrlStatus_shouldReturnStatus() throws Exception {
        mockMvc.perform(get("/api/webdash/admin/url-status")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/admin/sys-error-status - hostNm=local이면 성공")
    void getSysErrorStatus_withLocalHostNm_shouldReturnList() throws Exception {
        mockMvc.perform(get("/api/webdash/admin/sys-error-status")
                        .param("hostNm", "local")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/webdash/admin/inci-type-cnt - 사고 유형별 건수 조회")
    void getInciTypeCnt_shouldReturnList() throws Exception {
        mockMvc.perform(get("/api/webdash/admin/inci-type-cnt")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

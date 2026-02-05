package com.klid.api.webdash.sido.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * WebDashSidoController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class WebDashSidoControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/webdash/sido/notice-list - 공지사항 목록 조회")
    void getNoticeList_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/sido/notice-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/sido/secu-list - 보안자료 목록 조회")
    void getSecuList_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/sido/secu-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/sido/region-status-manual - 수동 지역 현황 조회")
    void getRegionStatusManual_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/sido/region-status-manual")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/sido/forgery-check - 위변조 체크 조회")
    void getForgeryCheck_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/sido/forgery-check")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/sido/hc-check - 헬스체크 조회")
    void getHcCheck_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/sido/hc-check")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/sido/process - 처리 현황 조회")
    void getProcess_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/sido/process")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/webdash/sido/sido-list - 시도 목록 조회")
    void getSidoList_shouldReturnData() throws Exception {
        mockMvc.perform(get("/api/webdash/sido/sido-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

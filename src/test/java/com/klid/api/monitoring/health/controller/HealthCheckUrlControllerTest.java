package com.klid.api.monitoring.health.controller;

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
 * HealthCheckUrlController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class HealthCheckUrlControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/monitoring/health-check-url - 헬스체크 URL 목록 조회")
    void getHealthCheckUrl_shouldReturnList() throws Exception {
        mockMvc.perform(get("/api/monitoring/health-check-url")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /api/monitoring/health-check-url - 헬스체크 URL 등록")
    void addHealthCheckUrl_shouldCreateAndReturnSeqNo() throws Exception {
        final Map<String, Object> params = new HashMap<>();
        params.put("url", "https://test.example.com");
        params.put("instCd", 1000);
        params.put("instCenterNm", "테스트 기관");
        params.put("useYn", 1);

        mockMvc.perform(post("/api/monitoring/health-check-url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PUT /api/monitoring/health-check-url - 헬스체크 URL 수정")
    void editHealthCheckUrl_shouldUpdateSuccessfully() throws Exception {
        final Map<String, Object> params = new HashMap<>();
        params.put("seqNo", 1);
        params.put("url", "https://updated.example.com");

        mockMvc.perform(put("/api/monitoring/health-check-url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /api/monitoring/health-check-url/watch-on - 집중관리 등록")
    void editWatchOn_shouldEnableWatch() throws Exception {
        final Map<String, Object> params = new HashMap<>();
        params.put("seqNo", 1);

        mockMvc.perform(put("/api/monitoring/health-check-url/watch-on")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /api/monitoring/health-check-url/watch-off - 집중관리 해제")
    void editWatchOff_shouldDisableWatch() throws Exception {
        final Map<String, Object> params = new HashMap<>();
        params.put("seqNo", 1);

        mockMvc.perform(put("/api/monitoring/health-check-url/watch-off")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/monitoring/health-check-url/detail - 헬스체크 URL 상세 조회")
    void getDetailHealthCheckUrl_shouldReturnDetail() throws Exception {
        mockMvc.perform(get("/api/monitoring/health-check-url/detail")
                        .param("seqNo", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("DELETE /api/monitoring/health-check-url - 헬스체크 URL 삭제")
    void delHealthCheckUrl_shouldDeleteSuccessfully() throws Exception {
        final Map<String, Object> params = new HashMap<>();
        params.put("seqNo", 999);

        mockMvc.perform(delete("/api/monitoring/health-check-url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /api/monitoring/health-check-url/histories - 헬스체크 장애이력 목록 조회")
    void getHealthCheckHist_shouldReturnHistoryList() throws Exception {
        mockMvc.perform(get("/api/monitoring/health-check-url/histories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/monitoring/health-check-url/statistics - 헬스체크 상태 통계 조회")
    void getHealthCheckStat_shouldReturnStatistics() throws Exception {
        mockMvc.perform(get("/api/monitoring/health-check-url/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /api/monitoring/health-check-url/export - 엑셀 출력")
    void export_shouldReturnFilePath() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/monitoring/health-check-url/export")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isOk());
    }
}

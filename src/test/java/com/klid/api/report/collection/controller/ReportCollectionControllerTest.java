package com.klid.api.report.collection.controller;

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
 * ReportCollectionController 통합 테스트
 *
 * Note: 서비스 메소드들이 UnsupportedOperationException을 던지므로
 * 현재는 엔드포인트 라우팅 및 HTTP 응답 코드만 테스트
 */
@Import(TestSecurityConfig.class)
class ReportCollectionControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/report/collection/security-hacking-detail - 보안 해킹 관리 대장 상세 조회 엔드포인트 존재 확인")
    void getSecurityHackingDetail_endpointExists() throws Exception {
        // 서비스가 미구현 상태이므로 500 에러 예상
        mockMvc.perform(get("/api/report/collection/security-hacking-detail")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/collection/security-list-detail - 보안자료실 목록 상세 조회 엔드포인트 존재 확인")
    void getSecurityListDetail_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/collection/security-list-detail")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/collection/notice-list-detail - 공지사항 목록 상세 조회 엔드포인트 존재 확인")
    void getNoticeListDetail_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/collection/notice-list-detail")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/collection/security-vulnerability-detail - 보안 취약점 관리 대장 상세 조회 엔드포인트 존재 확인")
    void getSecurityVulnerabilityDetail_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/collection/security-vulnerability-detail")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/collection/incident-detail - 사고 처리중 현황 상세 조회 엔드포인트 존재 확인")
    void getIncidentDetail_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/collection/incident-detail")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/collection/export/notice-list - 공지사항 현황 엑셀 출력 엔드포인트 존재 확인")
    void exportNoticeList_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/collection/export/notice-list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/collection/export/security-list - 보안자료실 현황 엑셀 출력 엔드포인트 존재 확인")
    void exportSecurityList_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/collection/export/security-list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/collection/export/security-hacking - 해킹관리대장 엑셀 출력 엔드포인트 존재 확인")
    void exportSecurityHacking_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/collection/export/security-hacking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/collection/export/security-vulnerability - 취약점관리대장 엑셀 출력 엔드포인트 존재 확인")
    void exportSecurityVulnerability_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/collection/export/security-vulnerability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/collection/export/incident-detail - 처리중현황 엑셀 출력 엔드포인트 존재 확인")
    void exportIncidentDetail_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/collection/export/incident-detail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/collection/export/ctrs-daily - 일일운영현황 엑셀 출력 엔드포인트 존재 확인")
    void exportCTRSDaily_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/collection/export/ctrs-daily")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }
}

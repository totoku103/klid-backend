package com.klid.api.report.security.controller;

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
 * ReportSecurityResultController 통합 테스트
 *
 * Note: 서비스 메소드들이 UnsupportedOperationException을 던지므로
 * 현재는 엔드포인트 라우팅 및 HTTP 응답 코드만 테스트
 */
@Import(TestSecurityConfig.class)
class ReportSecurityResultControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/report/security/result/total - 보안 결과 총계 조회 엔드포인트 존재 확인")
    void getResultTotal_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/security/result/total")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/security/result/list - 보안 결과 목록 조회 엔드포인트 존재 확인")
    void getResultList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/security/result/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/security/result/except-list - 보안 결과 제외 목록 조회 엔드포인트 존재 확인")
    void getResultExceptList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/security/result/except-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/security/result/download - 보안 보고서 HWP 다운로드 엔드포인트 존재 확인")
    void makeReportDownload_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/security/result/download")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }
}

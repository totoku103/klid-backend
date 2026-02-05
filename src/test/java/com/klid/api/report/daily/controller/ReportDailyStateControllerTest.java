package com.klid.api.report.daily.controller;

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
 * ReportDailyStateController 통합 테스트
 *
 * Note: 서비스 메소드들이 UnsupportedOperationException을 던지므로
 * 현재는 엔드포인트 라우팅 및 HTTP 응답 코드만 테스트
 */
@Import(TestSecurityConfig.class)
class ReportDailyStateControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/report/daily/state/rotation-list - 근무자 순번 목록 조회 엔드포인트 존재 확인")
    void getRotationList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/daily/state/rotation-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/daily/state/list - 일일 목록 조회 엔드포인트 존재 확인")
    void getDailyList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/daily/state/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/daily/state/total-list - 일일 누적 목록 조회 엔드포인트 존재 확인")
    void getDailyTotalList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/daily/state/total-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/daily/state/type-accident-list - 사고 유형별 목록 조회 엔드포인트 존재 확인")
    void getTypeAccidentList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/daily/state/type-accident-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/daily/state/detection-list - 탐지 목록 조회 엔드포인트 존재 확인")
    void getDetectionList_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/daily/state/detection-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("POST /api/report/daily/state/download - 일일 상태 보고서 HWP 다운로드 엔드포인트 존재 확인")
    void makeReportDownload_endpointExists() throws Exception {
        final Map<String, Object> params = new HashMap<>();

        mockMvc.perform(post("/api/report/daily/state/download")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().is5xxServerError());
    }
}

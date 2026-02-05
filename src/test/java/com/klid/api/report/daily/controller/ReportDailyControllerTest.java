package com.klid.api.report.daily.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ReportDailyController 통합 테스트
 *
 * Note: 서비스 메소드들이 UnsupportedOperationException을 던지므로
 * 현재는 엔드포인트 라우팅 및 HTTP 응답 코드만 테스트
 */
@Import(TestSecurityConfig.class)
class ReportDailyControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("GET /api/report/daily/statistics - 일일 통계 조회 엔드포인트 존재 확인")
    void getDayStatistics_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/daily/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/report/daily/download - 일일 보고서 HWP 다운로드 엔드포인트 존재 확인")
    void downloadDailyReport_endpointExists() throws Exception {
        mockMvc.perform(get("/api/report/daily/download")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }
}

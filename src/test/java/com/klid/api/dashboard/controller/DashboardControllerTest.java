package com.klid.api.dashboard.controller;

import com.klid.api.BaseControllerTest;
import com.klid.api.dashboard.dto.*;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * DashboardController 통합 테스트.
 * 실제 데이터베이스와 연동하여 대시보드 API를 검증합니다.
 */
@Import(TestSecurityConfig.class)
@DisplayName("대시보드 Controller 통합 테스트")
class DashboardControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("위협 상태 조회 - instCd 없음")
    void getThreatStatus_WithoutInstCd() throws Exception {
        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/threat-status"))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final ThreatStatusDTO response = fromJson(responseBody, ThreatStatusDTO.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("위협 상태 조회 - instCd 포함")
    void getThreatStatus_WithInstCd() throws Exception {
        // given
        final String instCd = "TEST001";

        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/threat-status")
                        .param("instCd", instCd))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final ThreatStatusDTO response = fromJson(responseBody, ThreatStatusDTO.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("기간 설정 조회 - instCd 없음")
    void getPeriodSetting_WithoutInstCd() throws Exception {
        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/period-setting"))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final PeriodSettingDTO response = fromJson(responseBody, PeriodSettingDTO.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("기간 설정 조회 - instCd 포함")
    void getPeriodSetting_WithInstCd() throws Exception {
        // given
        final String instCd = "TEST001";

        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/period-setting")
                        .param("instCd", instCd))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final PeriodSettingDTO response = fromJson(responseBody, PeriodSettingDTO.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("기간 상태 조회")
    void getPeriodStatus() throws Exception {
        // given
        final String sAuthMain = "AUTH001";
        final String sInstCd = "INST001";

        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/period-status")
                        .param("sAuthMain", sAuthMain)
                        .param("sInstCd", sInstCd))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final PeriodStatusDTO response = fromJson(responseBody, PeriodStatusDTO.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("기간 상태 조회 - 파라미터 없음")
    void getPeriodStatus_WithoutParams() throws Exception {
        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/period-status"))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final PeriodStatusDTO response = fromJson(responseBody, PeriodStatusDTO.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("금일 상태 조회")
    void getTodayStatus() throws Exception {
        // given
        final String sAuthMain = "AUTH001";
        final String sInstCd = "INST001";
        final String atype = "TYPE001";

        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/today-status")
                        .param("sAuthMain", sAuthMain)
                        .param("sInstCd", sInstCd)
                        .param("atype", atype))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final TodayStatusDTO response = fromJson(responseBody, TodayStatusDTO.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("금일 상태 조회 - 파라미터 없음")
    void getTodayStatus_WithoutParams() throws Exception {
        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/today-status"))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final TodayStatusDTO response = fromJson(responseBody, TodayStatusDTO.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("연도별 상태 조회")
    void getYearStatus() throws Exception {
        // given
        final String sAuthMain = "AUTH001";
        final String sInstCd = "INST001";
        final String atype = "TYPE001";

        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/year-status")
                        .param("sAuthMain", sAuthMain)
                        .param("sInstCd", sInstCd)
                        .param("atype", atype))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final YearStatusDTO response = fromJson(responseBody, YearStatusDTO.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("연도별 상태 조회 - 파라미터 없음")
    void getYearStatus_WithoutParams() throws Exception {
        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/year-status"))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final YearStatusDTO response = fromJson(responseBody, YearStatusDTO.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("사고 유형 Top5 조회")
    void getAccidentTypeTop5() throws Exception {
        // given
        final String sAuthMain = "AUTH001";
        final String instCd = "INST001";
        final String dateType = "MONTH";
        final String startDt = "20240101";
        final String endDt = "20241231";

        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/accident-type-top5")
                        .param("sAuthMain", sAuthMain)
                        .param("instCd", instCd)
                        .param("dateType", dateType)
                        .param("startDt", startDt)
                        .param("endDt", endDt))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        assertThat(responseBody).isNotNull();

        // List 타입이므로 배열로 파싱
        final List<?> response = fromJson(responseBody, List.class);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("사고 유형 Top5 조회 - 파라미터 없음")
    void getAccidentTypeTop5_WithoutParams() throws Exception {
        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/accident-type-top5"))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final List<?> response = fromJson(responseBody, List.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("기관 Top5 조회 - SIDO 정렬")
    void getInstitutionTop5_SidoSort() throws Exception {
        // given
        final String sAuthMain = "AUTH001";
        final String instCd = "INST001";
        final String dateType = "MONTH";
        final String startDt = "20240101";
        final String endDt = "20241231";
        final String sortType = "SIDO";
        final String topInstView = "Y";

        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/institution-top5")
                        .param("sAuthMain", sAuthMain)
                        .param("instCd", instCd)
                        .param("dateType", dateType)
                        .param("startDt", startDt)
                        .param("endDt", endDt)
                        .param("sortType", sortType)
                        .param("topInstView", topInstView))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final List<?> response = fromJson(responseBody, List.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("기관 Top5 조회 - LOCAL 정렬")
    void getInstitutionTop5_LocalSort() throws Exception {
        // given
        final String sAuthMain = "AUTH001";
        final String instCd = "INST001";
        final String dateType = "MONTH";
        final String startDt = "20240101";
        final String endDt = "20241231";
        final String sortType = "LOCAL";
        final String topInstView = "Y";

        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/institution-top5")
                        .param("sAuthMain", sAuthMain)
                        .param("instCd", instCd)
                        .param("dateType", dateType)
                        .param("startDt", startDt)
                        .param("endDt", endDt)
                        .param("sortType", sortType)
                        .param("topInstView", topInstView))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final List<?> response = fromJson(responseBody, List.class);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("기관 Top5 조회 - 파라미터 없음")
    void getInstitutionTop5_WithoutParams() throws Exception {
        // when
        final MvcResult result = mockMvc.perform(get("/api/dashboard/institution-top5"))
                .andExpect(status().isOk())
                .andReturn();

        // then
        final String responseBody = result.getResponse().getContentAsString();
        final List<?> response = fromJson(responseBody, List.class);

        assertThat(response).isNotNull();
    }
}

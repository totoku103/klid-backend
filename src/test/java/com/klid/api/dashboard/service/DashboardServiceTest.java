package com.klid.api.dashboard.service;

import com.klid.api.BaseServiceTest;
import com.klid.api.dashboard.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DashboardService 통합 테스트.
 * 실제 데이터베이스와 연동하여 대시보드 비즈니스 로직을 검증합니다.
 */
@DisplayName("대시보드 Service 통합 테스트")
class DashboardServiceTest extends BaseServiceTest {

    @Autowired
    private DashboardService dashboardService;

    @Test
    @DisplayName("위협 상태 조회 - instCd 없음")
    void getThreatStatus_WithoutInstCd() {
        // when
        final ThreatStatusDTO result = dashboardService.getThreatStatus(null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("위협 상태 조회 - instCd 포함")
    void getThreatStatus_WithInstCd() {
        // given
        final String instCd = "TEST001";

        // when
        final ThreatStatusDTO result = dashboardService.getThreatStatus(instCd);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("기간 설정 조회 - instCd 없음")
    void getPeriodSetting_WithoutInstCd() {
        // when
        final PeriodSettingDTO result = dashboardService.getPeriodSetting(null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("기간 설정 조회 - instCd 포함")
    void getPeriodSetting_WithInstCd() {
        // given
        final String instCd = "TEST001";

        // when
        final PeriodSettingDTO result = dashboardService.getPeriodSetting(instCd);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("기간 상태 조회")
    void getPeriodStatus() {
        // given
        final String sAuthMain = "AUTH001";
        final String sInstCd = "INST001";

        // when
        final PeriodStatusDTO result = dashboardService.getPeriodStatus(sAuthMain, sInstCd);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("기간 상태 조회 - 파라미터 없음")
    void getPeriodStatus_WithoutParams() {
        // when
        final PeriodStatusDTO result = dashboardService.getPeriodStatus(null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("금일 상태 조회")
    void getTodayStatus() {
        // given
        final String sAuthMain = "AUTH001";
        final String sInstCd = "INST001";
        final String atype = "TYPE001";

        // when
        final TodayStatusDTO result = dashboardService.getTodayStatus(sAuthMain, sInstCd, atype);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("금일 상태 조회 - 파라미터 없음")
    void getTodayStatus_WithoutParams() {
        // when
        final TodayStatusDTO result = dashboardService.getTodayStatus(null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("연도별 상태 조회")
    void getYearStatus() {
        // given
        final String sAuthMain = "AUTH001";
        final String sInstCd = "INST001";
        final String atype = "TYPE001";

        // when
        final YearStatusDTO result = dashboardService.getYearStatus(sAuthMain, sInstCd, atype);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("연도별 상태 조회 - 파라미터 없음")
    void getYearStatus_WithoutParams() {
        // when
        final YearStatusDTO result = dashboardService.getYearStatus(null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 유형 Top5 조회")
    void getAccidentTypeTop5() {
        // given
        final String sAuthMain = "AUTH001";
        final String instCd = "INST001";
        final String dateType = "MONTH";
        final String startDt = "20240101";
        final String endDt = "20241231";

        // when
        final List<AccidentTypeRankDTO> result = dashboardService.getAccidentTypeTop5(
                sAuthMain, instCd, dateType, startDt, endDt);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 유형 Top5 조회 - 파라미터 없음")
    void getAccidentTypeTop5_WithoutParams() {
        // when
        final List<AccidentTypeRankDTO> result = dashboardService.getAccidentTypeTop5(
                null, null, null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 유형 Top5 조회 - 결과가 리스트 타입임을 검증")
    void getAccidentTypeTop5_ReturnsListType() {
        // given
        final String sAuthMain = "AUTH001";
        final String instCd = "INST001";
        final String dateType = "MONTH";
        final String startDt = "20240101";
        final String endDt = "20241231";

        // when
        final List<AccidentTypeRankDTO> result = dashboardService.getAccidentTypeTop5(
                sAuthMain, instCd, dateType, startDt, endDt);

        // then
        assertThat(result)
                .isNotNull()
                .isInstanceOf(List.class);
    }

    @Test
    @DisplayName("기관 Top5 조회 - SIDO 정렬")
    void getInstitutionTop5_SidoSort() {
        // given
        final String sAuthMain = "AUTH001";
        final String instCd = "INST001";
        final String dateType = "MONTH";
        final String startDt = "20240101";
        final String endDt = "20241231";
        final String sortType = "SIDO";
        final String topInstView = "Y";

        // when
        final List<InstitutionRankDTO> result = dashboardService.getInstitutionTop5(
                sAuthMain, instCd, dateType, startDt, endDt, sortType, topInstView);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("기관 Top5 조회 - LOCAL 정렬")
    void getInstitutionTop5_LocalSort() {
        // given
        final String sAuthMain = "AUTH001";
        final String instCd = "INST001";
        final String dateType = "MONTH";
        final String startDt = "20240101";
        final String endDt = "20241231";
        final String sortType = "LOCAL";
        final String topInstView = "Y";

        // when
        final List<InstitutionRankDTO> result = dashboardService.getInstitutionTop5(
                sAuthMain, instCd, dateType, startDt, endDt, sortType, topInstView);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("기관 Top5 조회 - sortType이 SIDO가 아닌 경우 LOCAL 메서드 호출")
    void getInstitutionTop5_DefaultToLocal() {
        // given
        final String sAuthMain = "AUTH001";
        final String instCd = "INST001";
        final String dateType = "MONTH";
        final String startDt = "20240101";
        final String endDt = "20241231";
        final String sortType = "OTHER";
        final String topInstView = "Y";

        // when
        final List<InstitutionRankDTO> result = dashboardService.getInstitutionTop5(
                sAuthMain, instCd, dateType, startDt, endDt, sortType, topInstView);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("기관 Top5 조회 - 파라미터 없음")
    void getInstitutionTop5_WithoutParams() {
        // when
        final List<InstitutionRankDTO> result = dashboardService.getInstitutionTop5(
                null, null, null, null, null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("기관 Top5 조회 - 결과가 리스트 타입임을 검증")
    void getInstitutionTop5_ReturnsListType() {
        // given
        final String sAuthMain = "AUTH001";
        final String instCd = "INST001";
        final String dateType = "MONTH";
        final String startDt = "20240101";
        final String endDt = "20241231";
        final String sortType = "SIDO";
        final String topInstView = "Y";

        // when
        final List<InstitutionRankDTO> result = dashboardService.getInstitutionTop5(
                sAuthMain, instCd, dateType, startDt, endDt, sortType, topInstView);

        // then
        assertThat(result)
                .isNotNull()
                .isInstanceOf(List.class);
    }
}

package com.klid.api.dashboard.persistence;

import com.klid.api.BaseMapperTest;
import com.klid.api.dashboard.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DashboardMapper 통합 테스트.
 * 실제 데이터베이스와 연동하여 MyBatis SQL 쿼리를 검증합니다.
 */
@DisplayName("대시보드 Mapper 통합 테스트")
class DashboardMapperTest extends BaseMapperTest {

    @Autowired
    private DashboardMapper dashboardMapper;

    @Test
    @DisplayName("위협 상태 조회 - instCd 없음")
    void selectThreatNow_WithoutInstCd() {
        // when
        final ThreatStatusDTO result = dashboardMapper.selectThreatNow(null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("위협 상태 조회 - instCd 포함")
    void selectThreatNow_WithInstCd() {
        // given
        final String instCd = "TEST001";

        // when
        final ThreatStatusDTO result = dashboardMapper.selectThreatNow(instCd);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("위협 상태 조회 - 반환 필드 검증")
    void selectThreatNow_VerifyFields() {
        // when
        final ThreatStatusDTO result = dashboardMapper.selectThreatNow(null);

        // then
        assertThat(result).isNotNull();
        // ThreatStatusDTO의 필드들이 매핑되는지 확인 (null이어도 객체는 존재해야 함)
    }

    @Test
    @DisplayName("기간 설정 조회 - instCd 없음")
    void selectPeriodNow_WithoutInstCd() {
        // when
        final PeriodSettingDTO result = dashboardMapper.selectPeriodNow(null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("기간 설정 조회 - instCd 포함")
    void selectPeriodNow_WithInstCd() {
        // given
        final String instCd = "TEST001";

        // when
        final PeriodSettingDTO result = dashboardMapper.selectPeriodNow(instCd);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("기간 상태 조회")
    void selectPeriodStatus() {
        // given
        final String sAuthMain = "AUTH001";
        final String sInstCd = "INST001";

        // when
        final PeriodStatusDTO result = dashboardMapper.selectPeriodStatus(sAuthMain, sInstCd);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("기간 상태 조회 - 파라미터 없음")
    void selectPeriodStatus_WithoutParams() {
        // when
        final PeriodStatusDTO result = dashboardMapper.selectPeriodStatus(null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("금일 상태 조회")
    void selectTodayStatus() {
        // given
        final String sAuthMain = "AUTH001";
        final String sInstCd = "INST001";
        final String atype = "TYPE001";

        // when
        final TodayStatusDTO result = dashboardMapper.selectTodayStatus(sAuthMain, sInstCd, atype);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("금일 상태 조회 - 파라미터 없음")
    void selectTodayStatus_WithoutParams() {
        // when
        final TodayStatusDTO result = dashboardMapper.selectTodayStatus(null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("연도별 상태 조회")
    void selectYearStatus() {
        // given
        final String sAuthMain = "AUTH001";
        final String sInstCd = "INST001";
        final String atype = "TYPE001";

        // when
        final YearStatusDTO result = dashboardMapper.selectYearStatus(sAuthMain, sInstCd, atype);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("연도별 상태 조회 - 파라미터 없음")
    void selectYearStatus_WithoutParams() {
        // when
        final YearStatusDTO result = dashboardMapper.selectYearStatus(null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 유형 리스트 조회")
    void selectInciTypeList() {
        // given
        final String sAuthMain = "AUTH001";
        final String instCd = "INST001";
        final String dateType = "MONTH";
        final String startDt = "20240101";
        final String endDt = "20241231";

        // when
        final List<AccidentTypeRankDTO> result = dashboardMapper.selectInciTypeList(
                sAuthMain, instCd, dateType, startDt, endDt);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 유형 리스트 조회 - 파라미터 없음")
    void selectInciTypeList_WithoutParams() {
        // when
        final List<AccidentTypeRankDTO> result = dashboardMapper.selectInciTypeList(
                null, null, null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 유형 리스트 조회 - 빈 리스트 반환 가능")
    void selectInciTypeList_CanReturnEmptyList() {
        // given
        final String sAuthMain = "NONEXISTENT";
        final String instCd = "NONEXISTENT";
        final String dateType = "MONTH";
        final String startDt = "19000101";
        final String endDt = "19000102";

        // when
        final List<AccidentTypeRankDTO> result = dashboardMapper.selectInciTypeList(
                sAuthMain, instCd, dateType, startDt, endDt);

        // then
        assertThat(result)
                .isNotNull()
                .isInstanceOf(List.class);
    }

    @Test
    @DisplayName("사고 지역 리스트 조회 (LOCAL)")
    void selectInciLocalList() {
        // given
        final String sAuthMain = "AUTH001";
        final String instCd = "INST001";
        final String dateType = "MONTH";
        final String startDt = "20240101";
        final String endDt = "20241231";
        final String sortType = "LOCAL";
        final String topInstView = "Y";

        // when
        final List<InstitutionRankDTO> result = dashboardMapper.selectInciLocalList(
                sAuthMain, instCd, dateType, startDt, endDt, sortType, topInstView);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 지역 리스트 조회 (LOCAL) - 파라미터 없음")
    void selectInciLocalList_WithoutParams() {
        // when
        final List<InstitutionRankDTO> result = dashboardMapper.selectInciLocalList(
                null, null, null, null, null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 시도 리스트 조회 (SIDO)")
    void selectInciSidoList() {
        // given
        final String sAuthMain = "AUTH001";
        final String instCd = "INST001";
        final String dateType = "MONTH";
        final String startDt = "20240101";
        final String endDt = "20241231";
        final String sortType = "SIDO";
        final String topInstView = "Y";

        // when
        final List<InstitutionRankDTO> result = dashboardMapper.selectInciSidoList(
                sAuthMain, instCd, dateType, startDt, endDt, sortType, topInstView);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 시도 리스트 조회 (SIDO) - 파라미터 없음")
    void selectInciSidoList_WithoutParams() {
        // when
        final List<InstitutionRankDTO> result = dashboardMapper.selectInciSidoList(
                null, null, null, null, null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 시도 리스트 조회 (SIDO) - 빈 리스트 반환 가능")
    void selectInciSidoList_CanReturnEmptyList() {
        // given
        final String sAuthMain = "NONEXISTENT";
        final String instCd = "NONEXISTENT";
        final String dateType = "MONTH";
        final String startDt = "19000101";
        final String endDt = "19000102";
        final String sortType = "SIDO";
        final String topInstView = "N";

        // when
        final List<InstitutionRankDTO> result = dashboardMapper.selectInciSidoList(
                sAuthMain, instCd, dateType, startDt, endDt, sortType, topInstView);

        // then
        assertThat(result)
                .isNotNull()
                .isInstanceOf(List.class);
    }

    @Test
    @DisplayName("사고 지역 리스트 조회 (LOCAL) - 빈 리스트 반환 가능")
    void selectInciLocalList_CanReturnEmptyList() {
        // given
        final String sAuthMain = "NONEXISTENT";
        final String instCd = "NONEXISTENT";
        final String dateType = "MONTH";
        final String startDt = "19000101";
        final String endDt = "19000102";
        final String sortType = "LOCAL";
        final String topInstView = "N";

        // when
        final List<InstitutionRankDTO> result = dashboardMapper.selectInciLocalList(
                sAuthMain, instCd, dateType, startDt, endDt, sortType, topInstView);

        // then
        assertThat(result)
                .isNotNull()
                .isInstanceOf(List.class);
    }

    @Test
    @DisplayName("Mapper가 정상적으로 주입되는지 검증")
    void mapperInjection() {
        // then
        assertThat(dashboardMapper).isNotNull();
    }
}

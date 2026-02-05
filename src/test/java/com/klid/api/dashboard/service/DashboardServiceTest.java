package com.klid.api.dashboard.service;

import com.klid.api.dashboard.dto.*;
import com.klid.api.dashboard.persistence.DashboardMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock
    private DashboardMapper dashboardMapper;

    private DashboardService service;

    @BeforeEach
    void setUp() {
        service = new DashboardService(dashboardMapper);
    }

    @Nested
    @DisplayName("getThreatStatus()")
    class GetThreatStatus {

        @Test
        @DisplayName("Mapper로부터 예경보 상태를 조회하여 반환한다")
        void returnsThreatStatusFromMapper() {
            final ThreatStatusDTO expected = new ThreatStatusDTO();
            expected.setNowThreat("3");
            expected.setPastThreat("2");
            given(dashboardMapper.selectThreatNow("1100000")).willReturn(expected);

            final ThreatStatusDTO result = service.getThreatStatus("1100000");

            assertNotNull(result);
            assertEquals("3", result.getNowThreat());
            assertEquals("2", result.getPastThreat());
        }

        @Test
        @DisplayName("Mapper가 null을 반환하면 null을 반환한다")
        void returnsNullWhenMapperReturnsNull() {
            given(dashboardMapper.selectThreatNow("1100000")).willReturn(null);

            final ThreatStatusDTO result = service.getThreatStatus("1100000");

            assertNull(result);
        }
    }

    @Nested
    @DisplayName("getPeriodSetting()")
    class GetPeriodSetting {

        @Test
        @DisplayName("Mapper로부터 기간 설정을 조회하여 반환한다")
        void returnsPeriodSettingFromMapper() {
            final PeriodSettingDTO expected = new PeriodSettingDTO();
            expected.setPeriod1(10);
            expected.setPeriod2(20);
            expected.setPeriod3(30);
            given(dashboardMapper.selectPeriodNow("1100000")).willReturn(expected);

            final PeriodSettingDTO result = service.getPeriodSetting("1100000");

            assertNotNull(result);
            assertEquals(10, result.getPeriod1());
            assertEquals(20, result.getPeriod2());
            assertEquals(30, result.getPeriod3());
        }
    }

    @Nested
    @DisplayName("getPeriodStatus()")
    class GetPeriodStatus {

        @Test
        @DisplayName("Mapper로부터 기간별 미처리 건수를 조회하여 반환한다")
        void returnsPeriodStatusFromMapper() {
            final PeriodStatusDTO expected = new PeriodStatusDTO();
            expected.setCnt1(5);
            expected.setCnt2(10);
            expected.setCnt3(15);
            given(dashboardMapper.selectPeriodStatus("AUTH_MAIN_2", "1100000")).willReturn(expected);

            final PeriodStatusDTO result = service.getPeriodStatus("AUTH_MAIN_2", "1100000");

            assertNotNull(result);
            assertEquals(5, result.getCnt1());
            assertEquals(10, result.getCnt2());
            assertEquals(15, result.getCnt3());
        }
    }

    @Nested
    @DisplayName("getTodayStatus()")
    class GetTodayStatus {

        @Test
        @DisplayName("Mapper로부터 금일 처리 현황을 조회하여 반환한다")
        void returnsTodayStatusFromMapper() {
            final TodayStatusDTO expected = new TodayStatusDTO();
            expected.setApply(100);
            expected.setIng(30);
            expected.setEnd(70);
            given(dashboardMapper.selectTodayStatus("AUTH_MAIN_2", "1100000", "1")).willReturn(expected);

            final TodayStatusDTO result = service.getTodayStatus("AUTH_MAIN_2", "1100000", "1");

            assertNotNull(result);
            assertEquals(100, result.getApply());
            assertEquals(30, result.getIng());
            assertEquals(70, result.getEnd());
        }
    }

    @Nested
    @DisplayName("getYearStatus()")
    class GetYearStatus {

        @Test
        @DisplayName("Mapper로부터 연간 처리 현황을 조회하여 반환한다")
        void returnsYearStatusFromMapper() {
            final YearStatusDTO expected = new YearStatusDTO();
            expected.setEnd(500);
            given(dashboardMapper.selectYearStatus("AUTH_MAIN_2", "1100000", "1")).willReturn(expected);

            final YearStatusDTO result = service.getYearStatus("AUTH_MAIN_2", "1100000", "1");

            assertNotNull(result);
            assertEquals(500, result.getEnd());
        }
    }

    @Nested
    @DisplayName("getAccidentTypeTop5()")
    class GetAccidentTypeTop5 {

        @Test
        @DisplayName("Mapper로부터 사고 유형별 Top5를 조회하여 반환한다")
        void returnsAccidentTypeTop5FromMapper() {
            final List<AccidentTypeRankDTO> expected = Arrays.asList(
                    createAccidentTypeRankDTO("랜섬웨어", 50),
                    createAccidentTypeRankDTO("피싱", 30)
            );
            given(dashboardMapper.selectInciTypeList("AUTH_MAIN_2", "1100000", "inci_acpn_dt", "20240101000000", "20240131235959"))
                    .willReturn(expected);

            final List<AccidentTypeRankDTO> result = service.getAccidentTypeTop5(
                    "AUTH_MAIN_2", "1100000", "inci_acpn_dt", "20240101000000", "20240131235959");

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("랜섬웨어", result.get(0).getName());
        }
    }

    @Nested
    @DisplayName("getInstitutionTop5()")
    class GetInstitutionTop5 {

        @Test
        @DisplayName("sortType이 SIDO이면 selectInciSidoList를 호출한다")
        void callsSelectInciSidoListWhenSortTypeIsSido() {
            final List<InstitutionRankDTO> expected = Arrays.asList(
                    createInstitutionRankDTO("강남구", 50),
                    createInstitutionRankDTO("서초구", 30)
            );
            given(dashboardMapper.selectInciSidoList("AUTH_MAIN_3", "1100000", "inci_acpn_dt", "20240101000000", "20240131235959", "SIDO", "main"))
                    .willReturn(expected);

            final List<InstitutionRankDTO> result = service.getInstitutionTop5(
                    "AUTH_MAIN_3", "1100000", "inci_acpn_dt", "20240101000000", "20240131235959", "SIDO", "main");

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("강남구", result.get(0).getName());
        }

        @Test
        @DisplayName("sortType이 SIDO가 아니면 selectInciLocalList를 호출한다")
        void callsSelectInciLocalListWhenSortTypeIsNotSido() {
            final List<InstitutionRankDTO> expected = Arrays.asList(
                    createInstitutionRankDTO("서울", 100),
                    createInstitutionRankDTO("경기", 80)
            );
            given(dashboardMapper.selectInciLocalList("AUTH_MAIN_2", "1100000", "inci_acpn_dt", "20240101000000", "20240131235959", "main", null))
                    .willReturn(expected);

            final List<InstitutionRankDTO> result = service.getInstitutionTop5(
                    "AUTH_MAIN_2", "1100000", "inci_acpn_dt", "20240101000000", "20240131235959", "main", null);

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("서울", result.get(0).getName());
        }
    }

    private AccidentTypeRankDTO createAccidentTypeRankDTO(final String name, final int count) {
        final AccidentTypeRankDTO dto = new AccidentTypeRankDTO();
        dto.setName(name);
        dto.setY(count);
        return dto;
    }

    private InstitutionRankDTO createInstitutionRankDTO(final String name, final int count) {
        final InstitutionRankDTO dto = new InstitutionRankDTO();
        dto.setName(name);
        dto.setY(count);
        return dto;
    }
}

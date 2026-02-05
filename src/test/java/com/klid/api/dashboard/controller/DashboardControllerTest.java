package com.klid.api.dashboard.controller;

import com.klid.api.dashboard.dto.*;
import com.klid.api.dashboard.service.DashboardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DashboardControllerTest {

    @Mock
    private DashboardService dashboardService;

    private DashboardController controller;

    @BeforeEach
    void setUp() {
        controller = new DashboardController(dashboardService);
    }

    @Nested
    @DisplayName("getThreatStatus()")
    class GetThreatStatus {

        @Test
        @DisplayName("예경보 현재 상태를 정상적으로 반환한다")
        void returnsThreatStatusSuccessfully() {
            final ThreatStatusDTO dto = new ThreatStatusDTO();
            dto.setNowThreat("3");
            dto.setPastThreat("2");
            dto.setNowNm("경계");
            dto.setPastNm("주의");
            given(dashboardService.getThreatStatus("1100000")).willReturn(dto);

            final ResponseEntity<ThreatStatusDTO> response = controller.getThreatStatus("1100000");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals("3", response.getBody().getNowThreat());
            assertEquals("경계", response.getBody().getNowNm());
        }

        @Test
        @DisplayName("instCd가 null이어도 정상 처리한다")
        void handlesNullInstCd() {
            given(dashboardService.getThreatStatus(null)).willReturn(new ThreatStatusDTO());

            final ResponseEntity<ThreatStatusDTO> response = controller.getThreatStatus(null);

            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

    @Nested
    @DisplayName("getPeriodSetting()")
    class GetPeriodSetting {

        @Test
        @DisplayName("미처리 기간 설정을 정상적으로 반환한다")
        void returnsPeriodSettingSuccessfully() {
            final PeriodSettingDTO dto = new PeriodSettingDTO();
            dto.setPeriod1(10);
            dto.setPeriod2(20);
            dto.setPeriod3(30);
            dto.setInstCd("1100000");
            given(dashboardService.getPeriodSetting("1100000")).willReturn(dto);

            final ResponseEntity<PeriodSettingDTO> response = controller.getPeriodSetting("1100000");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(10, response.getBody().getPeriod1());
            assertEquals(20, response.getBody().getPeriod2());
            assertEquals(30, response.getBody().getPeriod3());
        }
    }

    @Nested
    @DisplayName("getPeriodStatus()")
    class GetPeriodStatus {

        @Test
        @DisplayName("기간별 미처리 건수를 정상적으로 반환한다")
        void returnsPeriodStatusSuccessfully() {
            final PeriodStatusDTO dto = new PeriodStatusDTO();
            dto.setCnt1(5);
            dto.setCnt2(10);
            dto.setCnt3(15);
            given(dashboardService.getPeriodStatus("AUTH_MAIN_2", "1100000")).willReturn(dto);

            final ResponseEntity<PeriodStatusDTO> response = controller.getPeriodStatus("AUTH_MAIN_2", "1100000");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(5, response.getBody().getCnt1());
            assertEquals(10, response.getBody().getCnt2());
            assertEquals(15, response.getBody().getCnt3());
        }
    }

    @Nested
    @DisplayName("getTodayStatus()")
    class GetTodayStatus {

        @Test
        @DisplayName("금일 처리 현황을 정상적으로 반환한다")
        void returnsTodayStatusSuccessfully() {
            final TodayStatusDTO dto = new TodayStatusDTO();
            dto.setApply(100);
            dto.setIng(30);
            dto.setEnd(70);
            given(dashboardService.getTodayStatus("AUTH_MAIN_2", "1100000", "1")).willReturn(dto);

            final ResponseEntity<TodayStatusDTO> response = controller.getTodayStatus("AUTH_MAIN_2", "1100000", "1");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(100, response.getBody().getApply());
            assertEquals(30, response.getBody().getIng());
            assertEquals(70, response.getBody().getEnd());
        }
    }

    @Nested
    @DisplayName("getYearStatus()")
    class GetYearStatus {

        @Test
        @DisplayName("연간 처리 현황을 정상적으로 반환한다")
        void returnsYearStatusSuccessfully() {
            final YearStatusDTO dto = new YearStatusDTO();
            dto.setEnd(500);
            given(dashboardService.getYearStatus("AUTH_MAIN_2", "1100000", "1")).willReturn(dto);

            final ResponseEntity<YearStatusDTO> response = controller.getYearStatus("AUTH_MAIN_2", "1100000", "1");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(500, response.getBody().getEnd());
        }
    }

    @Nested
    @DisplayName("getAccidentTypeTop5()")
    class GetAccidentTypeTop5 {

        @Test
        @DisplayName("사고 유형별 Top5를 정상적으로 반환한다")
        void returnsAccidentTypeTop5Successfully() {
            final List<AccidentTypeRankDTO> dtoList = Arrays.asList(
                    createAccidentTypeRankDTO("랜섬웨어", 50),
                    createAccidentTypeRankDTO("피싱", 30),
                    createAccidentTypeRankDTO("DDoS", 20)
            );
            given(dashboardService.getAccidentTypeTop5("AUTH_MAIN_2", "1100000", "inci_acpn_dt", "20240101000000", "20240131235959"))
                    .willReturn(dtoList);

            final ResponseEntity<List<AccidentTypeRankDTO>> response = controller.getAccidentTypeTop5(
                    "AUTH_MAIN_2", "1100000", "inci_acpn_dt", "20240101000000", "20240131235959");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(3, response.getBody().size());
            assertEquals("랜섬웨어", response.getBody().get(0).getName());
            assertEquals(50, response.getBody().get(0).getY());
        }

        @Test
        @DisplayName("결과가 없으면 빈 리스트를 반환한다")
        void returnsEmptyListWhenNoData() {
            given(dashboardService.getAccidentTypeTop5("AUTH_MAIN_2", "1100000", "inci_acpn_dt", "20240101000000", "20240131235959"))
                    .willReturn(Collections.emptyList());

            final ResponseEntity<List<AccidentTypeRankDTO>> response = controller.getAccidentTypeTop5(
                    "AUTH_MAIN_2", "1100000", "inci_acpn_dt", "20240101000000", "20240131235959");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertTrue(response.getBody().isEmpty());
        }
    }

    @Nested
    @DisplayName("getInstitutionTop5()")
    class GetInstitutionTop5 {

        @Test
        @DisplayName("기관별 Top5를 정상적으로 반환한다")
        void returnsInstitutionTop5Successfully() {
            final List<InstitutionRankDTO> dtoList = Arrays.asList(
                    createInstitutionRankDTO("서울", 100, "1100000"),
                    createInstitutionRankDTO("경기", 80, "4100000"),
                    createInstitutionRankDTO("부산", 60, "2600000")
            );
            given(dashboardService.getInstitutionTop5("AUTH_MAIN_2", "1100000", "inci_acpn_dt", "20240101000000", "20240131235959", "main", null))
                    .willReturn(dtoList);

            final ResponseEntity<List<InstitutionRankDTO>> response = controller.getInstitutionTop5(
                    "AUTH_MAIN_2", "1100000", "inci_acpn_dt", "20240101000000", "20240131235959", "main", null);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(3, response.getBody().size());
            assertEquals("서울", response.getBody().get(0).getName());
        }
    }

    private AccidentTypeRankDTO createAccidentTypeRankDTO(final String name, final int count) {
        final AccidentTypeRankDTO dto = new AccidentTypeRankDTO();
        dto.setName(name);
        dto.setY(count);
        return dto;
    }

    private InstitutionRankDTO createInstitutionRankDTO(final String name, final int count, final String instCd) {
        final InstitutionRankDTO dto = new InstitutionRankDTO();
        dto.setName(name);
        dto.setY(count);
        dto.setInstCd(instCd);
        return dto;
    }
}

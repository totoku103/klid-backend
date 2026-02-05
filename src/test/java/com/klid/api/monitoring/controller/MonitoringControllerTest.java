package com.klid.api.monitoring.controller;

import com.klid.api.monitoring.dto.MonitoringDetailDTO;
import com.klid.api.monitoring.dto.MonitoringStatsDTO;
import com.klid.api.monitoring.service.MonitoringService;
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
class MonitoringControllerTest {

    @Mock
    private MonitoringService monitoringService;

    private MonitoringController controller;

    @BeforeEach
    void setUp() {
        controller = new MonitoringController(monitoringService);
    }

    @Nested
    @DisplayName("getMonitoringStats()")
    class GetMonitoringStats {

        @Test
        @DisplayName("홈페이지 모니터링 통계를 정상적으로 반환한다")
        void returnsMonitoringStatsSuccessfully() {
            final MonitoringStatsDTO dto = new MonitoringStatsDTO();
            dto.setHealthNormalCnt(100);
            dto.setHealthErrCnt(5);
            dto.setUrlNormalCnt(200);
            dto.setUrlErrCnt(3);
            given(monitoringService.getMonitoringStats("1100000", "AUTH_MAIN_2")).willReturn(dto);

            final ResponseEntity<MonitoringStatsDTO> response = controller.getMonitoringStats("1100000", "AUTH_MAIN_2");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(100, response.getBody().getHealthNormalCnt());
            assertEquals(5, response.getBody().getHealthErrCnt());
            assertEquals(200, response.getBody().getUrlNormalCnt());
            assertEquals(3, response.getBody().getUrlErrCnt());
        }

        @Test
        @DisplayName("선택적 파라미터가 null이어도 정상 처리한다")
        void handlesNullParameters() {
            given(monitoringService.getMonitoringStats(null, null)).willReturn(new MonitoringStatsDTO());

            final ResponseEntity<MonitoringStatsDTO> response = controller.getMonitoringStats(null, null);

            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

    @Nested
    @DisplayName("getMonitoringDetail()")
    class GetMonitoringDetail {

        @Test
        @DisplayName("홈페이지 모니터링 상세를 정상적으로 반환한다")
        void returnsMonitoringDetailSuccessfully() {
            final List<MonitoringDetailDTO> dtoList = Arrays.asList(
                    createMonitoringDetailDTO("서울", "1100000", 0, 0),
                    createMonitoringDetailDTO("경기", "4100000", 1, 0)
            );
            given(monitoringService.getMonitoringDetail("1100000", "20240115120000", "20240115121000"))
                    .willReturn(dtoList);

            final ResponseEntity<List<MonitoringDetailDTO>> response = controller.getMonitoringDetail(
                    "1100000", "20240115120000", "20240115121000");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(2, response.getBody().size());
            assertEquals("서울", response.getBody().get(0).getInstNm());
            assertEquals(0, response.getBody().get(0).getHmCnt());
        }

        @Test
        @DisplayName("장애가 있는 기관을 올바르게 반환한다")
        void returnsInstitutionWithError() {
            final List<MonitoringDetailDTO> dtoList = Arrays.asList(
                    createMonitoringDetailDTO("경기", "4100000", 1, 1)
            );
            given(monitoringService.getMonitoringDetail("1100000", "20240115120000", "20240115121000"))
                    .willReturn(dtoList);

            final ResponseEntity<List<MonitoringDetailDTO>> response = controller.getMonitoringDetail(
                    "1100000", "20240115120000", "20240115121000");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(1, response.getBody().get(0).getHmCnt());
            assertEquals(1, response.getBody().get(0).getFoCnt());
        }

        @Test
        @DisplayName("결과가 없으면 빈 리스트를 반환한다")
        void returnsEmptyListWhenNoData() {
            given(monitoringService.getMonitoringDetail("1100000", "20240115120000", "20240115121000"))
                    .willReturn(Collections.emptyList());

            final ResponseEntity<List<MonitoringDetailDTO>> response = controller.getMonitoringDetail(
                    "1100000", "20240115120000", "20240115121000");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.getBody().isEmpty());
        }
    }

    private MonitoringDetailDTO createMonitoringDetailDTO(final String instNm, final String instCd,
                                                           final Integer hmCnt, final Integer foCnt) {
        final MonitoringDetailDTO dto = new MonitoringDetailDTO();
        dto.setInstNm(instNm);
        dto.setInstCd(instCd);
        dto.setHmCnt(hmCnt);
        dto.setFoCnt(foCnt);
        return dto;
    }
}

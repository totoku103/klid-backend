package com.klid.api.monitoring.service;

import com.klid.api.monitoring.dto.MonitoringDetailDTO;
import com.klid.api.monitoring.dto.MonitoringStatsDTO;
import com.klid.api.monitoring.persistence.MonitoringMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MonitoringServiceTest {

    @Mock
    private MonitoringMapper monitoringMapper;

    private MonitoringService service;

    @BeforeEach
    void setUp() {
        service = new MonitoringService(monitoringMapper);
    }

    @Nested
    @DisplayName("getMonitoringStats()")
    class GetMonitoringStats {

        @Test
        @DisplayName("Mapper로부터 모니터링 통계를 조회하여 반환한다")
        void returnsMonitoringStatsFromMapper() {
            final MonitoringStatsDTO expected = new MonitoringStatsDTO();
            expected.setHealthNormalCnt(100);
            expected.setHealthErrCnt(5);
            expected.setUrlNormalCnt(200);
            expected.setUrlErrCnt(3);
            given(monitoringMapper.selectMainForgeryCnt("1100000", "AUTH_MAIN_2")).willReturn(expected);

            final MonitoringStatsDTO result = service.getMonitoringStats("1100000", "AUTH_MAIN_2");

            assertNotNull(result);
            assertEquals(100, result.getHealthNormalCnt());
            assertEquals(5, result.getHealthErrCnt());
        }

        @Test
        @DisplayName("Mapper가 null을 반환하면 null을 반환한다")
        void returnsNullWhenMapperReturnsNull() {
            given(monitoringMapper.selectMainForgeryCnt("1100000", "AUTH_MAIN_2")).willReturn(null);

            final MonitoringStatsDTO result = service.getMonitoringStats("1100000", "AUTH_MAIN_2");

            assertNull(result);
        }
    }

    @Nested
    @DisplayName("getMonitoringDetail()")
    class GetMonitoringDetail {

        @Test
        @DisplayName("Mapper로부터 모니터링 상세를 조회하여 반환한다")
        void returnsMonitoringDetailFromMapper() {
            final List<MonitoringDetailDTO> expected = Arrays.asList(
                    createMonitoringDetailDTO("서울", "1100000"),
                    createMonitoringDetailDTO("경기", "4100000")
            );
            given(monitoringMapper.selectMainForgeryHm("1100000", "20240115120000", "20240115121000"))
                    .willReturn(expected);

            final List<MonitoringDetailDTO> result = service.getMonitoringDetail(
                    "1100000", "20240115120000", "20240115121000");

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("서울", result.get(0).getInstNm());
        }

        @Test
        @DisplayName("Mapper가 빈 리스트를 반환하면 빈 리스트를 반환한다")
        void returnsEmptyListWhenMapperReturnsEmptyList() {
            given(monitoringMapper.selectMainForgeryHm("1100000", "20240115120000", "20240115121000"))
                    .willReturn(Collections.emptyList());

            final List<MonitoringDetailDTO> result = service.getMonitoringDetail(
                    "1100000", "20240115120000", "20240115121000");

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    private MonitoringDetailDTO createMonitoringDetailDTO(final String instNm, final String instCd) {
        final MonitoringDetailDTO dto = new MonitoringDetailDTO();
        dto.setInstNm(instNm);
        dto.setInstCd(instCd);
        return dto;
    }
}

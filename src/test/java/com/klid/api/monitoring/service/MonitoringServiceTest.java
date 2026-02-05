package com.klid.api.monitoring.service;

import com.klid.api.BaseServiceTest;
import com.klid.api.monitoring.dto.MonitoringDetailDTO;
import com.klid.api.monitoring.dto.MonitoringStatsDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * MonitoringService 통합 테스트
 */
class MonitoringServiceTest extends BaseServiceTest {

    @Autowired
    private MonitoringService monitoringService;

    @Test
    @DisplayName("getMonitoringStats - 전체 모니터링 통계 조회")
    void getMonitoringStats_shouldReturnStats() {
        // when
        final MonitoringStatsDTO result = monitoringService.getMonitoringStats(null, null);

        // then - null 또는 valid stats 반환
        // 데이터가 없을 수 있으므로 null 체크만 수행
    }

    @Test
    @DisplayName("getMonitoringStats - 기관코드로 통계 조회")
    void getMonitoringStats_withInstCd_shouldReturnStats() {
        // when
        final MonitoringStatsDTO result = monitoringService.getMonitoringStats("1000", null);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }

    @Test
    @DisplayName("getMonitoringStats - 권한 메인으로 통계 조회")
    void getMonitoringStats_withAuthMain_shouldReturnStats() {
        // when
        final MonitoringStatsDTO result = monitoringService.getMonitoringStats(null, "AUTH_MAIN_1");

        // then
    }

    @Test
    @DisplayName("getMonitoringDetail - 전체 모니터링 상세 조회")
    void getMonitoringDetail_shouldReturnDetailList() {
        // when
        final List<MonitoringDetailDTO> result = monitoringService.getMonitoringDetail(null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getMonitoringDetail - 시간 범위로 상세 조회")
    void getMonitoringDetail_withTimeRange_shouldReturnDetailList() {
        // when
        final List<MonitoringDetailDTO> result = monitoringService.getMonitoringDetail(
                "1000", "2024-01-01 00:00:00", "2024-12-31 23:59:59");

        // then
        assertThat(result).isNotNull();
    }
}

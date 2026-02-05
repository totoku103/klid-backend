package com.klid.api.monitoring.persistence;

import com.klid.api.BaseMapperTest;
import com.klid.api.monitoring.dto.MonitoringDetailDTO;
import com.klid.api.monitoring.dto.MonitoringStatsDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * MonitoringMapper 통합 테스트
 */
class MonitoringMapperTest extends BaseMapperTest {

    @Autowired
    private MonitoringMapper monitoringMapper;

    @Test
    @DisplayName("selectMainForgeryCnt - 전체 조회")
    void selectMainForgeryCnt_shouldReturnStats() {
        // when
        final MonitoringStatsDTO result = monitoringMapper.selectMainForgeryCnt(null, null);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }

    @Test
    @DisplayName("selectMainForgeryCnt - 기관코드로 조회")
    void selectMainForgeryCnt_withInstCd_shouldReturnStats() {
        // when
        final MonitoringStatsDTO result = monitoringMapper.selectMainForgeryCnt("1000", null);

        // then
    }

    @Test
    @DisplayName("selectMainForgeryCnt - 권한 메인으로 조회")
    void selectMainForgeryCnt_withAuthMain_shouldReturnStats() {
        // when
        final MonitoringStatsDTO result = monitoringMapper.selectMainForgeryCnt(null, "AUTH_MAIN_1");

        // then
    }

    @Test
    @DisplayName("selectMainForgeryHm - 전체 조회")
    void selectMainForgeryHm_shouldReturnDetailList() {
        // when
        final List<MonitoringDetailDTO> result = monitoringMapper.selectMainForgeryHm(null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectMainForgeryHm - 시간 범위로 조회")
    void selectMainForgeryHm_withTimeRange_shouldReturnDetailList() {
        // when
        final List<MonitoringDetailDTO> result = monitoringMapper.selectMainForgeryHm(
                "1000", "2024-01-01 00:00:00", "2024-12-31 23:59:59");

        // then
        assertThat(result).isNotNull();
    }
}

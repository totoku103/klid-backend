package com.klid.api.monitoring.health.service;

import com.klid.api.BaseServiceTest;
import com.klid.api.monitoring.health.dto.HealthCheckUrlDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * HealthCheckUrlService 통합 테스트
 */
class HealthCheckUrlServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiHealthCheckUrlService")
    private HealthCheckUrlService healthCheckUrlService;

    @Test
    @DisplayName("getHealthCheckUrl - 전체 목록 조회")
    void getHealthCheckUrl_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<HealthCheckUrlDTO> result = healthCheckUrlService.getHealthCheckUrl(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getHealthCheckUrl - 조건부 목록 조회")
    void getHealthCheckUrl_withCondition_shouldReturnFilteredList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("useYn", 1);

        // when
        final List<HealthCheckUrlDTO> result = healthCheckUrlService.getHealthCheckUrl(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getDetailHealthCheckUrl - 상세 조회")
    void getDetailHealthCheckUrl_shouldReturnDetail() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("seqNo", 1);

        // when
        final Map<String, Object> result = healthCheckUrlService.getDetailHealthCheckUrl(params);

        // then
        assertThat(result).isNotNull();
        assertThat(result).containsKey("contents");
    }

    @Test
    @DisplayName("getHealthCheckHist - 장애이력 목록 조회")
    void getHealthCheckHist_shouldReturnHistoryList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<HealthCheckUrlDTO> result = healthCheckUrlService.getHealthCheckHist(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getHealthCheckStat - 상태 통계 조회")
    void getHealthCheckStat_shouldReturnStatistics() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<Map<String, Object>> result = healthCheckUrlService.getHealthCheckStat(params);

        // then
        assertThat(result).isNotNull();
    }
}

package com.klid.api.monitoring.health.persistence;

import com.klid.api.BaseMapperTest;
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
 * HealthCheckUrlMapper 통합 테스트
 */
class HealthCheckUrlMapperTest extends BaseMapperTest {

    @Autowired
    @Qualifier("apiHealthCheckUrlMapper")
    private HealthCheckUrlMapper healthCheckUrlMapper;

    @Test
    @DisplayName("selectHealthCheckUrl - 전체 목록 조회")
    void selectHealthCheckUrl_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<HealthCheckUrlDTO> result = healthCheckUrlMapper.selectHealthCheckUrl(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectHealthCheckUrl - 조건부 조회")
    void selectHealthCheckUrl_withCondition_shouldReturnFilteredList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("useYn", 1);

        // when
        final List<HealthCheckUrlDTO> result = healthCheckUrlMapper.selectHealthCheckUrl(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectDetailHealthCheckUrl - 상세 조회")
    void selectDetailHealthCheckUrl_shouldReturnDetail() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("seqNo", 1);

        // when
        final HealthCheckUrlDTO result = healthCheckUrlMapper.selectDetailHealthCheckUrl(params);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }

    @Test
    @DisplayName("selectHealthCheckHist - 장애이력 조회")
    void selectHealthCheckHist_shouldReturnHistoryList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<HealthCheckUrlDTO> result = healthCheckUrlMapper.selectHealthCheckHist(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectHealthCheckStat - 상태 통계 조회")
    void selectHealthCheckStat_shouldReturnStatistics() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<Map<String, Object>> result = healthCheckUrlMapper.selectHealthCheckStat(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectUrls - URL 목록 조회 (중복체크용)")
    void selectUrls_shouldReturnUrlList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<HealthCheckUrlDTO> result = healthCheckUrlMapper.selectUrls(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectRelateInstCd - 관련 기관 코드 조회")
    void selectRelateInstCd_shouldReturnInstCdList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<Integer> result = healthCheckUrlMapper.selectRelateInstCd(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectLastSeq - 마지막 시퀀스 조회")
    void selectLastSeq_shouldReturnLastSequence() {
        // when
        final int result = healthCheckUrlMapper.selectLastSeq();

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }
}

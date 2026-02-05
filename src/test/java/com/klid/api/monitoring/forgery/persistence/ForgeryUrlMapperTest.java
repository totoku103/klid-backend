package com.klid.api.monitoring.forgery.persistence;

import com.klid.api.BaseMapperTest;
import com.klid.api.monitoring.forgery.dto.ForgeryUrlDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ForgeryUrlMapper 통합 테스트
 */
class ForgeryUrlMapperTest extends BaseMapperTest {

    @Autowired
    @Qualifier("apiForgeryUrlMapper")
    private ForgeryUrlMapper forgeryUrlMapper;

    @Test
    @DisplayName("selectForgeryUrl - 위변조 URL 목록 조회")
    void selectForgeryUrl_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<Map<String, Object>> result = forgeryUrlMapper.selectForgeryUrl(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectForgeryUrlHist - 위변조 URL 이력 조회")
    void selectForgeryUrlHist_shouldReturnHistoryList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<Map<String, Object>> result = forgeryUrlMapper.selectForgeryUrlHist(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectMainForgeryHm - 메인 홈페이지 모니터링 조회")
    void selectMainForgeryHm_shouldReturnMonitoringList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<ForgeryUrlDTO> result = forgeryUrlMapper.selectMainForgeryHm(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectMainForgeryCnt - 메인 홈페이지 모니터링 수치 통계")
    void selectMainForgeryCnt_shouldReturnStatistics() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final ForgeryUrlDTO result = forgeryUrlMapper.selectMainForgeryCnt(params);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }

    @Test
    @DisplayName("getByInstNm - 기관명으로 조회")
    void getByInstNm_shouldReturnByInstitution() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("instNm", "테스트기관");

        // when
        final ForgeryUrlDTO result = forgeryUrlMapper.getByInstNm(params);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }
}

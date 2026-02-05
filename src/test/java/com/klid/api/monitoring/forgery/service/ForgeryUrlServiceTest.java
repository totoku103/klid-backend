package com.klid.api.monitoring.forgery.service;

import com.klid.api.BaseServiceTest;
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
 * ForgeryUrlService 통합 테스트
 */
class ForgeryUrlServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiForgeryUrlService")
    private ForgeryUrlService forgeryUrlService;

    @Test
    @DisplayName("getForgeryUrl - 전체 위변조 URL 목록 조회")
    void getForgeryUrl_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<Map<String, Object>> result = forgeryUrlService.getForgeryUrl(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getForgeryUrlHist - 위변조 URL 이력 목록 조회")
    void getForgeryUrlHist_shouldReturnHistoryList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<Map<String, Object>> result = forgeryUrlService.getForgeryUrlHist(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getMainForgeryHm - 메인 홈페이지 모니터링 조회")
    void getMainForgeryHm_shouldReturnMonitoringList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<ForgeryUrlDTO> result = forgeryUrlService.getMainForgeryHm(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getMainForgeryCnt - 메인 홈페이지 모니터링 수치 통계")
    void getMainForgeryCnt_shouldReturnStatistics() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Map<String, Object> result = forgeryUrlService.getMainForgeryCnt(params);

        // then
        assertThat(result).isNotNull();
        assertThat(result).containsKey("contents");
    }

    @Test
    @DisplayName("getByInstNm - 기관명으로 조회")
    void getByInstNm_shouldReturnByInstitution() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("instNm", "테스트기관");

        // when
        final ForgeryUrlDTO result = forgeryUrlService.getByInstNm(params);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }
}

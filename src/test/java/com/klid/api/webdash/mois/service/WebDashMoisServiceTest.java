package com.klid.api.webdash.mois.service;

import com.klid.api.BaseServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * WebDashMoisService 통합 테스트
 */
class WebDashMoisServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiWebDashMoisService")
    private WebDashMoisService webDashMoisService;

    @Test
    @DisplayName("getThreatNow - 현재 위협 조회")
    void getThreatNow_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisService.getThreatNow(params);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }

    @Test
    @DisplayName("getHmHcUrlCenter - 센터 HM/HC URL 조회")
    void getHmHcUrlCenter_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisService.getHmHcUrlCenter(params);

        // then
    }

    @Test
    @DisplayName("getHmHcUrlRegion - 지역별 HM/HC URL 조회")
    void getHmHcUrlRegion_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisService.getHmHcUrlRegion(params);

        // then
    }

    @Test
    @DisplayName("getForgeryRegion - 지역별 위변조 조회")
    void getForgeryRegion_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisService.getForgeryRegion(params);

        // then
    }

    @Test
    @DisplayName("getRegionStatus - 지역 현황 조회")
    void getRegionStatus_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisService.getRegionStatus(params);

        // then
    }

    @Test
    @DisplayName("getRegionStatusAuto - 자동 지역 현황 조회")
    void getRegionStatusAuto_shouldReturnSum() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Integer result = webDashMoisService.getRegionStatusAuto(params);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("getRegionStatusManual - 수동 지역 현황 조회")
    void getRegionStatusManual_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisService.getRegionStatusManual(params);

        // then
    }

    @Test
    @DisplayName("getDashConfigList - 대시보드 설정 목록 조회")
    void getDashConfigList_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisService.getDashConfigList(params);

        // then
    }

    @Test
    @DisplayName("getDashChartSum - 대시보드 차트 합계 조회")
    void getDashChartSum_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisService.getDashChartSum(params);

        // then
    }
}

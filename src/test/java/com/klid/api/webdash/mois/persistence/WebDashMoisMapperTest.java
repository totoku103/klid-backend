package com.klid.api.webdash.mois.persistence;

import com.klid.api.BaseMapperTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * WebDashMoisMapper 통합 테스트
 */
class WebDashMoisMapperTest extends BaseMapperTest {

    @Autowired
    @Qualifier("apiWebDashMoisMapper")
    private WebDashMoisMapper webDashMoisMapper;

    @Test
    @DisplayName("getThreatNow - 현재 위협 조회")
    void getThreatNow_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisMapper.getThreatNow(params);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }

    @Test
    @DisplayName("getHmHcUrlCenter - 센터 HM/HC URL 조회")
    void getHmHcUrlCenter_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisMapper.getHmHcUrlCenter(params);

        // then
    }

    @Test
    @DisplayName("getHmHcUrlRegion - 지역별 HM/HC URL 조회")
    void getHmHcUrlRegion_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisMapper.getHmHcUrlRegion(params);

        // then
    }

    @Test
    @DisplayName("getForgeryRegion - 지역별 위변조 조회")
    void getForgeryRegion_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisMapper.getForgeryRegion(params);

        // then
    }

    @Test
    @DisplayName("getRegionStatus - 지역 현황 조회")
    void getRegionStatus_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisMapper.getRegionStatus(params);

        // then
    }

    @Test
    @DisplayName("getRegionStatusAuto - 자동 지역 현황 조회")
    void getRegionStatusAuto_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<String> result = webDashMoisMapper.getRegionStatusAuto(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getRegionStatusManual - 수동 지역 현황 조회")
    void getRegionStatusManual_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisMapper.getRegionStatusManual(params);

        // then
    }

    @Test
    @DisplayName("getDashConfigList - 대시보드 설정 목록 조회")
    void getDashConfigList_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisMapper.getDashConfigList(params);

        // then
    }

    @Test
    @DisplayName("getDashChartSum - 대시보드 차트 합계 조회")
    void getDashChartSum_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashMoisMapper.getDashChartSum(params);

        // then
    }
}

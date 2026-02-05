package com.klid.api.webdash.center.service;

import com.klid.api.BaseServiceTest;
import com.klid.api.webdash.center.dto.WebDashCenterDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * WebDashCenterService 통합 테스트
 */
class WebDashCenterServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiWebDashCenterService")
    private WebDashCenterService webDashCenterService;

    @Test
    @DisplayName("getAttNationTop5 - 공격국가 Top5 조회")
    void getAttNationTop5_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final List<WebDashCenterDTO> result = webDashCenterService.getAttNationTop5(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getTypeChart - 유형별 차트 데이터 조회")
    void getTypeChart_shouldReturnChartData() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final List<Map<String, Integer>> result = webDashCenterService.getTypeChart(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getEvtChart - 이벤트 차트 데이터 조회")
    void getEvtChart_shouldReturnChartData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Map<String, List<Map<String, Integer>>> result = webDashCenterService.getEvtChart(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getEvtAllChart - 전체 이벤트 차트 데이터 조회")
    void getEvtAllChart_shouldReturnChartData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Map<String, List<Map<String, Integer>>> result = webDashCenterService.getEvtAllChart(params);

        // then
        assertThat(result).isNotNull();
    }
}

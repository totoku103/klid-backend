package com.klid.api.webdash.center.persistence;

import com.klid.api.BaseMapperTest;
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
 * WebDashCenterMapper 통합 테스트
 */
class WebDashCenterMapperTest extends BaseMapperTest {

    @Autowired
    @Qualifier("apiWebDashCenterMapper")
    private WebDashCenterMapper webDashCenterMapper;

    @Test
    @DisplayName("selectAttNationTop5 - 공격국가 Top5 조회")
    void selectAttNationTop5_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final List<WebDashCenterDTO> result = webDashCenterMapper.selectAttNationTop5(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectTypeChart - 유형별 차트 데이터 조회")
    void selectTypeChart_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final List<WebDashCenterDTO> result = webDashCenterMapper.selectTypeChart(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectEvtChart - 이벤트 차트 데이터 조회")
    void selectEvtChart_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<WebDashCenterDTO> result = webDashCenterMapper.selectEvtChart(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectEvtAllChart - 전체 이벤트 차트 데이터 조회")
    void selectEvtAllChart_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<WebDashCenterDTO> result = webDashCenterMapper.selectEvtAllChart(params);

        // then
        assertThat(result).isNotNull();
    }
}

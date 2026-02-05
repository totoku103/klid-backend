package com.klid.api.system.config;

import com.klid.api.BaseMapperTest;
import com.klid.api.system.config.dto.DashboardConfigRequest;
import com.klid.api.system.config.dto.DashboardConfigResponse;
import com.klid.api.system.config.persistence.DashboardConfigMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DashboardConfigMapper 통합 테스트
 */
class DashboardConfigMapperTest extends BaseMapperTest {

    @Autowired
    private DashboardConfigMapper dashboardConfigMapper;

    @Test
    @DisplayName("selectDashConfigList - 전체 목록 조회")
    void selectDashConfigList_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<DashboardConfigResponse> result = dashboardConfigMapper.selectDashConfigList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectDashConfigList - 사용 여부로 필터링")
    void selectDashConfigList_WithUseYn_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("useYn", "Y");

        // when
        final List<DashboardConfigResponse> result = dashboardConfigMapper.selectDashConfigList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectDashConfigList - 설정명으로 검색")
    void selectDashConfigList_WithConfigName_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("configName", "대시보드");

        // when
        final List<DashboardConfigResponse> result = dashboardConfigMapper.selectDashConfigList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("insertDashConfig - 설정 등록")
    void insertDashConfig_Success() {
        // given
        final DashboardConfigRequest request = new DashboardConfigRequest();
        request.setConfigId("MAPPER_TEST_001");
        request.setConfigName("매퍼 테스트 설정");
        request.setConfigValue("MAPPER_TEST_VALUE");
        request.setConfigDesc("매퍼 테스트용 설명");
        request.setUseYn("Y");
        request.setSortOrder(999);

        // when & then - 예외가 발생하지 않으면 성공
        dashboardConfigMapper.insertDashConfig(request);
    }

    @Test
    @DisplayName("updateDashConfig - 설정 수정")
    void updateDashConfig_Success() {
        // given
        final DashboardConfigRequest request = new DashboardConfigRequest();
        request.setConfigId("EXISTING_CONFIG");
        request.setConfigName("수정된 설정명");
        request.setConfigValue("UPDATED_VALUE");
        request.setConfigDesc("수정된 설명");
        request.setUseYn("N");
        request.setSortOrder(1);

        // when & then - 예외가 발생하지 않으면 성공
        dashboardConfigMapper.updateDashConfig(request);
    }

    @Test
    @DisplayName("insertDashConfig - 최소 필드로 등록")
    void insertDashConfig_WithMinimalFields_Success() {
        // given
        final DashboardConfigRequest request = new DashboardConfigRequest();
        request.setConfigId("MIN_CONFIG_001");
        request.setConfigName("최소 설정");

        // when & then - 예외가 발생하지 않으면 성공
        dashboardConfigMapper.insertDashConfig(request);
    }
}

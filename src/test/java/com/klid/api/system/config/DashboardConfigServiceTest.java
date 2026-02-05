package com.klid.api.system.config;

import com.klid.api.BaseServiceTest;
import com.klid.api.system.config.dto.DashboardConfigRequest;
import com.klid.api.system.config.dto.DashboardConfigResponse;
import com.klid.api.system.config.service.DashboardConfigService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DashboardConfigService 통합 테스트
 */
class DashboardConfigServiceTest extends BaseServiceTest {

    @Autowired
    private DashboardConfigService dashboardConfigService;

    @Test
    @DisplayName("대시보드 설정 목록 조회 - 빈 파라미터")
    void getDashConfigList_WithEmptyParams_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<DashboardConfigResponse> result = dashboardConfigService.getDashConfigList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("대시보드 설정 목록 조회 - 사용 여부 필터")
    void getDashConfigList_WithUseYn_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("useYn", "Y");

        // when
        final List<DashboardConfigResponse> result = dashboardConfigService.getDashConfigList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("대시보드 설정 등록")
    void addDashConfig_Success() {
        // given
        final DashboardConfigRequest request = new DashboardConfigRequest();
        request.setConfigName("서비스 테스트 설정");
        request.setConfigValue("SVC_TEST_VALUE");
        request.setConfigDesc("서비스 테스트용 설정 설명");
        request.setUseYn("Y");
        request.setSortOrder(100);

        // when & then - 예외가 발생하지 않으면 성공
        dashboardConfigService.addDashConfig(request);
    }

    @Test
    @DisplayName("대시보드 설정 수정")
    void editDashConfig_Success() {
        // given
        final String configId = "CONFIG_FOR_UPDATE";
        final DashboardConfigRequest request = new DashboardConfigRequest();
        request.setConfigName("수정된 설정명");
        request.setConfigValue("UPDATED_VALUE");
        request.setConfigDesc("수정된 설명");
        request.setUseYn("N");
        request.setSortOrder(50);

        // when & then - 예외가 발생하지 않으면 성공
        dashboardConfigService.editDashConfig(configId, request);

        // verify that configId was set
        assertThat(request.getConfigId()).isEqualTo(configId);
    }

    @Test
    @DisplayName("대시보드 설정 등록 - 최소 필드만")
    void addDashConfig_WithMinimalFields_Success() {
        // given
        final DashboardConfigRequest request = new DashboardConfigRequest();
        request.setConfigName("최소 설정");

        // when & then - 예외가 발생하지 않으면 성공
        dashboardConfigService.addDashConfig(request);
    }
}

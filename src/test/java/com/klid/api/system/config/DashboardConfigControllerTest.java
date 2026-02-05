package com.klid.api.system.config;

import com.klid.api.BaseControllerTest;
import com.klid.api.system.config.dto.DashboardConfigRequest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * DashboardConfigController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class DashboardConfigControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/system/dashboard-config";

    @Test
    @DisplayName("GET /api/system/dashboard-config - 대시보드 설정 목록 조회")
    void getDashConfigList_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/system/dashboard-config - 파라미터로 필터링 조회")
    void getDashConfigList_WithParams_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("useYn", "Y")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /api/system/dashboard-config - 대시보드 설정 등록")
    void addDashConfig_ReturnsOk() throws Exception {
        final DashboardConfigRequest request = new DashboardConfigRequest();
        request.setConfigName("테스트 설정");
        request.setConfigValue("TEST_VALUE");
        request.setConfigDesc("테스트 설정 설명");
        request.setUseYn("Y");
        request.setSortOrder(1);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /api/system/dashboard-config/{configId} - 대시보드 설정 수정")
    void editDashConfig_ReturnsOk() throws Exception {
        final DashboardConfigRequest request = new DashboardConfigRequest();
        request.setConfigName("수정된 설정명");
        request.setConfigValue("UPDATED_VALUE");
        request.setConfigDesc("수정된 설명");
        request.setUseYn("Y");
        request.setSortOrder(2);

        mockMvc.perform(put(BASE_URL + "/CONFIG001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/system/dashboard-config - 빈 요청으로 등록")
    void addDashConfig_WithEmptyRequest_ReturnsOk() throws Exception {
        final DashboardConfigRequest request = new DashboardConfigRequest();

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }
}

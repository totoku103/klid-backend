package com.klid.api.system.thirdparty;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * VmsController 통합 테스트
 *
 * Note: VMS 서비스는 현재 구현이 필요한 상태이며,
 * UnsupportedOperationException을 던지도록 구현되어 있습니다.
 */
@Import(TestSecurityConfig.class)
class VmsControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/system/thirdparty/vms";

    @Test
    @DisplayName("GET /api/system/thirdparty/vms/privacy-policy - VMS 개인정보 처리방침 URL 조회 (미구현)")
    void getPrivacyPolicyUrl_ThrowsUnsupportedOperationException() throws Exception {
        // VMS 서비스가 아직 구현되지 않아 500 에러 반환 예상
        mockMvc.perform(get(BASE_URL + "/privacy-policy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/system/thirdparty/vms/redirect/auth - VMS 인증 리다이렉트 URL 생성 (미구현)")
    void getAuthRedirectUrl_ThrowsUnsupportedOperationException() throws Exception {
        // VMS 서비스가 아직 구현되지 않아 500 에러 반환 예상
        mockMvc.perform(get(BASE_URL + "/redirect/auth")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }
}

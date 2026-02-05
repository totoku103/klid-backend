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
 * CtssController 통합 테스트
 *
 * Note: CTSS 서비스는 현재 구현이 필요한 상태이며,
 * UnsupportedOperationException을 던지도록 구현되어 있습니다.
 */
@Import(TestSecurityConfig.class)
class CtssControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/system/thirdparty/ctss";

    @Test
    @DisplayName("GET /api/system/thirdparty/ctss/privacy-policy - CTSS 개인정보 처리방침 URL 조회 (미구현)")
    void getPrivacyPolicyUrl_ThrowsUnsupportedOperationException() throws Exception {
        // CTSS 서비스가 아직 구현되지 않아 500 에러 반환 예상
        mockMvc.perform(get(BASE_URL + "/privacy-policy")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("GET /api/system/thirdparty/ctss/redirect/auth - CTSS 인증 리다이렉트 URL 생성 (미구현)")
    void getAuthRedirectUrl_ThrowsUnsupportedOperationException() throws Exception {
        // CTSS 서비스가 아직 구현되지 않아 500 에러 반환 예상
        mockMvc.perform(get(BASE_URL + "/redirect/auth")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }
}

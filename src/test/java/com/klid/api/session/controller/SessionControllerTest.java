package com.klid.api.session.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 세션 Controller 통합 테스트
 */
@Import(TestSecurityConfig.class)
@DisplayName("세션 Controller 테스트")
class SessionControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/user/information";

    @Test
    @DisplayName("세션 사용자 정보 조회 - 로그인하지 않은 상태")
    void testGetSessionUserSimpleInformation_NotLoggedIn() throws Exception {
        // 로그인하지 않은 상태에서 요청하면 CustomException 발생
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().is5xxServerError());
    }
}

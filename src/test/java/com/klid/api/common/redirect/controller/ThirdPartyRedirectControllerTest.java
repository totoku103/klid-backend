package com.klid.api.common.redirect.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import com.klid.webapp.common.dto.CtrsRedirectCryptoReqDto;
import com.klid.webapp.common.enums.ThirdPartySystemTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 타 시스템 리다이렉트 Controller 통합 테스트
 */
@Import(TestSecurityConfig.class)
@DisplayName("타 시스템 리다이렉트 Controller 테스트")
class ThirdPartyRedirectControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/v2/third-party/auth";

    @Test
    @DisplayName("리다이렉트 토큰 생성 요청 - 잘못된 요청 (암호화 데이터 없음)")
    void testGenerateRedirectToken_InvalidRequest() throws Exception {
        final CtrsRedirectCryptoReqDto request = new CtrsRedirectCryptoReqDto();
        request.setSystemType(ThirdPartySystemTypes.CTRS);
        // 필수 데이터를 설정하지 않음

        mockMvc.perform(post(BASE_URL + "/redirect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().is5xxServerError()); // 복호화 실패로 예외 발생
    }

    @Test
    @DisplayName("리다이렉트 토큰 생성 요청 - 빈 요청")
    void testGenerateRedirectToken_EmptyRequest() throws Exception {
        final CtrsRedirectCryptoReqDto request = new CtrsRedirectCryptoReqDto();

        mockMvc.perform(post(BASE_URL + "/redirect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().is5xxServerError()); // 복호화 실패로 예외 발생
    }

    @Test
    @DisplayName("리다이렉트 토큰 생성 요청 - VMS 시스템 타입")
    void testGenerateRedirectToken_VmsSystemType() throws Exception {
        final CtrsRedirectCryptoReqDto request = new CtrsRedirectCryptoReqDto();
        request.setSystemType(ThirdPartySystemTypes.VMS);

        mockMvc.perform(post(BASE_URL + "/redirect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().is5xxServerError()); // 복호화 실패로 예외 발생
    }

    @Test
    @DisplayName("리다이렉트 토큰 생성 요청 - CTSS 시스템 타입")
    void testGenerateRedirectToken_CtssSystemType() throws Exception {
        final CtrsRedirectCryptoReqDto request = new CtrsRedirectCryptoReqDto();
        request.setSystemType(ThirdPartySystemTypes.CTSS);

        mockMvc.perform(post(BASE_URL + "/redirect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().is5xxServerError()); // 복호화 실패로 예외 발생
    }
}

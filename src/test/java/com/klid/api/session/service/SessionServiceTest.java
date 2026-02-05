package com.klid.api.session.service;

import com.klid.api.BaseServiceTest;
import com.klid.webapp.common.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 세션 Service 통합 테스트
 */
@DisplayName("세션 Service 테스트")
class SessionServiceTest extends BaseServiceTest {

    @Autowired
    private SessionService sessionService;

    @Test
    @DisplayName("세션 사용자 정보 조회 - 로그인하지 않은 상태")
    void testGetSessionUserSimpleInformation_NotLoggedIn() {
        // when & then
        assertThatThrownBy(() -> sessionService.getSessionUserSimpleInformation())
                .isInstanceOf(CustomException.class)
                .hasMessage("로그인 정보가 없습니다.");
    }
}

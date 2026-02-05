package com.klid.api.system.thirdparty;

import com.klid.api.BaseServiceTest;
import com.klid.api.system.thirdparty.service.CtssService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * CtssService 통합 테스트
 *
 * Note: CTSS 서비스는 현재 구현이 필요한 상태이며,
 * 모든 메소드가 UnsupportedOperationException을 던지도록 구현되어 있습니다.
 * 실제 ThirdPartyProperty, RedirectCtssService, IntegrationSessionManager 등의
 * 의존성이 추가되면 테스트를 수정해야 합니다.
 */
class CtssServiceTest extends BaseServiceTest {

    @Autowired
    private CtssService ctssService;

    @Test
    @DisplayName("CTSS 개인정보 처리방침 URL 조회 - 미구현 예외")
    void getPrivacyPolicyUrl_ThrowsUnsupportedOperationException() {
        // given & when & then
        assertThatThrownBy(() -> ctssService.getPrivacyPolicyUrl())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("구현 필요");
    }

    @Test
    @DisplayName("CTSS 인증 리다이렉트 URL 생성 - 미구현 예외")
    void generateAuthRedirectUrl_ThrowsUnsupportedOperationException() {
        // given & when & then
        assertThatThrownBy(() -> ctssService.generateAuthRedirectUrl())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("구현 필요");
    }
}

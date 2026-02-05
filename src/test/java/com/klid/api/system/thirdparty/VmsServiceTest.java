package com.klid.api.system.thirdparty;

import com.klid.api.BaseServiceTest;
import com.klid.api.system.thirdparty.service.VmsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * VmsService 통합 테스트
 *
 * Note: VMS 서비스는 현재 구현이 필요한 상태이며,
 * 모든 메소드가 UnsupportedOperationException을 던지도록 구현되어 있습니다.
 * 실제 ThirdPartyProperty, RedirectVmsService, IntegrationSessionManager 등의
 * 의존성이 추가되면 테스트를 수정해야 합니다.
 */
class VmsServiceTest extends BaseServiceTest {

    @Autowired
    private VmsService vmsService;

    @Test
    @DisplayName("VMS 개인정보 처리방침 URL 조회 - 미구현 예외")
    void getPrivacyPolicyUrl_ThrowsUnsupportedOperationException() {
        // given & when & then
        assertThatThrownBy(() -> vmsService.getPrivacyPolicyUrl())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("구현 필요");
    }

    @Test
    @DisplayName("VMS 인증 리다이렉트 URL 생성 - 미구현 예외")
    void generateAuthRedirectUrl_ThrowsUnsupportedOperationException() {
        // given & when & then
        assertThatThrownBy(() -> vmsService.generateAuthRedirectUrl())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("구현 필요");
    }
}

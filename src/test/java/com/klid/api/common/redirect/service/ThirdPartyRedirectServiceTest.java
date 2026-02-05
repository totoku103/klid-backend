package com.klid.api.common.redirect.service;

import com.klid.api.BaseServiceTest;
import com.klid.webapp.common.dto.CtrsRedirectCryptoReqDto;
import com.klid.webapp.common.enums.ThirdPartySystemTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 타 시스템 리다이렉트 Service 통합 테스트
 */
@DisplayName("타 시스템 리다이렉트 Service 테스트")
class ThirdPartyRedirectServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiThirdPartyRedirectService")
    private ThirdPartyRedirectService thirdPartyRedirectService;

    @Test
    @DisplayName("리다이렉트 URL 생성 - 암호화 데이터 없음 (CTRS)")
    void testGenerateRedirectUrl_NoEncryptedData_Ctrs() {
        // given
        final CtrsRedirectCryptoReqDto request = new CtrsRedirectCryptoReqDto();
        request.setSystemType(ThirdPartySystemTypes.CTRS);

        // when & then
        assertThatThrownBy(() -> thirdPartyRedirectService.generateRedirectUrl(request))
                .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("리다이렉트 URL 생성 - 빈 요청")
    void testGenerateRedirectUrl_EmptyRequest() {
        // given
        final CtrsRedirectCryptoReqDto request = new CtrsRedirectCryptoReqDto();

        // when & then
        assertThatThrownBy(() -> thirdPartyRedirectService.generateRedirectUrl(request))
                .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("리다이렉트 URL 생성 - 잘못된 데이터 (VMS)")
    void testGenerateRedirectUrl_InvalidData_Vms() {
        // given
        final CtrsRedirectCryptoReqDto request = new CtrsRedirectCryptoReqDto();
        request.setSystemType(ThirdPartySystemTypes.VMS);
        request.setUserName("invalid_data");
        request.setOfficeNumber("invalid_data");
        request.setPhoneNumber("invalid_data");
        request.setClientIp("127.0.0.1");

        // when & then
        assertThatThrownBy(() -> thirdPartyRedirectService.generateRedirectUrl(request))
                .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("리다이렉트 URL 생성 - 잘못된 데이터 (CTSS)")
    void testGenerateRedirectUrl_InvalidData_Ctss() {
        // given
        final CtrsRedirectCryptoReqDto request = new CtrsRedirectCryptoReqDto();
        request.setSystemType(ThirdPartySystemTypes.CTSS);
        request.setUserName("invalid_data");
        request.setOfficeNumber("invalid_data");
        request.setPhoneNumber("invalid_data");
        request.setClientIp("127.0.0.1");

        // when & then
        assertThatThrownBy(() -> thirdPartyRedirectService.generateRedirectUrl(request))
                .isInstanceOf(Exception.class);
    }
}

package com.klid.api.system.thirdparty.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * VMS 연동 서비스
 *
 * Note: 원본 코드의 ThirdPartyProperty, RedirectVmsService, IntegrationSessionManager 등의
 * 의존성이 필요하므로, 실제 구현 시 해당 클래스들을 마이그레이션하거나 적절히 수정해야 합니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VmsService {

    // TODO: ThirdPartyProperty 의존성 추가 필요
    // private final ThirdPartyProperty thirdPartyProperty;
    // private final RedirectVmsService redirectVmsService;

    /**
     * VMS 개인정보 처리방침 URL 조회
     */
    public String getPrivacyPolicyUrl() {
        // TODO: 실제 구현 시 ThirdPartyProperty에서 URL 조회
        // return thirdPartyProperty.getVmsUrlLinkPolicy();
        throw new UnsupportedOperationException("VMS 개인정보 처리방침 URL 조회 - 구현 필요");
    }

    /**
     * VMS 인증 후 리다이렉트 URL 생성
     */
    public String generateAuthRedirectUrl() {
        // TODO: 실제 구현 시 IntegrationSessionManager와 RedirectVmsService 연동
        /*
        final boolean authenticateAll = IntegrationSessionManager.isAuthenticateAll();

        if (!authenticateAll) {
            throw new CustomException("인증 정보가 없습니다.");
        }

        final IntegrationLoginInfoDto loginInfo = IntegrationSessionManager.getIntegrationLoginInfo();
        final String redirectUrl = redirectVmsService.requestRedirectMainPage(
            loginInfo.getUserName(),
            loginInfo.getOfficeNumber(),
            loginInfo.getPlainPhoneNumber(),
            loginInfo.getClientIp()
        );

        IntegrationSessionManager.setThirdPartyRedirect(redirectUrl);
        return redirectUrl;
        */
        throw new UnsupportedOperationException("VMS 리다이렉트 URL 생성 - 구현 필요");
    }
}

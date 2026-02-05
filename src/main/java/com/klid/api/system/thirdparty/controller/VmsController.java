package com.klid.api.system.thirdparty.controller;

import com.klid.api.system.thirdparty.service.VmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * VMS(차량관리 시스템) 연동 REST API
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system/thirdparty/vms")
public class VmsController {

    private final VmsService vmsService;

    /**
     * VMS 개인정보 처리방침 URL 조회
     */
    @GetMapping("/privacy-policy")
    public ResponseEntity<String> getPrivacyPolicyUrl() {
        final String url = vmsService.getPrivacyPolicyUrl();
        log.info("VMS 개인정보 처리방침 호출 URL: {}", url);
        return ResponseEntity.ok(url);
    }

    /**
     * VMS 인증 후 리다이렉트 URL 생성
     */
    @GetMapping("/redirect/auth")
    public ResponseEntity<String> getAuthRedirectUrl() {
        log.info("VMS redirect 주소 요청");
        final String redirectUrl = vmsService.generateAuthRedirectUrl();
        return ResponseEntity.ok(redirectUrl);
    }
}

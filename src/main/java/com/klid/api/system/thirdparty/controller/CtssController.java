package com.klid.api.system.thirdparty.controller;

import com.klid.api.system.thirdparty.service.CtssService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CTSS(교통정보 시스템) 연동 REST API
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system/thirdparty/ctss")
public class CtssController {

    private final CtssService ctssService;

    /**
     * CTSS 개인정보 처리방침 URL 조회
     */
    @GetMapping("/privacy-policy")
    public ResponseEntity<String> getPrivacyPolicyUrl() {
        final String url = ctssService.getPrivacyPolicyUrl();
        log.info("CTSS 개인정보 처리방침 호출 URL: {}", url);
        return ResponseEntity.ok(url);
    }

    /**
     * CTSS 인증 후 리다이렉트 URL 생성
     */
    @GetMapping("/redirect/auth")
    public ResponseEntity<String> getAuthRedirectUrl() {
        log.info("CTSS redirect 주소 요청");
        final String redirectUrl = ctssService.generateAuthRedirectUrl();
        return ResponseEntity.ok(redirectUrl);
    }
}

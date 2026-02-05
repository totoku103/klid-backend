package com.klid.api.common.redirect.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.klid.api.common.redirect.service.ThirdPartyRedirectService;
import com.klid.webapp.common.dto.CtrsRedirectCryptoReqDto;
import com.klid.webapp.common.dto.ThirdPartyBaseResDto;
import com.klid.webapp.common.enums.ThirdPartyResponseStatusCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 타 시스템 리다이렉트 인증 REST API Controller
 *
 * <p>외부 시스템에서 KLID 시스템으로의 SSO 리다이렉트를 위한 토큰 생성 API</p>
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/third-party/auth")
public class ThirdPartyRedirectController {

    private final ThirdPartyRedirectService thirdPartyRedirectService;

    /**
     * 리다이렉트 토큰 생성
     *
     * <p>타 시스템에서 사용자 정보를 암호화하여 전송하면, 검증 후 리다이렉트 URL을 반환합니다.</p>
     *
     * @param requestDTO 암호화된 사용자 정보 (이름, 사무실번호, 전화번호, 클라이언트IP, 시스템타입)
     * @return 리다이렉트 URL
     */
    @PostMapping("/redirect")
    public ResponseEntity<ThirdPartyBaseResDto<RedirectResponseDTO>> generateRedirectToken(
            @RequestBody final CtrsRedirectCryptoReqDto requestDTO) {
        log.info("타 시스템에서 리다이렉트 토큰 생성 요청 - 시스템타입: {}", requestDTO.getSystemType());

        final String redirectUrl = thirdPartyRedirectService.generateRedirectUrl(requestDTO);

        final RedirectResponseDTO responseDto = new RedirectResponseDTO(redirectUrl);
        final ThirdPartyBaseResDto<RedirectResponseDTO> response =
                new ThirdPartyBaseResDto<>(ThirdPartyResponseStatusCodes.SUCCESS);
        response.setData(responseDto);

        log.info("타사 시스템 리다이렉트 토큰 요청 완료 - 시스템타입: {}", requestDTO.getSystemType());
        return ResponseEntity.ok(response);
    }

    /**
     * 리다이렉트 URL 응답 DTO
     */
    public record RedirectResponseDTO(
            @JsonProperty("redirect_url")
            String redirectUrl
    ) {}
}

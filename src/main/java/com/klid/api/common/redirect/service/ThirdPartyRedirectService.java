package com.klid.api.common.redirect.service;

import com.klid.webapp.common.UserInfoNotFoundException;
import com.klid.webapp.common.UserInfoStatusCheckException;
import com.klid.webapp.common.dto.CtrsRedirectCryptoReqDto;
import com.klid.webapp.common.dto.CtrsRedirectPlainReqDto;
import com.klid.webapp.common.dto.UserDto;
import com.klid.webapp.common.service.PrimaryCtrsService;
import com.klid.webapp.common.service.ThirdPartyCryptoService;
import com.klid.webapp.main.thirdparty.redirect.dto.SimpleSaveTokenInfoDto;
import com.klid.webapp.main.thirdparty.redirect.service.TokenInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * 타 시스템 리다이렉트 서비스
 *
 * <p>외부 시스템에서의 SSO 리다이렉트 처리를 담당합니다.</p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ThirdPartyRedirectService {

    private final TokenInfoService tokenInfoService;
    private final PrimaryCtrsService primaryCtrsService;
    private final ThirdPartyCryptoService thirdPartyCryptoService;
    private final com.klid.webapp.common.service.ThirdPartyRedirectService legacyRedirectService;

    /**
     * 리다이렉트 URL 생성
     *
     * @param requestDTO 암호화된 리다이렉트 요청 정보
     * @return 리다이렉트 URL
     */
    public String generateRedirectUrl(final CtrsRedirectCryptoReqDto requestDTO) {
        // 1. 암호화된 요청 복호화
        final CtrsRedirectPlainReqDto plainReqDto = thirdPartyCryptoService.decryptCtrsRedirectCryptoReqDto(requestDTO);
        final String userKey = plainReqDto.getUserName() + " " + plainReqDto.getOfficeNumber() + " " + plainReqDto.getPhoneNumber();

        // 2. 사용자 정보 조회
        final UserDto userInfo = legacyRedirectService.getUserInfo(
                plainReqDto.getUserName(),
                plainReqDto.getOfficeNumber(),
                plainReqDto.getPhoneNumber()
        );

        if (userInfo == null) {
            log.warn("사용자 정보 조회 실패 - 존재하지 않는 사용자: {}", userKey);
            throw new UserInfoNotFoundException(userKey);
        }
        log.debug("사용자 정보 조회 성공 - {}", userKey);

        // 3. 로그인 정책 검증
        final PrimaryCtrsService.LoginCheckType loginCheckType =
                primaryCtrsService.validateLoginPolicy(userInfo, plainReqDto.getClientIp());

        if (loginCheckType != null) {
            final String message = loginCheckType.getMessage();
            log.warn("로그인 정책 검증 실패 - {} - {} - {}", loginCheckType, userKey, message);

            switch (loginCheckType) {
                case LOCK, RESET, EXPIRE, MISS_MATCH_IP, INACTIVE ->
                        throw new UserInfoStatusCheckException(message);
            }
        }

        // 4. 리다이렉트 URL 생성
        try {
            log.debug("리다이렉트 URL 생성 시작 - {}, 시스템타입: {}", userKey, plainReqDto.getSystemType());

            final SimpleSaveTokenInfoDto tokenInfo = legacyRedirectService.buildRedirectUrl(
                    plainReqDto.getUserName(),
                    plainReqDto.getOfficeNumber(),
                    plainReqDto.getPhoneNumber(),
                    plainReqDto.getClientIp(),
                    plainReqDto.getSystemType()
            );

            log.debug("토큰 저장 시작 - 토큰: {}, 만료시간: {}", tokenInfo.getToken(), tokenInfo.getExpiredAt());
            tokenInfoService.saveToken(tokenInfo);

            final String redirectUrl = legacyRedirectService.getRedirect(tokenInfo.getToken());
            log.debug("리다이렉트 URL 생성 완료 - URL: {}", redirectUrl);

            return redirectUrl;
        } catch (final UnsupportedEncodingException e) {
            log.error("리다이렉트 URL 생성 중 인코딩 오류 발생 - {}", userKey, e);
            throw new RuntimeException("리다이렉트 URL 생성 실패", e);
        }
    }
}

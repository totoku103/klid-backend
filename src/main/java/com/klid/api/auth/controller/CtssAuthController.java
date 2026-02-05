package com.klid.api.auth.controller;

import com.klid.api.auth.dto.request.CtssLoginRequestDTO;
import com.klid.api.auth.dto.request.OTPCheckRequestDTO;
import com.klid.api.auth.dto.response.CtssAuthPrimaryResponseDTO;
import com.klid.api.auth.dto.response.OTPCheckResponseDTO;
import com.klid.common.HttpRequestUtils;
import com.klid.common.IntegrationSessionManager;
import com.klid.webapp.common.CustomException;
import com.klid.webapp.common.SessionManager;
import com.klid.webapp.common.dto.IntegrationLoginInfoDto;
import com.klid.webapp.common.dto.ThirdPartyAuthPrimaryPlainResDto;
import com.klid.webapp.common.enums.ThirdPartySystemTypes;
import com.klid.webapp.common.service.OtpService;
import com.klid.webapp.common.service.PrimaryCtssServiceI;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/ctss")
public class CtssAuthController {

    private static final Logger log = LoggerFactory.getLogger(CtssAuthController.class);

    private final PrimaryCtssServiceI primaryCtssService;
    private final OtpService otpService;

    @PostMapping("/primary")
    public ResponseEntity<CtssAuthPrimaryResponseDTO> authenticatePrimary(@RequestBody final CtssLoginRequestDTO request) {
        final String userId = request.getId();
        final String clientIp = HttpRequestUtils.getClientIp();

        log.info("[CTSS] 1차 인증 요청 시작. id: {}, ip: {}", userId, clientIp);

        if (StringUtils.isBlank(userId)) {
            log.warn("CTSS 인증 실패 - 사용자 ID 누락, IP: {}", clientIp);
            throw new CustomException("사용자 ID를 입력해주세요.");
        }
        if (StringUtils.isBlank(request.getPassword())) {
            log.warn("CTSS 인증 실패 - 비밀번호 누락, 사용자ID: {}, IP: {}", userId, clientIp);
            throw new CustomException("비밀번호를 입력해주세요.");
        }

        log.debug("CTSS 외부 시스템 인증 요청 - 사용자ID: {}, IP: {}", userId, clientIp);
        SessionManager.setLiteLoginInfo(userId, clientIp, ThirdPartySystemTypes.CTSS);
        final ThirdPartyAuthPrimaryPlainResDto responseData = primaryCtssService.requestCheck(userId, request.getPassword(), clientIp);

        log.debug("CTSS 1차 인증 세션 설정 - 사용자ID: {}", userId);
        final IntegrationLoginInfoDto integrationLoginInfoDto = new IntegrationLoginInfoDto();
        integrationLoginInfoDto.setUserName(responseData.getUserName());
        integrationLoginInfoDto.setOfficeNumber(responseData.getOfficeNumber());
        integrationLoginInfoDto.setPlainPhoneNumber(responseData.getPhoneNumber());
        integrationLoginInfoDto.setSystemType(ThirdPartySystemTypes.CTSS);
        integrationLoginInfoDto.setClientIp(clientIp);

        IntegrationSessionManager.setPrimaryAuthSuccess(integrationLoginInfoDto);

        final CtssAuthPrimaryResponseDTO response = new CtssAuthPrimaryResponseDTO();
        final String ctrsOtpSecretKey = otpService.getCtrsOtpSecretKey(responseData.getUserName(), responseData.getOfficeNumber(), responseData.getPhoneNumber());
        final String vmsOtpSecretKey = otpService.getVmsOtpSecretKey(responseData.getUserName(), responseData.getOfficeNumber(), responseData.getPhoneNumber());
        final String ctssOtpSecretKey = responseData.getOtpKey();

        log.debug("ctrs otp: {}, vms otp: {}, ctss otp: {}", ctrsOtpSecretKey, vmsOtpSecretKey, ctssOtpSecretKey);

        if (otpService.hasUsableSecretKey(ctrsOtpSecretKey, vmsOtpSecretKey, ctssOtpSecretKey)) {
            log.debug("OTP 키 사용 - 사용자ID: {}", userId);
            IntegrationSessionManager.setOtpSecretKeys(ctrsOtpSecretKey, vmsOtpSecretKey, ctssOtpSecretKey);
        } else {
            log.debug("새 OTP 키 생성 - 사용자ID: {}", userId);
            final String otpSecretKey = otpService.generateSecretKeyAndSetSession();
            response.setOtpSecretKey(otpSecretKey);
        }

        log.info("CTSS 1차 인증 성공 - 사용자ID: {}, IP: {}", userId, clientIp);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/secondary/otp")
    public ResponseEntity<OTPCheckResponseDTO> authenticateOtp(@RequestBody final OTPCheckRequestDTO request) {
        otpService.validateSession();

        final String[] otpSecretKeyArrayFromSession = otpService.getOtpSecretKeyArrayFromSession();
        final String finalOtpSecretKey = otpService.getSecretKeyAndCheckOtpCode(otpSecretKeyArrayFromSession, request.getUserCode());
        final boolean isPass = StringUtils.isNotBlank(finalOtpSecretKey);

        if (isPass) {
            IntegrationSessionManager.setSecondAuthSuccess();
            final IntegrationLoginInfoDto integrationLoginInfo = IntegrationSessionManager.getIntegrationLoginInfo();
            otpService.updateOtpSecretKeyAllSystem(finalOtpSecretKey, integrationLoginInfo.getUserName(),
                integrationLoginInfo.getOfficeNumber(), integrationLoginInfo.getPlainPhoneNumber());
        } else {
            log.warn("CTSS 2차 OTP 인증 실패");
        }

        log.info("CTSS 2차 OTP 인증 완료 - 결과: {}", isPass ? "성공" : "실패");
        return ResponseEntity.ok(new OTPCheckResponseDTO(isPass));
    }
}

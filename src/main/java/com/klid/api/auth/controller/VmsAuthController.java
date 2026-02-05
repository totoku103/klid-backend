package com.klid.api.auth.controller;

import com.klid.api.auth.dto.request.EmailCheckRequestDTO;
import com.klid.api.auth.dto.request.OTPCheckRequestDTO;
import com.klid.api.auth.dto.request.VmsLoginRequestDTO;
import com.klid.api.auth.dto.response.EmailSendResponseDTO;
import com.klid.api.auth.dto.response.OTPCheckResponseDTO;
import com.klid.api.auth.dto.response.VmsAuthPrimaryResponseDTO;
import com.klid.common.HttpRequestUtils;
import com.klid.common.IntegrationSessionManager;
import com.klid.common.util.RandomUtils;
import com.klid.webapp.common.CustomException;
import com.klid.webapp.common.dto.*;
import com.klid.webapp.common.enums.ThirdPartySystemTypes;
import com.klid.webapp.common.service.EmailService;
import com.klid.webapp.common.service.OtpService;
import com.klid.webapp.common.service.PrimaryVmsServiceI;
import com.klid.webapp.common.service.SecondVmsService;
import lombok.RequiredArgsConstructor;
import me.totoku103.crypto.java.sha2.Sha512;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/vms")
public class VmsAuthController {

    private static final Logger log = LoggerFactory.getLogger(VmsAuthController.class);

    private final PrimaryVmsServiceI primaryVmsService;
    private final SecondVmsService secondVmsService;
    private final OtpService otpService;
    private final EmailService emailService;

    @PostMapping("/primary")
    public ResponseEntity<VmsAuthPrimaryResponseDTO> authenticatePrimary(@RequestBody final VmsLoginRequestDTO request) {
        final String userId = request.getId();
        final String clientIp = HttpRequestUtils.getClientIp();

        log.info("[VMS] 1차 인증 요청 시작. id: {}, ip: {}", userId, clientIp);

        if (StringUtils.isBlank(userId)) {
            log.warn("VMS 인증 실패 - 사용자 ID 누락, IP: {}", clientIp);
            throw new CustomException("사용자 ID를 입력해주세요.");
        }
        if (StringUtils.isBlank(request.getPassword())) {
            log.warn("VMS 인증 실패 - 비밀번호 누락, 사용자ID: {}, IP: {}", userId, clientIp);
            throw new CustomException("비밀번호를 입력해주세요.");
        }

        log.debug("VMS 외부 시스템 인증 요청 - 사용자ID: {}, IP: {}", userId, clientIp);
        final ThirdPartyAuthPrimaryPlainResDto responseData = primaryVmsService.requestCheck(userId, request.getPassword(), clientIp);

        final IntegrationLoginInfoDto integrationLoginInfoDto = new IntegrationLoginInfoDto();
        integrationLoginInfoDto.setUserName(responseData.getUserName());
        integrationLoginInfoDto.setOfficeNumber(responseData.getOfficeNumber());
        integrationLoginInfoDto.setPlainPhoneNumber(responseData.getPhoneNumber());
        integrationLoginInfoDto.setSystemType(ThirdPartySystemTypes.VMS);
        integrationLoginInfoDto.setClientIp(clientIp);

        IntegrationSessionManager.setPrimaryAuthSuccess(integrationLoginInfoDto);

        final VmsAuthPrimaryResponseDTO response = new VmsAuthPrimaryResponseDTO();

        // GPKI 정보 셋팅
        final String vmsGpkiSerialNumber = responseData.getGpkiKey();
        if (StringUtils.isNotBlank(vmsGpkiSerialNumber)) {
            final Sha512 sha512 = new Sha512();
            final String encryptSerialNumber = sha512.encrypt(vmsGpkiSerialNumber.getBytes(StandardCharsets.UTF_8));
            IntegrationSessionManager.setGpkiSerialNumber(encryptSerialNumber);
        }

        // OTP 정보 셋팅
        final String ctrsOtpSecretKey = otpService.getCtrsOtpSecretKey(responseData.getUserName(), responseData.getOfficeNumber(), responseData.getPhoneNumber());
        final String vmsOtpSecretKey = responseData.getOtpKey();
        final String ctssOtpSecretKey = otpService.getCtssOtpSecretKey(responseData.getUserName(), responseData.getOfficeNumber(), responseData.getPhoneNumber());

        log.debug("ctrs otp: {}, vms otp: {}, ctss otp: {}", ctrsOtpSecretKey, vmsOtpSecretKey, ctssOtpSecretKey);

        if (otpService.hasUsableSecretKey(ctrsOtpSecretKey, vmsOtpSecretKey, ctssOtpSecretKey)) {
            log.debug("OTP 키 사용 - 사용자ID: {}", userId);
            IntegrationSessionManager.setOtpSecretKeys(ctrsOtpSecretKey, vmsOtpSecretKey, ctssOtpSecretKey);
        } else {
            log.debug("새 OTP 키 생성 - 사용자ID: {}", userId);
            final String otpSecretKey = otpService.generateSecretKeyAndSetSession();
            response.setOtpSecretKey(otpSecretKey);
        }

        response.setEmail(responseData.getEmail());
        response.setGpkiKey(responseData.getGpkiKey());

        log.info("VMS 1차 인증 성공 - 사용자ID: {}, IP: {}", userId, clientIp);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/secondary/otp")
    public ResponseEntity<OTPCheckResponseDTO> authenticateOtp(@RequestBody final OTPCheckRequestDTO request) {
        otpService.validateSession();

        log.debug("VMS OTP 코드 검증 시작");
        final String[] otpSecretKeyArrayFromSession = otpService.getOtpSecretKeyArrayFromSession();
        final String finalOtpSecretKey = otpService.getSecretKeyAndCheckOtpCode(otpSecretKeyArrayFromSession, request.getUserCode());
        final boolean isPass = StringUtils.isNotBlank(finalOtpSecretKey);

        if (isPass) {
            log.info("VMS 2차 OTP 인증 성공");
            IntegrationSessionManager.setSecondAuthSuccess();
            final IntegrationLoginInfoDto integrationLoginInfo = IntegrationSessionManager.getIntegrationLoginInfo();
            otpService.updateOtpSecretKeyAllSystem(finalOtpSecretKey, integrationLoginInfo.getUserName(),
                integrationLoginInfo.getOfficeNumber(), integrationLoginInfo.getPlainPhoneNumber());
        } else {
            log.warn("VMS 2차 OTP 인증 실패");
        }

        log.info("VMS 2차 OTP 인증 완료 - 결과: {}", isPass ? "성공" : "실패");
        return ResponseEntity.ok(new OTPCheckResponseDTO(isPass));
    }

    @PostMapping("/secondary/email/send")
    public ResponseEntity<EmailSendResponseDTO> sendEmail() {
        log.info("VMS 이메일 인증 발송 요청 시작");

        log.debug("VMS 1차 인증 상태 검증");
        validateAuthPrimary();

        log.debug("VMS 이메일 발송 시간 제한 검증");
        emailService.validateEmailSendTime();

        final IntegrationLoginInfoDto integrationLoginInfo = IntegrationSessionManager.getIntegrationLoginInfo();

        log.debug("VMS 외부 시스템 이메일 발송 요청");
        final String random6Digit = RandomUtils.getRandom6Digit();
        final ThirdPartyBaseResDto<ThirdPartyAuthSecondValueResDto> response = secondVmsService.postSendEmail(random6Digit,
            integrationLoginInfo.getUserName(), integrationLoginInfo.getOfficeNumber(), integrationLoginInfo.getPlainPhoneNumber());
        IntegrationSessionManager.setEmailRandomDigit(random6Digit);

        emailService.setUserLastAction();

        final EmailSendInfoDto emailRandomDigit = IntegrationSessionManager.getEmailRandomDigit();
        final long expiredTimestamp = emailRandomDigit.getExpiredTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        log.info("VMS 이메일 인증 발송 완료 - 만료시간: {}", emailRandomDigit.getExpiredTime());
        return ResponseEntity.ok(new EmailSendResponseDTO(expiredTimestamp, response.getMessage()));
    }

    @PostMapping("/secondary/email/validate")
    public ResponseEntity<OTPCheckResponseDTO> validateEmailCode(@RequestBody final EmailCheckRequestDTO request) {
        log.info("이메일 인증 검증 요청 시작");

        log.debug("1차 인증 상태 검증");
        validateAuthPrimary();

        final EmailSendInfoDto emailRandomDigit = IntegrationSessionManager.getEmailRandomDigit();
        if (emailRandomDigit == null || StringUtils.isBlank(emailRandomDigit.getDigit())) {
            log.error("이메일 검증 실패 - 세션 내 이메일 검증 번호가 없음");
            throw new CustomException("400", "이메일 검증 번호가 없습니다.");
        }

        if (emailRandomDigit.getExpiredTime().isBefore(LocalDateTime.now())) {
            log.warn("이메일 검증 실패 - 검증 번호 만료: {}", emailRandomDigit.getExpiredTime());
            throw new CustomException("이메일 검증에 실패했습니다.");
        }

        if (emailRandomDigit.getDigit().equals(request.getUserCode())) {
            IntegrationSessionManager.removeEmailRandomDigit();
            IntegrationSessionManager.setSecondAuthSuccess();
            log.info("이메일 인증 성공");
            return ResponseEntity.ok(new OTPCheckResponseDTO(true));
        } else {
            log.warn("이메일 검증 실패 - 검증 번호 불일치");
            throw new CustomException("이메일 검증에 실패했습니다.");
        }
    }

    private void validateAuthPrimary() {
        final boolean isPassPrimary = IntegrationSessionManager.isAuthenticatePrimary();
        if (!isPassPrimary) {
            throw new AuthenticationCredentialsNotFoundException("1차 인증이 완료되지 않았습니다.");
        }
    }
}

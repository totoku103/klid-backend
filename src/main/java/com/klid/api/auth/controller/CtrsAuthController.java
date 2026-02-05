package com.klid.api.auth.controller;

import com.klid.api.auth.dto.request.CtrsLoginRequestDTO;
import com.klid.api.auth.dto.request.OTPCheckRequestDTO;
import com.klid.api.auth.dto.response.AuthPrimaryResponseDTO;
import com.klid.api.auth.dto.response.EmailSendResponseDTO;
import com.klid.api.auth.dto.response.OTPCheckResponseDTO;
import com.klid.common.HttpRequestUtils;
import com.klid.common.IntegrationSessionManager;
import com.klid.common.SEED_KISA256;
import com.klid.common.util.RandomUtils;
import com.klid.webapp.common.CustomException;
import com.klid.webapp.common.SessionManager;
import com.klid.webapp.common.dto.*;
import com.klid.webapp.common.enums.ThirdPartySystemTypes;
import com.klid.webapp.common.service.EmailService;
import com.klid.webapp.common.service.GpkiService;
import com.klid.webapp.common.service.OtpService;
import com.klid.webapp.common.service.PrimaryCtrsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/ctrs")
public class CtrsAuthController {

    private static final Logger log = LoggerFactory.getLogger(CtrsAuthController.class);

    private final PrimaryCtrsService primaryCtrsService;
    private final OtpService otpService;
    private final GpkiService gpkiService;
    private final EmailService emailService;

    @PostMapping("/primary")
    public ResponseEntity<AuthPrimaryResponseDTO> authenticatePrimary(@RequestBody final CtrsLoginRequestDTO request) {
        final String userId = request.getId();
        final String clientIp = HttpRequestUtils.getClientIp();

        log.info("[CTRS] 1차 인증 요청 시작. id: {}, ip: {}", userId, clientIp);

        try {
            validateRequest(request);
            log.debug("요청 데이터 검증 완료");

            SessionManager.setLiteLoginInfo(userId, clientIp, ThirdPartySystemTypes.CTRS);
            final PrimaryCtrsService.LoginCheckType check = primaryCtrsService.check(userId, request.getPassword(), clientIp);

            if (check == PrimaryCtrsService.LoginCheckType.OK) {
                log.info("CTRS 1차 인증 성공 - 사용자ID: {}", userId);
                return ResponseEntity.ok(handleSuccessfulAuthentication(userId, clientIp, check));
            }

            log.warn("CTRS 1차 인증 실패 - 사용자ID: {}, 실패 유형: {}", userId, check.name());
            return ResponseEntity.ok(new AuthPrimaryResponseDTO(check.name(), check.getMessage(), null));
        } catch (CustomException e) {
            log.warn("1차 인증 요청 실패: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("1차 인증 처리 중 예상치 못한 오류 발생", e);
            throw new CustomException("인증 처리 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/secondary/otp")
    public ResponseEntity<OTPCheckResponseDTO> authenticateOtp(@RequestBody final OTPCheckRequestDTO request) {
        final IntegrationLoginInfoDto integrationLoginInfo = IntegrationSessionManager.getIntegrationLoginInfo();
        log.info("CTRS 2차 OTP 인증 요청 시작 - {}", integrationLoginInfo.getPk());

        otpService.validateSession();
        final String[] otpSecretKeyArrayFromSession = otpService.getOtpSecretKeyArrayFromSession();
        final String finalOtpSecretKey = otpService.getSecretKeyAndCheckOtpCode(otpSecretKeyArrayFromSession, request.getUserCode());
        final boolean isPass = StringUtils.isNotBlank(finalOtpSecretKey);

        if (isPass) {
            IntegrationSessionManager.setSecondAuthSuccess();
            otpService.updateOtpSecretKeyAllSystem(finalOtpSecretKey, integrationLoginInfo.getUserName(),
                integrationLoginInfo.getOfficeNumber(), integrationLoginInfo.getPlainPhoneNumber());
        } else {
            log.warn("CTRS 2차 OTP 인증 실패 - {}", integrationLoginInfo.getPk());
        }

        return ResponseEntity.ok(new OTPCheckResponseDTO(isPass));
    }

    @PostMapping("/secondary/email")
    public ResponseEntity<EmailSendResponseDTO> authenticateEmail() {
        final UserDto user = SessionManager.getUser();
        if (user == null) {
            throw new CustomException("사용자 정보를 찾을 수 없습니다.");
        }

        final String cryptoEmailAddr = user.getEmailAddr();
        if (StringUtils.isBlank(cryptoEmailAddr)) {
            throw new CustomException("이메일 정보가 없습니다.");
        }
        final String plainEmail = SEED_KISA256.Decrypt(cryptoEmailAddr);

        log.info("CTRS 2차 인증 EMAIL 인증 요청 시작 - 사용자ID: {}", user.getUserId());

        log.debug("CTRS 1차 인증 상태 검증");
        validateAuthPrimary();

        log.debug("CTRS 이메일 발송 시간 제한 검증");
        emailService.validateEmailSendTime();

        log.debug("CTRS 외부 시스템 이메일 발송 요청");
        final String random6Digit = RandomUtils.getRandom6Digit();
        final ThirdPartyBaseResDto<ThirdPartyAuthEmailSendCryptResDto> response = emailService.postSendEmail(plainEmail, random6Digit);
        IntegrationSessionManager.setEmailRandomDigit(random6Digit);

        emailService.setUserLastAction();

        final EmailSendInfoDto emailRandomDigit = IntegrationSessionManager.getEmailRandomDigit();
        final long expiredTimestamp = emailRandomDigit.getExpiredTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        log.info("CTRS 이메일 인증 발송 완료 - 만료시간: {}", emailRandomDigit.getExpiredTime());
        return ResponseEntity.ok(new EmailSendResponseDTO(expiredTimestamp, response.getMessage()));
    }

    private void validateRequest(final CtrsLoginRequestDTO request) {
        if (request == null) {
            throw new CustomException("요청 정보가 없습니다.");
        }
        if (StringUtils.isBlank(request.getId())) {
            throw new CustomException("사용자 ID를 입력해주세요.");
        }
        if (StringUtils.isBlank(request.getPassword())) {
            throw new CustomException("비밀번호를 입력해주세요.");
        }
    }

    private void setIntegrationSession(final UserDto userDto) {
        final IntegrationLoginInfoDto integrationLoginInfoDto = new IntegrationLoginInfoDto();
        integrationLoginInfoDto.setUserName(userDto.getUserName());
        integrationLoginInfoDto.setOfficeNumber(userDto.getOffcTelNo());
        integrationLoginInfoDto.setPlainPhoneNumber(userDto.getMoblPhnNo());
        integrationLoginInfoDto.setSystemType(ThirdPartySystemTypes.CTRS);
        integrationLoginInfoDto.setClientIp(HttpRequestUtils.getClientIp());

        IntegrationSessionManager.setPrimaryAuthSuccess(integrationLoginInfoDto);
    }

    private AuthPrimaryResponseDTO handleSuccessfulAuthentication(final String userId, final String clientIp,
                                                                   final PrimaryCtrsService.LoginCheckType check) {
        final UserDto user = SessionManager.getIntegrateUser();
        if (user == null) {
            log.error("사용자 정보를 찾을 수 없음. userId: {}", userId);
            throw new CustomException("사용자 정보를 찾을 수 없습니다.");
        }

        setIntegrationSession(user);
        final String userName = user.getUserName();
        final String officeNumber = user.getOffcTelNo();
        final String plainPhoneNumber = user.getMoblPhnNo();

        // GPKI 정보 셋팅
        final String ctrsGpkiSerialNumber = gpkiService.getCtrsGpkiSerialNumber(userId);
        if (StringUtils.isNotBlank(ctrsGpkiSerialNumber)) {
            IntegrationSessionManager.setGpkiSerialNumber(ctrsGpkiSerialNumber);
        }

        // OTP 정보 셋팅
        final String ctrsOtpSecretKey = otpService.getCtrsOtpSecretKey(userName, officeNumber, plainPhoneNumber);
        final String vmsOtpSecretKey = otpService.getVmsOtpSecretKey(userName, officeNumber, plainPhoneNumber);
        final String ctssOtpSecretKey = otpService.getCtssOtpSecretKey(userName, officeNumber, plainPhoneNumber);

        log.debug("ctrs otp: {}, vms otp: {}, ctss otp: {}", ctrsOtpSecretKey, vmsOtpSecretKey, ctssOtpSecretKey);

        if (otpService.hasUsableSecretKey(ctrsOtpSecretKey, vmsOtpSecretKey, ctssOtpSecretKey)) {
            log.debug("OTP 키 사용 - 사용자ID: {}", userId);
            IntegrationSessionManager.setOtpSecretKeys(ctrsOtpSecretKey, vmsOtpSecretKey, ctssOtpSecretKey);
            return new AuthPrimaryResponseDTO(check.name(), check.getMessage(), null);
        } else {
            log.debug("새 OTP 키 생성 - 사용자ID: {}", userId);
            final String secretKey = otpService.generateSecretKeyAndSetSession();
            return new AuthPrimaryResponseDTO(check.name(), check.getMessage(), secretKey);
        }
    }

    private void validateAuthPrimary() {
        final boolean isPassPrimary = IntegrationSessionManager.isAuthenticatePrimary();
        if (!isPassPrimary) {
            throw new AuthenticationCredentialsNotFoundException("1차 인증이 완료되지 않았습니다.");
        }
    }
}

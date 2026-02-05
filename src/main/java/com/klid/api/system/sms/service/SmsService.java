package com.klid.api.system.sms.service;

import com.klid.api.system.sms.dto.SmsMessageRequest;
import com.klid.api.system.sms.dto.SmsRecipient;
import com.klid.api.system.sms.persistence.SmsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * SMS 전송 서비스
 * NRMSG_DATA 테이블에 INSERT하면 Nuri2 Agent가 읽어서 발송
 */
@Slf4j
@Service("apiSmsService")
@RequiredArgsConstructor
public class SmsService {

    private static final int SMS_MAX_BYTES = 80;

    private final SmsMapper smsMapper;

    /**
     * SMS 메시지 전송 (DB에 저장하면 Nuri2 Agent가 발송 처리)
     */
    @Transactional
    public void sendSmsMessage(final SmsMessageRequest request) {
        final String message = normalizeLineBreaks(request.getMsg());
        final String sender = extractDigits(request.getSender());

        // 메시지 길이 검증
        validateMessageLength(message);

        // 각 수신자에게 SMS 발송
        for (final SmsRecipient recipient : request.getRecipients()) {
            final String phone = recipient.getPhone();
            final String cleanPhone = extractAndValidateMobileNumber(phone);

            final Map<String, Object> params = new HashMap<>();
            params.put("phone", cleanPhone);
            params.put("sender", sender);
            params.put("message", message);
            params.put("type", request.getType());

            smsMapper.insertMessage(params);

            log.info("SMS inserted to NRMSG_DATA - phone: {}, date: {}", phone, LocalDateTime.now());
        }
    }

    /**
     * 전화번호에서 숫자만 추출하고 유효성 검증
     */
    private String extractAndValidateMobileNumber(final String phoneNumber) {
        final String digits = extractDigits(phoneNumber);
        validatePhoneLength(digits, phoneNumber);
        validateMobilePrefix(digits, phoneNumber);
        return digits;
    }

    /**
     * 전화번호에서 숫자만 추출
     */
    private String extractDigits(final String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("전화번호가 입력되지 않았습니다.");
        }
        return phoneNumber.replaceAll("[^0-9]", "");
    }

    /**
     * 전화번호 자릿수 검증 (10~11자리)
     */
    private void validatePhoneLength(final String digits, final String originalNumber) {
        if (digits.length() < 10 || digits.length() > 11) {
            throw new IllegalArgumentException("전화번호는 10~11자리여야 합니다: " + originalNumber);
        }
    }

    /**
     * 휴대폰 번호 prefix 검증 (01x: 010, 011, 016, 017, 018, 019)
     */
    private void validateMobilePrefix(final String digits, final String originalNumber) {
        if (!digits.matches("^01[0-9]\\d{7,8}$")) {
            throw new IllegalArgumentException("01x(010, 011, 016, 017, 018, 019) 번호만 발송 가능합니다: " + originalNumber);
        }
    }

    /**
     * 줄바꿈 문자를 LF(\n)로 통일
     */
    private String normalizeLineBreaks(final String text) {
        if (text == null) {
            return null;
        }
        return text.trim()
                .replaceAll("\\r\\n", "\n")
                .replaceAll("\\r", "\n");
    }

    /**
     * 메시지 길이 검증 (80바이트 초과 시 예외)
     */
    private void validateMessageLength(final String message) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("메시지가 입력되지 않았습니다.");
        }
        final int byteLength = calculateByteLength(message);
        if (byteLength > SMS_MAX_BYTES) {
            throw new IllegalArgumentException(
                    String.format("메시지가 %d바이트를 초과했습니다. (현재: %d바이트)", SMS_MAX_BYTES, byteLength));
        }
    }

    /**
     * 문자열의 바이트 길이 계산 (한글: 2바이트, 영문/숫자/특수문자: 1바이트)
     */
    private int calculateByteLength(final String text) {
        if (text == null) {
            return 0;
        }
        int byteLength = 0;
        for (final char c : text.toCharArray()) {
            if (c >= 0x0080) {
                byteLength += 2;
            } else {
                byteLength += 1;
            }
        }
        return byteLength;
    }
}

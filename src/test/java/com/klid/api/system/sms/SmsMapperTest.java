package com.klid.api.system.sms;

import com.klid.api.BaseMapperTest;
import com.klid.api.system.sms.persistence.SmsMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

/**
 * SmsMapper 통합 테스트
 */
class SmsMapperTest extends BaseMapperTest {

    @Autowired
    @Qualifier("apiSmsMapper")
    private SmsMapper smsMapper;

    @Test
    @DisplayName("insertMessage - SMS 메시지 저장")
    void insertMessage_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("phone", "01012345678");
        params.put("sender", "01098765432");
        params.put("message", "테스트 메시지");
        params.put("type", "SMS");

        // when & then - 예외가 발생하지 않으면 성공
        smsMapper.insertMessage(params);
    }

    @Test
    @DisplayName("insertMessage - 타입 없이 SMS 메시지 저장")
    void insertMessage_WithoutType_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("phone", "01012345678");
        params.put("sender", "01098765432");
        params.put("message", "타입 없는 메시지");

        // when & then - 예외가 발생하지 않으면 성공
        smsMapper.insertMessage(params);
    }

    @Test
    @DisplayName("insertMessage - 한글 메시지 저장")
    void insertMessage_KoreanMessage_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("phone", "01011112222");
        params.put("sender", "01033334444");
        params.put("message", "안녕하세요. 테스트입니다.");
        params.put("type", "SMS");

        // when & then - 예외가 발생하지 않으면 성공
        smsMapper.insertMessage(params);
    }

    @Test
    @DisplayName("insertMessage - 영문 메시지 저장")
    void insertMessage_EnglishMessage_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("phone", "01055556666");
        params.put("sender", "01077778888");
        params.put("message", "Hello, this is a test message.");
        params.put("type", "SMS");

        // when & then - 예외가 발생하지 않으면 성공
        smsMapper.insertMessage(params);
    }

    @Test
    @DisplayName("insertMessage - 줄바꿈 포함 메시지 저장")
    void insertMessage_WithLineBreaks_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("phone", "01099990000");
        params.put("sender", "01011110000");
        params.put("message", "첫째줄\n둘째줄\n셋째줄");
        params.put("type", "SMS");

        // when & then - 예외가 발생하지 않으면 성공
        smsMapper.insertMessage(params);
    }

    @Test
    @DisplayName("insertMessage - 특수문자 포함 메시지 저장")
    void insertMessage_WithSpecialCharacters_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("phone", "01012341234");
        params.put("sender", "01056785678");
        params.put("message", "특수문자 테스트: !@#$%^&*()");
        params.put("type", "SMS");

        // when & then - 예외가 발생하지 않으면 성공
        smsMapper.insertMessage(params);
    }
}

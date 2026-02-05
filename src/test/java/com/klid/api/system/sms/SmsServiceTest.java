package com.klid.api.system.sms;

import com.klid.api.BaseServiceTest;
import com.klid.api.system.sms.dto.SmsMessageRequest;
import com.klid.api.system.sms.dto.SmsRecipient;
import com.klid.api.system.sms.service.SmsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * SmsService 통합 테스트
 */
class SmsServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiSmsService")
    private SmsService smsService;

    @Test
    @DisplayName("SMS 메시지 전송 - 정상 케이스")
    void sendSmsMessage_Success() {
        // given
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("홍길동");
        recipient.setPhone("01012345678");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("테스트 메시지");
        request.setSender("01098765432");
        request.setRecipients(Collections.singletonList(recipient));

        // when & then - 예외가 발생하지 않으면 성공
        smsService.sendSmsMessage(request);
    }

    @Test
    @DisplayName("SMS 메시지 전송 - 다수 수신자")
    void sendSmsMessage_MultipleRecipients_Success() {
        // given
        final SmsRecipient recipient1 = new SmsRecipient();
        recipient1.setUserName("홍길동");
        recipient1.setPhone("01012345678");

        final SmsRecipient recipient2 = new SmsRecipient();
        recipient2.setUserName("김철수");
        recipient2.setPhone("01087654321");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("다수 수신자 테스트");
        request.setSender("01098765432");
        request.setRecipients(Arrays.asList(recipient1, recipient2));

        // when & then - 예외가 발생하지 않으면 성공
        smsService.sendSmsMessage(request);
    }

    @Test
    @DisplayName("SMS 메시지 전송 - 한글 메시지 (바이트 검증)")
    void sendSmsMessage_KoreanMessage_Success() {
        // given
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("테스트");
        recipient.setPhone("01011112222");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("안녕하세요"); // 5글자 * 2바이트 = 10바이트
        request.setSender("01099998888");
        request.setRecipients(Collections.singletonList(recipient));

        // when & then - 예외가 발생하지 않으면 성공
        smsService.sendSmsMessage(request);
    }

    @Test
    @DisplayName("SMS 메시지 전송 - 80바이트 이하 영문 메시지")
    void sendSmsMessage_EnglishMessage_UnderLimit_Success() {
        // given
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("Test");
        recipient.setPhone("01033334444");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("Hello World"); // 11바이트
        request.setSender("01055556666");
        request.setRecipients(Collections.singletonList(recipient));

        // when & then - 예외가 발생하지 않으면 성공
        smsService.sendSmsMessage(request);
    }

    @Test
    @DisplayName("SMS 메시지 전송 - 80바이트 초과 시 예외 발생")
    void sendSmsMessage_ExceedsLimit_ThrowsException() {
        // given
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("테스트");
        recipient.setPhone("01011112222");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        // 한글 41자 = 82바이트 (80바이트 초과)
        request.setMsg("가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가");
        request.setSender("01099998888");
        request.setRecipients(Collections.singletonList(recipient));

        // when & then
        assertThatThrownBy(() -> smsService.sendSmsMessage(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("80바이트를 초과");
    }

    @Test
    @DisplayName("SMS 메시지 전송 - 빈 메시지 시 예외 발생")
    void sendSmsMessage_EmptyMessage_ThrowsException() {
        // given
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("테스트");
        recipient.setPhone("01011112222");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("");
        request.setSender("01099998888");
        request.setRecipients(Collections.singletonList(recipient));

        // when & then
        assertThatThrownBy(() -> smsService.sendSmsMessage(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("메시지가 입력되지 않았습니다");
    }

    @Test
    @DisplayName("SMS 메시지 전송 - 잘못된 전화번호 형식 예외")
    void sendSmsMessage_InvalidPhoneNumber_ThrowsException() {
        // given
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("테스트");
        recipient.setPhone("021234567"); // 02로 시작하는 유선전화

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("테스트");
        request.setSender("01099998888");
        request.setRecipients(Collections.singletonList(recipient));

        // when & then
        assertThatThrownBy(() -> smsService.sendSmsMessage(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("01x");
    }

    @Test
    @DisplayName("SMS 메시지 전송 - 전화번호 자릿수 부족 예외")
    void sendSmsMessage_PhoneTooShort_ThrowsException() {
        // given
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("테스트");
        recipient.setPhone("0101234"); // 7자리

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("테스트");
        request.setSender("01099998888");
        request.setRecipients(Collections.singletonList(recipient));

        // when & then
        assertThatThrownBy(() -> smsService.sendSmsMessage(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("10~11자리");
    }

    @Test
    @DisplayName("SMS 메시지 전송 - 빈 발신자 전화번호 예외")
    void sendSmsMessage_EmptySender_ThrowsException() {
        // given
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("테스트");
        recipient.setPhone("01012345678");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("테스트");
        request.setSender("");
        request.setRecipients(Collections.singletonList(recipient));

        // when & then
        assertThatThrownBy(() -> smsService.sendSmsMessage(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("전화번호가 입력되지 않았습니다");
    }

    @Test
    @DisplayName("SMS 메시지 전송 - 줄바꿈 문자 정규화")
    void sendSmsMessage_NormalizeLineBreaks_Success() {
        // given
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("테스트");
        recipient.setPhone("01012345678");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("첫줄\r\n둘째줄\r셋째줄"); // 다양한 줄바꿈 문자
        request.setSender("01098765432");
        request.setRecipients(Collections.singletonList(recipient));

        // when & then - 예외가 발생하지 않으면 성공
        smsService.sendSmsMessage(request);
    }

    @Test
    @DisplayName("SMS 메시지 전송 - 하이픈 포함 전화번호")
    void sendSmsMessage_PhoneWithHyphens_Success() {
        // given
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("테스트");
        recipient.setPhone("010-1234-5678");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("하이픈 테스트");
        request.setSender("010-9876-5432");
        request.setRecipients(Collections.singletonList(recipient));

        // when & then - 예외가 발생하지 않으면 성공
        smsService.sendSmsMessage(request);
    }
}

package com.klid.api.system.sms;

import com.klid.api.BaseControllerTest;
import com.klid.api.system.sms.dto.SmsMessageRequest;
import com.klid.api.system.sms.dto.SmsRecipient;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * SmsController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class SmsControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/system/sms";

    @Test
    @DisplayName("POST /api/system/sms/send - SMS 메시지 전송")
    void sendSmsMessage_ReturnsOk() throws Exception {
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("홍길동");
        recipient.setPhone("01012345678");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("테스트 메시지");
        request.setSender("01098765432");
        request.setRecipients(Collections.singletonList(recipient));

        mockMvc.perform(post(BASE_URL + "/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/system/sms/send - 다수 수신자에게 SMS 전송")
    void sendSmsMessage_MultipleRecipients_ReturnsOk() throws Exception {
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

        mockMvc.perform(post(BASE_URL + "/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/system/sms/send - 한글 메시지 전송")
    void sendSmsMessage_KoreanMessage_ReturnsOk() throws Exception {
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("테스트");
        recipient.setPhone("01011112222");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("안녕하세요. 테스트입니다.");
        request.setSender("01099998888");
        request.setRecipients(Collections.singletonList(recipient));

        mockMvc.perform(post(BASE_URL + "/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/system/sms/send - 짧은 영문 메시지 전송")
    void sendSmsMessage_EnglishMessage_ReturnsOk() throws Exception {
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("Test User");
        recipient.setPhone("01033334444");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setType("SMS");
        request.setMsg("Hello, this is a test message.");
        request.setSender("01055556666");
        request.setRecipients(Collections.singletonList(recipient));

        mockMvc.perform(post(BASE_URL + "/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/system/sms/send - 타입 없이 SMS 전송")
    void sendSmsMessage_WithoutType_ReturnsOk() throws Exception {
        final SmsRecipient recipient = new SmsRecipient();
        recipient.setUserName("홍길동");
        recipient.setPhone("01012345678");

        final SmsMessageRequest request = new SmsMessageRequest();
        request.setMsg("타입 없는 메시지");
        request.setSender("01098765432");
        request.setRecipients(Collections.singletonList(recipient));

        mockMvc.perform(post(BASE_URL + "/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }
}

package com.klid.webapp.realtimeNotify.sms.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.realtimeNotify.sms.dto.SmsSendResponseDto;
import com.klid.webapp.realtimeNotify.sms.persistence.RealtimeNotifySmsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RealtimeNotifySmsServiceImplTest {

    @Mock
    private RealtimeNotifySmsMapper mapper;

    @InjectMocks
    private RealtimeNotifySmsServiceImpl service;

    @Nested
    @DisplayName("sendSms()")
    class SendSms {

        @Test
        @DisplayName("수신자 목록이 비어있으면 에러를 반환한다")
        void returnsErrorWhenNoReceivers() {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("msg", "테스트 메시지");
            reqMap.put("sender", "010-1234-5678");
            reqMap.put("recv", new ArrayList<>());

            ReturnData result = service.sendSms(new Criterion(reqMap, false));

            assertTrue(result.getHasError());
            assertEquals("ERR_NO_RECEIVER", result.getErrorInfo().getCode());
        }

        @Test
        @DisplayName("수신자 목록이 null이면 에러를 반환한다")
        void returnsErrorWhenReceiversIsNull() {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("msg", "테스트 메시지");
            reqMap.put("sender", "010-1234-5678");
            reqMap.put("recv", null);

            ReturnData result = service.sendSms(new Criterion(reqMap, false));

            assertTrue(result.getHasError());
            assertEquals("ERR_NO_RECEIVER", result.getErrorInfo().getCode());
        }

        @Test
        @DisplayName("SMS 발송이 성공하면 성공 응답을 반환한다")
        void returnsSuccessWhenSmsSent() {
            Map<String, Object> reqMap = createValidSmsRequest();
            given(mapper.insertSmsMessage(any())).willReturn(1);

            ReturnData result = service.sendSms(new Criterion(reqMap, false));

            assertFalse(result.getHasError());
            assertNotNull(result.getResultData());
            SmsSendResponseDto response = (SmsSendResponseDto) result.getResultData();
            assertTrue(response.isSuccess());
            assertEquals(1, response.getSentCount());
        }

        @Test
        @DisplayName("여러 수신자에게 SMS를 발송하면 발송 건수가 정확히 반환된다")
        void returnsCorrectCountForMultipleReceivers() {
            Map<String, Object> reqMap = createMultiReceiverSmsRequest();
            given(mapper.insertSmsMessage(any())).willReturn(1);

            ReturnData result = service.sendSms(new Criterion(reqMap, false));

            assertFalse(result.getHasError());
            SmsSendResponseDto response = (SmsSendResponseDto) result.getResultData();
            assertEquals(3, response.getSentCount());
            verify(mapper, times(3)).insertSmsMessage(any());
        }

        @Test
        @DisplayName("전화번호의 하이픈이 제거되어 저장된다")
        void removesHyphenFromPhoneNumber() {
            Map<String, Object> reqMap = createValidSmsRequest();
            given(mapper.insertSmsMessage(any())).willAnswer(invocation -> {
                Map<String, Object> param = invocation.getArgument(0);
                assertEquals("01099999999", param.get("phone"));
                assertEquals("01012345678", param.get("callback"));
                return 1;
            });

            service.sendSms(new Criterion(reqMap, false));

            verify(mapper).insertSmsMessage(any());
        }

        @Test
        @DisplayName("DB 저장 실패시 성공 건수가 0으로 반환된다")
        void returnsZeroCountWhenDbFails() {
            Map<String, Object> reqMap = createValidSmsRequest();
            given(mapper.insertSmsMessage(any())).willReturn(0);

            ReturnData result = service.sendSms(new Criterion(reqMap, false));

            assertFalse(result.getHasError());
            SmsSendResponseDto response = (SmsSendResponseDto) result.getResultData();
            assertFalse(response.isSuccess());
            assertEquals(0, response.getSentCount());
        }
    }

    @Nested
    @DisplayName("getSmsStatus()")
    class GetSmsStatus {

        @Test
        @DisplayName("SMS 상태 조회가 성공하면 상태 정보를 반환한다")
        void returnsStatusWhenFound() {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("msgKey", "12345");

            Map<String, Object> statusMap = new HashMap<>();
            statusMap.put("msgKey", 12345L);
            statusMap.put("msgState", 2);
            statusMap.put("phone", "01099999999");

            given(mapper.selectSmsStatus(any())).willReturn(statusMap);

            ReturnData result = service.getSmsStatus(new Criterion(reqMap));

            assertFalse(result.getHasError());
            assertNotNull(result.getResultData());
        }

        @Test
        @DisplayName("SMS 상태 조회시 예외가 발생하면 에러를 반환한다")
        void returnsErrorWhenExceptionOccurs() {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("msgKey", "12345");

            given(mapper.selectSmsStatus(any())).willThrow(new RuntimeException("DB Error"));

            ReturnData result = service.getSmsStatus(new Criterion(reqMap));

            assertTrue(result.getHasError());
            assertEquals("ERR_SMS_STATUS", result.getErrorInfo().getCode());
        }
    }

    private Map<String, Object> createValidSmsRequest() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("msg", "테스트 메시지입니다.");
        reqMap.put("subject", "테스트 제목");
        reqMap.put("sender", "010-1234-5678");
        reqMap.put("userId", "testUser");

        List<Map<String, Object>> receivers = new ArrayList<>();
        Map<String, Object> receiver = new HashMap<>();
        receiver.put("userName", "홍길동");
        receiver.put("phone", "010-9999-9999");
        receivers.add(receiver);
        reqMap.put("recv", receivers);

        return reqMap;
    }

    private Map<String, Object> createMultiReceiverSmsRequest() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("msg", "테스트 메시지입니다.");
        reqMap.put("sender", "010-1234-5678");

        List<Map<String, Object>> receivers = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Map<String, Object> receiver = new HashMap<>();
            receiver.put("userName", "수신자" + i);
            receiver.put("phone", "010-1111-111" + i);
            receivers.add(receiver);
        }
        reqMap.put("recv", receivers);

        return reqMap;
    }
}

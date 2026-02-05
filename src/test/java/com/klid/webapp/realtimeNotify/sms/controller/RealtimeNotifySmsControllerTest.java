package com.klid.webapp.realtimeNotify.sms.controller;

import com.klid.webapp.common.ErrorInfo;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.realtimeNotify.sms.dto.SmsSendResponseDto;
import com.klid.webapp.realtimeNotify.sms.service.RealtimeNotifySmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RealtimeNotifySmsControllerTest {

    @Mock
    private RealtimeNotifySmsService smsService;

    private RealtimeNotifySmsController controller;

    @BeforeEach
    void setUp() {
        controller = new RealtimeNotifySmsController(smsService);
    }

    @Nested
    @DisplayName("sendSms()")
    class SendSms {

        @Test
        @DisplayName("SMS 발송 성공시 200 OK를 반환한다")
        void returns200WhenSmsSentSuccessfully() {
            Map<String, Object> reqMap = createValidSmsRequest();
            ReturnData returnData = new ReturnData();
            returnData.setResultData(SmsSendResponseDto.builder()
                    .success(true)
                    .sentCount(1)
                    .message("1건의 SMS가 발송 대기열에 등록되었습니다.")
                    .build());

            given(smsService.sendSms(any())).willReturn(returnData);

            ResponseEntity<ReturnData> response = controller.sendSms(reqMap);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertFalse(response.getBody().getHasError());
        }

        @Test
        @DisplayName("SMS 발송 실패시 500 에러를 반환한다")
        void returns500WhenSmsFails() {
            Map<String, Object> reqMap = createValidSmsRequest();
            ReturnData returnData = new ReturnData();
            returnData.setErrorInfo(new ErrorInfo("ERR_SMS_SEND", "SMS 발송 실패"));

            given(smsService.sendSms(any())).willReturn(returnData);

            ResponseEntity<ReturnData> response = controller.sendSms(reqMap);

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertTrue(response.getBody().getHasError());
        }

        @Test
        @DisplayName("발송 결과에 성공 건수가 포함된다")
        void responseContainsSentCount() {
            Map<String, Object> reqMap = createValidSmsRequest();
            ReturnData returnData = new ReturnData();
            returnData.setResultData(SmsSendResponseDto.builder()
                    .success(true)
                    .sentCount(5)
                    .message("5건의 SMS가 발송 대기열에 등록되었습니다.")
                    .build());

            given(smsService.sendSms(any())).willReturn(returnData);

            ResponseEntity<ReturnData> response = controller.sendSms(reqMap);

            SmsSendResponseDto responseDto = (SmsSendResponseDto) response.getBody().getResultData();
            assertEquals(5, responseDto.getSentCount());
        }
    }

    @Nested
    @DisplayName("getSmsStatus()")
    class GetSmsStatus {

        @Test
        @DisplayName("SMS 상태 조회 성공시 200 OK를 반환한다")
        void returns200WhenStatusFound() {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("msgKey", "12345");

            ReturnData returnData = new ReturnData();
            Map<String, Object> statusMap = new HashMap<>();
            statusMap.put("msgState", 2);
            returnData.setResultData(statusMap);

            given(smsService.getSmsStatus(any())).willReturn(returnData);

            ResponseEntity<ReturnData> response = controller.getSmsStatus(reqMap);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertFalse(response.getBody().getHasError());
        }
    }

    private Map<String, Object> createValidSmsRequest() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("msg", "테스트 메시지");
        reqMap.put("sender", "010-1234-5678");

        List<Map<String, Object>> receivers = new ArrayList<>();
        Map<String, Object> receiver = new HashMap<>();
        receiver.put("userName", "홍길동");
        receiver.put("phone", "010-9999-9999");
        receivers.add(receiver);
        reqMap.put("recv", receivers);

        return reqMap;
    }
}

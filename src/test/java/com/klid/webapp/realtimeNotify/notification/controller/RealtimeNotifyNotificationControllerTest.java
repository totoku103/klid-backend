package com.klid.webapp.realtimeNotify.notification.controller;

import com.klid.webapp.common.ErrorInfo;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.realtimeNotify.notification.dto.NotificationResponseDto;
import com.klid.webapp.realtimeNotify.notification.service.RealtimeNotifyNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RealtimeNotifyNotificationControllerTest {

    @Mock
    private RealtimeNotifyNotificationService notificationService;

    private RealtimeNotifyNotificationController controller;

    @BeforeEach
    void setUp() {
        controller = new RealtimeNotifyNotificationController(notificationService);
    }

    @Nested
    @DisplayName("sendNotification()")
    class SendNotification {

        @Test
        @DisplayName("알림 발송 성공시 200 OK를 반환한다")
        void returns200WhenNotificationSent() {
            Map<String, Object> reqMap = createNotificationRequest();
            ReturnData returnData = createSuccessReturnData();

            given(notificationService.sendNotification(any())).willReturn(returnData);

            ResponseEntity<ReturnData> response = controller.sendNotification(reqMap);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertFalse(response.getBody().getHasError());
        }

        @Test
        @DisplayName("알림 발송 실패시 500 에러를 반환한다")
        void returns500WhenNotificationFails() {
            Map<String, Object> reqMap = createNotificationRequest();
            ReturnData returnData = new ReturnData();
            returnData.setErrorInfo(new ErrorInfo("ERR_NOTIFICATION", "알림 발송 실패"));

            given(notificationService.sendNotification(any())).willReturn(returnData);

            ResponseEntity<ReturnData> response = controller.sendNotification(reqMap);

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertTrue(response.getBody().getHasError());
        }
    }

    @Nested
    @DisplayName("broadcastNotification()")
    class BroadcastNotification {

        @Test
        @DisplayName("브로드캐스트 알림 발송 성공시 200 OK를 반환한다")
        void returns200WhenBroadcastSent() {
            Map<String, Object> reqMap = createNotificationRequest();
            ReturnData returnData = createSuccessReturnData();

            given(notificationService.sendNotification(any())).willReturn(returnData);

            ResponseEntity<ReturnData> response = controller.broadcastNotification(reqMap);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertFalse(response.getBody().getHasError());
        }
    }

    @Nested
    @DisplayName("sendToDepartment()")
    class SendToDepartment {

        @Test
        @DisplayName("기관 알림 발송 성공시 200 OK를 반환한다")
        void returns200WhenDeptNotificationSent() {
            Map<String, Object> reqMap = createNotificationRequest();
            ReturnData returnData = createSuccessReturnData();

            given(notificationService.sendNotification(any())).willReturn(returnData);

            ResponseEntity<ReturnData> response = controller.sendToDepartment("DEPT001", reqMap);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertFalse(response.getBody().getHasError());
        }
    }

    @Nested
    @DisplayName("sendToUser()")
    class SendToUser {

        @Test
        @DisplayName("사용자 알림 발송 성공시 200 OK를 반환한다")
        void returns200WhenUserNotificationSent() {
            Map<String, Object> reqMap = createNotificationRequest();
            ReturnData returnData = createSuccessReturnData();

            given(notificationService.sendNotification(any())).willReturn(returnData);

            ResponseEntity<ReturnData> response = controller.sendToUser("user123", reqMap);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertFalse(response.getBody().getHasError());
        }
    }

    private Map<String, Object> createNotificationRequest() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("type", "ACCIDENT_RECEIVED");
        reqMap.put("title", "새로운 사건 접수");
        reqMap.put("message", "새로운 사건이 접수되었습니다.");
        return reqMap;
    }

    private ReturnData createSuccessReturnData() {
        ReturnData returnData = new ReturnData();
        returnData.setResultData(NotificationResponseDto.builder()
                .id(UUID.randomUUID().toString())
                .type("ACCIDENT_RECEIVED")
                .title("새로운 사건 접수")
                .message("새로운 사건이 접수되었습니다.")
                .timestamp(LocalDateTime.now())
                .success(true)
                .build());
        return returnData;
    }
}

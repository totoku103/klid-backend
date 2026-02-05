package com.klid.webapp.realtimeNotify.notification.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.realtimeNotify.notification.dto.NotificationRequestDto;
import com.klid.webapp.realtimeNotify.notification.dto.NotificationResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RealtimeNotifyNotificationServiceImplTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    private RealtimeNotifyNotificationServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new RealtimeNotifyNotificationServiceImpl(messagingTemplate);
    }

    @Nested
    @DisplayName("sendNotification()")
    class SendNotification {

        @Test
        @DisplayName("브로드캐스트 알림이 전체 토픽으로 발송된다")
        void broadcastsToTopic() {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("type", "ACCIDENT_RECEIVED");
            reqMap.put("title", "새로운 사건 접수");
            reqMap.put("message", "사건이 접수되었습니다.");
            reqMap.put("broadcast", "true");

            ReturnData result = service.sendNotification(new Criterion(reqMap, false));

            assertFalse(result.getHasError());
            verify(messagingTemplate).convertAndSend(eq("/topic/notification"), any(NotificationResponseDto.class));
        }

        @Test
        @DisplayName("사용자 지정 알림이 사용자 큐로 발송된다")
        void sendsToUserQueue() {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("type", "ACCIDENT_UPDATED");
            reqMap.put("title", "사건 상태 변경");
            reqMap.put("message", "사건 상태가 변경되었습니다.");
            reqMap.put("userId", "user123");

            ReturnData result = service.sendNotification(new Criterion(reqMap, false));

            assertFalse(result.getHasError());
            verify(messagingTemplate).convertAndSendToUser(eq("user123"), eq("/queue/notification"), any(NotificationResponseDto.class));
        }

        @Test
        @DisplayName("기관 지정 알림이 기관 토픽으로 발송된다")
        void sendsToDepartmentTopic() {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("type", "GENERAL");
            reqMap.put("title", "기관 공지");
            reqMap.put("message", "기관 공지사항입니다.");
            reqMap.put("deptCode", "DEPT001");

            ReturnData result = service.sendNotification(new Criterion(reqMap, false));

            assertFalse(result.getHasError());
            verify(messagingTemplate).convertAndSend(eq("/topic/notification/dept/DEPT001"), any(NotificationResponseDto.class));
        }

        @Test
        @DisplayName("대상 미지정시 브로드캐스트로 발송된다")
        void defaultsToBroadcast() {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("type", "GENERAL");
            reqMap.put("title", "일반 알림");
            reqMap.put("message", "일반 알림입니다.");

            ReturnData result = service.sendNotification(new Criterion(reqMap, false));

            assertFalse(result.getHasError());
            verify(messagingTemplate).convertAndSend(eq("/topic/notification"), any(NotificationResponseDto.class));
        }

        @Test
        @DisplayName("응답에 알림 ID가 포함된다")
        void responseContainsNotificationId() {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("type", "TEST");
            reqMap.put("title", "테스트");
            reqMap.put("broadcast", "true");

            ReturnData result = service.sendNotification(new Criterion(reqMap, false));

            NotificationResponseDto response = (NotificationResponseDto) result.getResultData();
            assertNotNull(response.getId());
            assertNotNull(response.getTimestamp());
            assertTrue(response.isSuccess());
        }

        @Test
        @DisplayName("타입이 지정되지 않으면 GENERAL로 설정된다")
        void defaultsToGeneralType() {
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("title", "테스트");
            reqMap.put("broadcast", "true");

            ArgumentCaptor<NotificationResponseDto> captor = ArgumentCaptor.forClass(NotificationResponseDto.class);

            service.sendNotification(new Criterion(reqMap, false));

            verify(messagingTemplate).convertAndSend(eq("/topic/notification"), captor.capture());
            assertEquals("GENERAL", captor.getValue().getType());
        }
    }

    @Nested
    @DisplayName("broadcastNotification()")
    class BroadcastNotification {

        @Test
        @DisplayName("브로드캐스트 메서드가 전체 토픽으로 메시지를 발송한다")
        void sendsToAllTopic() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .type("ACCIDENT_RECEIVED")
                    .title("새로운 사건")
                    .message("새로운 사건이 접수되었습니다.")
                    .build();

            service.broadcastNotification(request);

            verify(messagingTemplate).convertAndSend(eq("/topic/notification"), any(NotificationResponseDto.class));
        }
    }

    @Nested
    @DisplayName("sendToUser()")
    class SendToUser {

        @Test
        @DisplayName("특정 사용자에게 알림을 발송한다")
        void sendsToSpecificUser() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .type("PERSONAL")
                    .title("개인 알림")
                    .message("개인 알림입니다.")
                    .build();

            service.sendToUser("targetUser", request);

            verify(messagingTemplate).convertAndSendToUser(
                    eq("targetUser"),
                    eq("/queue/notification"),
                    any(NotificationResponseDto.class)
            );
        }
    }

    @Nested
    @DisplayName("sendToDepartment()")
    class SendToDepartment {

        @Test
        @DisplayName("특정 기관에 알림을 발송한다")
        void sendsToSpecificDepartment() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .type("DEPT_NOTICE")
                    .title("기관 공지")
                    .message("기관 공지사항입니다.")
                    .build();

            service.sendToDepartment("DEPT123", request);

            verify(messagingTemplate).convertAndSend(
                    eq("/topic/notification/dept/DEPT123"),
                    any(NotificationResponseDto.class)
            );
        }
    }
}

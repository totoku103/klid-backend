package com.klid.webapp.realtimeNotify.notification.handler;

import com.klid.webapp.realtimeNotify.notification.dto.NotificationRequestDto;
import com.klid.webapp.realtimeNotify.notification.service.RealtimeNotifyNotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RealtimeNotifyNotificationHandlerTest {

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @Mock
    private RealtimeNotifyNotificationService notificationService;

    private RealtimeNotifyNotificationHandler handler;

    @BeforeEach
    void setUp() {
        handler = new RealtimeNotifyNotificationHandler(messagingTemplate, notificationService);
    }

    @Nested
    @DisplayName("handleNotification()")
    class HandleNotification {

        @Test
        @DisplayName("브로드캐스트 요청시 브로드캐스트 메서드를 호출한다")
        void callsBroadcastForBroadcastRequest() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .type("GENERAL")
                    .title("전체 알림")
                    .message("전체 알림입니다.")
                    .broadcast(true)
                    .build();

            handler.handleNotification(request);

            verify(notificationService).broadcastNotification(request);
        }

        @Test
        @DisplayName("사용자 지정시 사용자 발송 메서드를 호출한다")
        void callsSendToUserForUserRequest() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .type("PERSONAL")
                    .title("개인 알림")
                    .message("개인 알림입니다.")
                    .userId("user123")
                    .build();

            handler.handleNotification(request);

            verify(notificationService).sendToUser("user123", request);
        }

        @Test
        @DisplayName("기관 지정시 기관 발송 메서드를 호출한다")
        void callsSendToDepartmentForDeptRequest() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .type("DEPT_NOTICE")
                    .title("기관 공지")
                    .message("기관 공지입니다.")
                    .deptCode("DEPT001")
                    .build();

            handler.handleNotification(request);

            verify(notificationService).sendToDepartment("DEPT001", request);
        }

        @Test
        @DisplayName("대상 미지정시 브로드캐스트를 호출한다")
        void callsBroadcastForNoTarget() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .type("GENERAL")
                    .title("일반 알림")
                    .message("일반 알림입니다.")
                    .build();

            handler.handleNotification(request);

            verify(notificationService).broadcastNotification(request);
        }
    }

    @Nested
    @DisplayName("handleAccidentReceived()")
    class HandleAccidentReceived {

        @Test
        @DisplayName("사건 접수 알림 타입이 ACCIDENT_RECEIVED로 설정된다")
        void setsTypeToAccidentReceived() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .title("사건 접수")
                    .message("새로운 사건이 접수되었습니다.")
                    .build();

            ArgumentCaptor<NotificationRequestDto> captor = ArgumentCaptor.forClass(NotificationRequestDto.class);

            handler.handleAccidentReceived(request);

            verify(notificationService).broadcastNotification(captor.capture());
            assertEquals("ACCIDENT_RECEIVED", captor.getValue().getType());
        }

        @Test
        @DisplayName("사건 접수 알림은 브로드캐스트된다")
        void broadcastsAccidentReceived() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .title("사건 접수")
                    .message("새로운 사건이 접수되었습니다.")
                    .build();

            handler.handleAccidentReceived(request);

            verify(notificationService).broadcastNotification(any());
        }
    }

    @Nested
    @DisplayName("handleAccidentUpdated()")
    class HandleAccidentUpdated {

        @Test
        @DisplayName("사건 상태 변경 알림 타입이 ACCIDENT_UPDATED로 설정된다")
        void setsTypeToAccidentUpdated() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .title("사건 상태 변경")
                    .message("사건 상태가 변경되었습니다.")
                    .userId("user123")
                    .build();

            handler.handleAccidentUpdated(request);

            assertEquals("ACCIDENT_UPDATED", request.getType());
        }

        @Test
        @DisplayName("사용자 지정시 해당 사용자에게 알림을 발송한다")
        void sendsToUserWhenUserIdSpecified() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .title("사건 상태 변경")
                    .message("사건 상태가 변경되었습니다.")
                    .userId("user123")
                    .build();

            handler.handleAccidentUpdated(request);

            verify(notificationService).sendToUser(eq("user123"), any());
        }

        @Test
        @DisplayName("기관 지정시 해당 기관에 알림을 발송한다")
        void sendsToDepartmentWhenDeptCodeSpecified() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .title("사건 상태 변경")
                    .message("사건 상태가 변경되었습니다.")
                    .deptCode("DEPT001")
                    .build();

            handler.handleAccidentUpdated(request);

            verify(notificationService).sendToDepartment(eq("DEPT001"), any());
        }

        @Test
        @DisplayName("사용자와 기관 모두 지정시 둘 다에게 알림을 발송한다")
        void sendsToBothWhenBothSpecified() {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .title("사건 상태 변경")
                    .message("사건 상태가 변경되었습니다.")
                    .userId("user123")
                    .deptCode("DEPT001")
                    .build();

            handler.handleAccidentUpdated(request);

            verify(notificationService).sendToUser(eq("user123"), any());
            verify(notificationService).sendToDepartment(eq("DEPT001"), any());
        }
    }
}

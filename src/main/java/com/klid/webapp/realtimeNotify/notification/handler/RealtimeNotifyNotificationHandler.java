package com.klid.webapp.realtimeNotify.notification.handler;

import com.klid.webapp.realtimeNotify.notification.dto.NotificationRequestDto;
import com.klid.webapp.realtimeNotify.notification.service.RealtimeNotifyNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * RealtimeNotify Notification STOMP Handler
 * WebSocket STOMP 메시지 처리
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class RealtimeNotifyNotificationHandler {

    private final SimpMessagingTemplate messagingTemplate;
    private final RealtimeNotifyNotificationService realtimeNotifyNotificationService;

    /**
     * 알림 발송 메시지 핸들러
     * 클라이언트에서 /app/notification/send로 메시지 전송 시 처리
     */
    @MessageMapping("/notification/send")
    public void handleNotification(@Payload NotificationRequestDto request) {
        log.info("STOMP 알림 수신: type={}, title={}", request.getType(), request.getTitle());

        try {
            if (request.isBroadcast()) {
                realtimeNotifyNotificationService.broadcastNotification(request);
            } else if (request.getUserId() != null && !request.getUserId().isEmpty()) {
                realtimeNotifyNotificationService.sendToUser(request.getUserId(), request);
            } else if (request.getDeptCode() != null && !request.getDeptCode().isEmpty()) {
                realtimeNotifyNotificationService.sendToDepartment(request.getDeptCode(), request);
            } else {
                // 기본: 브로드캐스트
                realtimeNotifyNotificationService.broadcastNotification(request);
            }
        } catch (Exception e) {
            log.error("STOMP 알림 처리 중 오류 발생", e);
        }
    }

    /**
     * 사건 접수 알림
     * 클라이언트에서 /app/accident/received로 메시지 전송 시 처리
     */
    @MessageMapping("/accident/received")
    public void handleAccidentReceived(@Payload NotificationRequestDto request) {
        log.info("사건 접수 알림: {}", request.getTitle());

        request.setType("ACCIDENT_RECEIVED");
        realtimeNotifyNotificationService.broadcastNotification(request);
    }

    /**
     * 사건 상태 변경 알림
     * 클라이언트에서 /app/accident/updated로 메시지 전송 시 처리
     */
    @MessageMapping("/accident/updated")
    public void handleAccidentUpdated(@Payload NotificationRequestDto request) {
        log.info("사건 상태 변경 알림: {}", request.getTitle());

        request.setType("ACCIDENT_UPDATED");

        // 담당자에게 개인 알림
        if (request.getUserId() != null && !request.getUserId().isEmpty()) {
            realtimeNotifyNotificationService.sendToUser(request.getUserId(), request);
        }

        // 기관에 알림
        if (request.getDeptCode() != null && !request.getDeptCode().isEmpty()) {
            realtimeNotifyNotificationService.sendToDepartment(request.getDeptCode(), request);
        }
    }
}

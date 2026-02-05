package com.klid.webapp.realtimeNotify.notification.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ErrorInfo;
import com.klid.webapp.common.MsgService;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.realtimeNotify.notification.dto.NotificationRequestDto;
import com.klid.webapp.realtimeNotify.notification.dto.NotificationResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * RealtimeNotify Notification Service Implementation
 * WebSocket STOMP를 통한 실시간 알림 발송
 */
@Slf4j
@Service("realtimeNotifyNotificationService")
@RequiredArgsConstructor
public class RealtimeNotifyNotificationServiceImpl extends MsgService implements RealtimeNotifyNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    private static final String TOPIC_BROADCAST = "/topic/notification";
    private static final String TOPIC_DEPARTMENT = "/topic/notification/dept/";
    private static final String QUEUE_USER = "/queue/notification";

    @Override
    public ReturnData sendNotification(Criterion criterion) {
        ReturnData returnData = new ReturnData();

        try {
            NotificationRequestDto request = NotificationRequestDto.builder()
                    .type(criterion.getValue("type") != null ? criterion.getValue("type").toString() : "GENERAL")
                    .title(criterion.getValue("title") != null ? criterion.getValue("title").toString() : "")
                    .message(criterion.getValue("message") != null ? criterion.getValue("message").toString() : "")
                    .userId(criterion.getValue("userId") != null ? criterion.getValue("userId").toString() : null)
                    .deptCode(criterion.getValue("deptCode") != null ? criterion.getValue("deptCode").toString() : null)
                    .data(criterion.getValue("data"))
                    .broadcast(criterion.getValue("broadcast") != null && Boolean.parseBoolean(criterion.getValue("broadcast").toString()))
                    .build();

            NotificationResponseDto response;

            if (request.isBroadcast()) {
                broadcastNotification(request);
                response = createSuccessResponse(request);
            } else if (request.getUserId() != null && !request.getUserId().isEmpty()) {
                sendToUser(request.getUserId(), request);
                response = createSuccessResponse(request);
            } else if (request.getDeptCode() != null && !request.getDeptCode().isEmpty()) {
                sendToDepartment(request.getDeptCode(), request);
                response = createSuccessResponse(request);
            } else {
                // 대상 미지정시 브로드캐스트
                broadcastNotification(request);
                response = createSuccessResponse(request);
            }

            returnData.setResultData(response);

        } catch (Exception e) {
            log.error("알림 발송 중 오류 발생", e);
            returnData.setErrorInfo(new ErrorInfo("ERR_NOTIFICATION", "알림 발송 중 오류가 발생했습니다: " + e.getMessage()));
        }

        return returnData;
    }

    @Override
    public void broadcastNotification(NotificationRequestDto request) {
        NotificationResponseDto response = createSuccessResponse(request);
        messagingTemplate.convertAndSend(TOPIC_BROADCAST, response);
        log.info("브로드캐스트 알림 발송: type={}, title={}", request.getType(), request.getTitle());
    }

    @Override
    public void sendToUser(String userId, NotificationRequestDto request) {
        NotificationResponseDto response = createSuccessResponse(request);
        messagingTemplate.convertAndSendToUser(userId, QUEUE_USER, response);
        log.info("사용자 알림 발송: userId={}, type={}", userId, request.getType());
    }

    @Override
    public void sendToDepartment(String deptCode, NotificationRequestDto request) {
        NotificationResponseDto response = createSuccessResponse(request);
        messagingTemplate.convertAndSend(TOPIC_DEPARTMENT + deptCode, response);
        log.info("기관 알림 발송: deptCode={}, type={}", deptCode, request.getType());
    }

    private NotificationResponseDto createSuccessResponse(NotificationRequestDto request) {
        return NotificationResponseDto.builder()
                .id(UUID.randomUUID().toString())
                .type(request.getType())
                .title(request.getTitle())
                .message(request.getMessage())
                .data(request.getData())
                .timestamp(LocalDateTime.now())
                .success(true)
                .build();
    }
}

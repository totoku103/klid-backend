package com.klid.realtimeNotify.notification.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.realtimeNotify.notification.dto.NotificationRequestDto;

/**
 * RealtimeNotify Notification Service Interface
 */
public interface RealtimeNotifyNotificationService {

    /**
     * 알림 발송 (REST API 호출용)
     * @param criterion 알림 파라미터
     * @return 발송 결과
     */
    ReturnData sendNotification(Criterion criterion);

    /**
     * 브로드캐스트 알림 발송
     * @param request 알림 요청
     */
    void broadcastNotification(NotificationRequestDto request);

    /**
     * 특정 사용자에게 알림 발송
     * @param userId 대상 사용자 ID
     * @param request 알림 요청
     */
    void sendToUser(String userId, NotificationRequestDto request);

    /**
     * 특정 기관에 알림 발송
     * @param deptCode 대상 기관 코드
     * @param request 알림 요청
     */
    void sendToDepartment(String deptCode, NotificationRequestDto request);
}

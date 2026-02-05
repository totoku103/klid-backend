package com.klid.webapp.realtimeNotify.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 알림 요청 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDto {

    /**
     * 알림 타입 (ACCIDENT_RECEIVED, ACCIDENT_UPDATED, etc.)
     */
    private String type;

    /**
     * 알림 제목
     */
    private String title;

    /**
     * 알림 메시지
     */
    private String message;

    /**
     * 대상 사용자 ID (개인 알림용)
     */
    private String userId;

    /**
     * 대상 기관 코드 (기관 알림용)
     */
    private String deptCode;

    /**
     * 관련 데이터 (사건번호, 링크 등)
     */
    private Object data;

    /**
     * 브로드캐스트 여부 (전체 알림)
     */
    private boolean broadcast;
}

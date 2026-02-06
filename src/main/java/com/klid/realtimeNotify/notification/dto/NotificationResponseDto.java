package com.klid.realtimeNotify.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 알림 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDto {

    /**
     * 알림 ID
     */
    private String id;

    /**
     * 알림 타입
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
     * 관련 데이터
     */
    private Object data;

    /**
     * 발송 시간
     */
    private LocalDateTime timestamp;

    /**
     * 발송 성공 여부
     */
    private boolean success;
}

package com.klid.realtimeNotify.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SMS 발송 응답 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsSendResponseDto {

    /**
     * 발송 성공 여부
     */
    private boolean success;

    /**
     * 발송된 메시지 수
     */
    private int sentCount;

    /**
     * 메시지
     */
    private String message;

    /**
     * 에러 코드 (실패시)
     */
    private String errorCode;
}

package com.klid.webapp.realtimeNotify.sms.dto;

import lombok.Data;

import java.util.List;

/**
 * SMS 발송 요청 DTO
 */
@Data
public class SmsSendRequestDto {

    /**
     * 메시지 내용
     */
    private String msg;

    /**
     * 메시지 제목 (LMS/MMS용)
     */
    private String subject;

    /**
     * 발신자 전화번호
     */
    private String sender;

    /**
     * 수신자 목록
     */
    private List<Receiver> recv;

    /**
     * 발송자 ID
     */
    private String userId;

    @Data
    public static class Receiver {
        /**
         * 수신자 이름
         */
        private String userName;

        /**
         * 수신자 전화번호
         */
        private String phone;
    }
}

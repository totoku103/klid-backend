package com.klid.api.system.sms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * SMS 메시지 전송 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class SmsMessageRequest {
    private String type;                    // 메시지 타입 (선택적)
    private String msg;                     // 메시지 내용
    private String sender;                  // 발신자 전화번호
    private List<SmsRecipient> recipients;  // 수신자 목록
}

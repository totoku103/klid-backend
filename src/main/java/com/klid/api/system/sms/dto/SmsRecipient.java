package com.klid.api.system.sms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SMS 수신자 정보 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class SmsRecipient {
    private String userName;  // 수신자 이름
    private String phone;     // 수신자 전화번호
}

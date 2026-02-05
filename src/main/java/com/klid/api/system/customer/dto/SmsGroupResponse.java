package com.klid.api.system.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SMS 그룹 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class SmsGroupResponse {
    private String groupId;
    private String groupName;
    private String groupDesc;
    private String useYn;
    private Integer memberCount;
    private String regDate;
    private String regUser;
}

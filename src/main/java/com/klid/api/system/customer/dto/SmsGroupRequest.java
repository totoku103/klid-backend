package com.klid.api.system.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * SMS 그룹 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class SmsGroupRequest {
    private String groupId;
    private String groupName;
    private String groupDesc;
    private String useYn;
    private List<String> userIds;
}

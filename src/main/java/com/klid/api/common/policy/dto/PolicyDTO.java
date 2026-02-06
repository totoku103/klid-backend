package com.klid.api.common.policy.dto;

import lombok.Data;

/**
 * 정책 설정 DTO
 */
@Data
public class PolicyDTO {
    private String policyKind;
    private String policyName;
    private String val1;
    private String val2;
    private String valLob1;
}

package com.klid.api.system.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 외부 사용자 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class CustomerUserRequest {
    private String userId;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String instCode;
    private String deptName;
    private String useYn;
}

package com.klid.api.system.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 외부 사용자 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class CustomerUserResponse {
    private String userId;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String instCode;
    private String instName;
    private String deptName;
    private String useYn;
    private String regDate;
}

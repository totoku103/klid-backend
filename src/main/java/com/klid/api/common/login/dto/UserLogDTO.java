package com.klid.api.common.login.dto;

import lombok.Data;

/**
 * 사용자 로그 정보 DTO
 */
@Data
public class UserLogDTO {
    private String userId;
    private String logDt;
    private String logCd;
    private String usrIp;
    private String menuCd;
    private String remark;
}

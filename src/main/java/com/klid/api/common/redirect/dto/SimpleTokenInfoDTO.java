package com.klid.api.common.redirect.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SimpleTokenInfoDTO {
    private Long idx;
    private String userId;
    private String clientIp;
    private String systemType;
    private String token;
    private Date createdAt;
    private Date expiredAt;
    private String status;
    private String isUsed;
    private Date updatedAt;
}

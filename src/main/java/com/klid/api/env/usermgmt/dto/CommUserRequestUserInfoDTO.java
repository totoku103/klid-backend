package com.klid.api.env.usermgmt.dto;

import lombok.Data;

@Data
public class CommUserRequestUserInfoDTO {
    private CommUserDTO userInfo;
    private Integer requestUserSeq;
    private String requestInstCd;
    private String requestType;
    private String requestReason;
    private String requestProcessState;
}

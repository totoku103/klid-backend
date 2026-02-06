package com.klid.api.env.usermgmthist.dto;

import lombok.Data;

@Data
public class StandByRegUserIdDTO {
    private Integer seq;
    private String userId;
    private String requestType;
    private String latestProcessState;
    private String childSeq;
}

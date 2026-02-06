package com.klid.api.env.usermgmthist.dto;

import lombok.Data;

@Data
public class LatestCommUserRequestProcessStateDTO {
    private Integer rootSeq;
    private Integer latestSeq;
    private String latestProcessState;
}

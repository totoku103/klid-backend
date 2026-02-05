package com.klid.api.webdash.admin.dto;

import lombok.Data;

@Data
public class LocalStatusDTO {
    private int localCd;
    private String localNm;
    private int forgeryEvt;
    private int hcEvt;
}

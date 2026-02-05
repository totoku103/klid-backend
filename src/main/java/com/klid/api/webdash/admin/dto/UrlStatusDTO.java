package com.klid.api.webdash.admin.dto;

import lombok.Data;

@Data
public class UrlStatusDTO {
    private int depthCnt;
    private int normalCnt;
    private int forgeryCnt;
    private int errorCnt;
}

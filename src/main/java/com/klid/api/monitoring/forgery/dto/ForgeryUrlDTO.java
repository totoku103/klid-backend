package com.klid.api.monitoring.forgery.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 위변조 URL DTO
 */
@Getter
@Setter
public class ForgeryUrlDTO {
    private String forgerySeq;
    private int instCd;
    private String instNm;
    private String pLocalNm;
    private int localCd;
    private int instLevel;
    private int pntInstCd;
    private String sIp;
    private String dIp;
    private String sPort;
    private String dPort;
    private int depth;
    private String excpYn;
    private String delYn;
    private String checkYn;
    private String wsisIp;
    private String url;
    private String domain;
    private String hmCnt;
    private String foCnt;

    private int healthNormalCnt;
    private int healthErrCnt;
    private int urlNormalCnt;
    private int urlErrCnt;
    private int lastRes;

    private String detectTime;
    private String evtName;
    private String depthRes;
}

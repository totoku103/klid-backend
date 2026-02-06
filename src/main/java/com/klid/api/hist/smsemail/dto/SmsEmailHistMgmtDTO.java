package com.klid.api.hist.smsemail.dto;

import lombok.Data;

@Data
public class SmsEmailHistMgmtDTO {
    private String ymdhms;
    private String yyyymmdd;
    private String hhmmss;
    private String cellNo;
    private String shortMsg;
    private String planDate;
    private String sndFlag;
    private String sendNo;
    private String cellName;
    private String planFlag;
    private String sndDate;
    private String mailSnd;
    private String mailId;
    private String mailTitle;
    private String mailMsg;
    private String mailSndDate;
    private String retryCount;
    private String sndLoc;
    private String sendName;
}

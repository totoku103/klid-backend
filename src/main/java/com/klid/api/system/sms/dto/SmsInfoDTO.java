package com.klid.api.system.sms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsInfoDTO {
    // SMS Info fields (from resultMap)
    private String smsIp;
    private String smsPort;
    private String smsUser;
    private String smsPwd;
    private String smsSid;

    // SMS History fields (from insert statement)
    private String yyyymmdd;
    private String hhmmss;
    private String cellNo;
    private String contents;
    private String sendNo;
    private String cellName;
    private String sendUserId;
}

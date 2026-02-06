package com.klid.api.env.usermgmthist.dto;

import lombok.Data;

@Data
public class HistorySearchDTO {
    private String searchDateFrom;
    private String searchDateTo;
    private String searchRequestType;
    private String searchInstitutionCode;
    private String searchProcessState;
    private String searchUserName;
}

package com.klid.api.logs.action.dto;

import com.klid.api.logs.common.dto.ThirdPartySystemType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InstitutionSearchReqDTO {
    private ThirdPartySystemType systemType;
    private String month;
    private String institutionCode;
    private Integer codeLevel;
}

package com.klid.api.logs.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutionCodeResDTO {
    private Integer level;
    private String institutionCode;
    private String institutionName;
    private String parentInstitutionCode;
}

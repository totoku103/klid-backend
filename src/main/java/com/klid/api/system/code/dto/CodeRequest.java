package com.klid.api.system.code.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 코드 등록/수정 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class CodeRequest {
    private String codeType;
    private String codeId;
    private String codeName;
    private String codeDesc;
    private String useYn;
    private Integer sortOrder;
    private String parentCode;
}

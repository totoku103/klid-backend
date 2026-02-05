package com.klid.api.system.code.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 코드 조회 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class CodeResponse {
    private String codeType;
    private String codeId;
    private String codeName;
    private String codeDesc;
    private String useYn;
    private Integer sortOrder;
    private String parentCode;
    private String regDate;
    private String regUser;
    private String modDate;
    private String modUser;
}

package com.klid.api.common.group.dto;

import lombok.Data;

/**
 * 권한 그룹 정보 DTO
 */
@Data
public class AuthGroupDTO {
    private String authGrpNo;
    private String authGrpName;
    private String authGrpDesc;
}

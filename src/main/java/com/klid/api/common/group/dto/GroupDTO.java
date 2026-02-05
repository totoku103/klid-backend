package com.klid.api.common.group.dto;

import lombok.Data;

/**
 * 그룹 정보 DTO
 *
 * <p>트리 구조의 그룹 정보를 표현합니다.</p>
 */
@Data
public class GroupDTO {
    private String grpNo;
    private long grpParent;
    private long grpRef;
    private String grpRefString;
    private String grpName;
    private int isLeaf;
    private String devKind2;
    private String devIp;
    private int grpFlag;
}

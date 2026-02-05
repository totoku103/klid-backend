package com.klid.api.logs.common.dto;

public interface InstitutionSummaryGridResDTO {

    Integer getDepthLevel();

    void setDepthLevel(Integer depthLevel);

    String getInstitutionCode();

    void setInstitutionCode(String institutionCode);

    String getInstitutionName();

    void setInstitutionName(String institutionName);

    String getParentInstitutionCode();

    void setParentInstitutionCode(String parentInstitutionCode);

    String getFullPathName();

    void setFullPathName(String fullPathName);

    Integer getTotalCount();

    void setTotalCount(Integer totalCount);
}

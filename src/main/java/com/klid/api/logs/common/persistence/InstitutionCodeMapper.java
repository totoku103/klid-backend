package com.klid.api.logs.common.persistence;

import com.klid.api.logs.common.dto.InstitutionCodeResDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionCodeMapper {

    List<InstitutionCodeResDTO> selectCTRSInstitutionCodeList();

    List<InstitutionCodeResDTO> selectOtherInstitutionCodeList(@Param("systemType") String systemType);
}

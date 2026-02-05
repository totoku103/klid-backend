package com.klid.api.common.code.persistence;

import com.klid.api.common.code.dto.CommonCodeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommonCodeMapper {

    List<CommonCodeDTO> selectCommonCode(
            @Param("comCode1") String comCode1,
            @Param("comCode2") String comCode2,
            @Param("comCode3") String comCode3,
            @Param("codeLvl") Integer codeLvl
    );
}

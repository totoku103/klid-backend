package com.klid.api.report.incitype.persistence;

import com.klid.api.report.incitype.dto.ReportInciTypeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportInciTypeMapper {

    List<ReportInciTypeDTO> selectInciTypeList(@Param("sAuthMain") String sAuthMain,
                                                @Param("instCd") String instCd,
                                                @Param("dateType") String dateType,
                                                @Param("startDt") String startDt,
                                                @Param("endDt") String endDt);
}

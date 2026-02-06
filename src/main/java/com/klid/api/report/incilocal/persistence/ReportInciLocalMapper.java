package com.klid.api.report.incilocal.persistence;

import com.klid.api.report.incilocal.dto.ReportInciLocalDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportInciLocalMapper {

    List<ReportInciLocalDTO> selectInciLocalList(@Param("dateType") String dateType,
                                                  @Param("startDt") String startDt,
                                                  @Param("endDt") String endDt,
                                                  @Param("sortType") String sortType);

    List<ReportInciLocalDTO> selectInciSidoList(@Param("sAuthMain") String sAuthMain,
                                                 @Param("instCd") String instCd,
                                                 @Param("dateType") String dateType,
                                                 @Param("startDt") String startDt,
                                                 @Param("endDt") String endDt,
                                                 @Param("topInstView") String topInstView);
}

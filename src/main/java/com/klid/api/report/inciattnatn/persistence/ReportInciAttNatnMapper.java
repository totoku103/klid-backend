package com.klid.api.report.inciattnatn.persistence;

import com.klid.api.report.inciattnatn.dto.ReportInciAttNatnDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportInciAttNatnMapper {

    List<ReportInciAttNatnDTO> selectAttNatnList(@Param("sAuthMain") String sAuthMain,
                                                  @Param("instCd") String instCd,
                                                  @Param("dateType") String dateType,
                                                  @Param("startDt") String startDt,
                                                  @Param("endDt") String endDt,
                                                  @Param("topSize") Integer topSize);
}

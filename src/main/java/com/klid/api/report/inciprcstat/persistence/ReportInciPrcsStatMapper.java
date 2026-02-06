package com.klid.api.report.inciprcstat.persistence;

import com.klid.api.report.inciprcstat.dto.ReportInciPrcsStatDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportInciPrcsStatMapper {

    List<ReportInciPrcsStatDTO> selectInciPrcsStat(@Param("sAuthMain") String sAuthMain,
                                                    @Param("instCd") String instCd,
                                                    @Param("dateType") String dateType,
                                                    @Param("startDt") String startDt,
                                                    @Param("endDt") String endDt);
}

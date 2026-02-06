package com.klid.api.report.dailyincistate.persistence;

import com.klid.api.report.dailyincistate.dto.ReportDailyInciStateDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDailyInciStateMapper {

    List<ReportDailyInciStateDTO> selectReportDayInciProc(@Param("sAuthMain") String sAuthMain,
                                                           @Param("endDt") String endDt,
                                                           @Param("startDt") String startDt,
                                                           @Param("localCd") String localCd,
                                                           @Param("instCd") String instCd);

    List<ReportDailyInciStateDTO> selectReportSumInci(@Param("sAuthMain") String sAuthMain,
                                                       @Param("sumStartDt") String sumStartDt,
                                                       @Param("sumEndDt") String sumEndDt,
                                                       @Param("localCd") String localCd,
                                                       @Param("instCd") String instCd);
}

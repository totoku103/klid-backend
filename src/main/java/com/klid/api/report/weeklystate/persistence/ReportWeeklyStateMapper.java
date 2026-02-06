package com.klid.api.report.weeklystate.persistence;

import com.klid.api.report.weeklystate.dto.ReportDailyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportWeeklyStateMapper {

    List<ReportDailyDTO> selectReportWeekType(@Param("sAuthMain") String sAuthMain,
                                               @Param("sInstCd") String sInstCd,
                                               @Param("startDt") String startDt,
                                               @Param("endDt") String endDt);

    ReportDailyDTO selectReportWeekStat(@Param("sAuthMain") String sAuthMain,
                                         @Param("sInstCd") String sInstCd,
                                         @Param("startDt") String startDt,
                                         @Param("endDt") String endDt);

    ReportDailyDTO selectReportYearSumStat(@Param("sAuthMain") String sAuthMain,
                                            @Param("sInstCd") String sInstCd,
                                            @Param("sumDay") String sumDay,
                                            @Param("sumEndDt") String sumEndDt);

    List<ReportDailyDTO> selectReportTypeSum(@Param("sAuthMain") String sAuthMain,
                                              @Param("sInstCd") String sInstCd,
                                              @Param("sumDay") String sumDay,
                                              @Param("sumEndDt") String sumEndDt);

    ReportDailyDTO selectReportWeekSum(@Param("sAuthMain") String sAuthMain,
                                        @Param("sInstCd") String sInstCd,
                                        @Param("startDt") String startDt,
                                        @Param("endDt") String endDt,
                                        @Param("sumDay") String sumDay,
                                        @Param("sumEndDt") String sumEndDt);
}

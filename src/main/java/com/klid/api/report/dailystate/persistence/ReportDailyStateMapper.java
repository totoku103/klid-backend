package com.klid.api.report.dailystate.persistence;

import com.klid.api.report.dailystate.dto.ReportDailyStateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportDailyStateMapper {

    List<ReportDailyStateDTO> selectReportDayType(@Param("sAuthMain") String sAuthMain,
                                                   @Param("startDt") String startDt,
                                                   @Param("endDt") String endDt,
                                                   @Param("sInstCd") String sInstCd);

    List<ReportDailyStateDTO> selectReportNcsc(@Param("startNcscDt") String startNcscDt,
                                                @Param("endNcscDt") String endNcscDt);

    ReportDailyStateDTO selectReportDayStat(@Param("sAuthMain") String sAuthMain,
                                            @Param("startDt") String startDt,
                                            @Param("endDt") String endDt,
                                            @Param("sInstCd") String sInstCd);

    ReportDailyStateDTO selectReportSumStat(@Param("sAuthMain") String sAuthMain,
                                            @Param("sumDay") String sumDay,
                                            @Param("sumEndDt") String sumEndDt,
                                            @Param("sInstCd") String sInstCd,
                                            @Param("sYear") String sYear);

    ReportDailyStateDTO selectReportSum(@Param("sAuthMain") String sAuthMain,
                                        @Param("startDt") String startDt,
                                        @Param("endDt") String endDt,
                                        @Param("sumDay") String sumDay,
                                        @Param("sumEndDt") String sumEndDt,
                                        @Param("sInstCd") String sInstCd,
                                        @Param("sYear") String sYear);

    List<ReportDailyStateDTO> selectReportTypeSum(@Param("sAuthMain") String sAuthMain,
                                                   @Param("sumDay") String sumDay,
                                                   @Param("sumEndDt") String sumEndDt,
                                                   @Param("sInstCd") String sInstCd,
                                                   @Param("sYear") String sYear);
}

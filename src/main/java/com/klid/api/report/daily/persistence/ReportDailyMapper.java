package com.klid.api.report.daily.persistence;

import com.klid.api.report.daily.dto.ReportDailyDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDailyMapper {

    ReportDailyDTO selectReportDayStat(@Param("occrDt") String occrDt,
                                       @Param("instCd") String instCd);

    ReportDailyDTO selectReportDayTotal(@Param("startDt") String startDt,
                                        @Param("endDt") String endDt,
                                        @Param("instCd") String instCd);

    ReportDailyDTO selectReportSumStat(@Param("startDt") String startDt,
                                       @Param("endDt") String endDt,
                                       @Param("instCd") String instCd);

    ReportDailyDTO selectReportEndCnt(@Param("occrDt") String occrDt,
                                      @Param("endDt") String endDt,
                                      @Param("instCd") String instCd);

    List<ReportDailyDTO> selectReportDayType(@Param("occrDt") String occrDt,
                                             @Param("instCd") String instCd);

    List<ReportDailyDTO> selectReportSumType(@Param("startDt") String startDt,
                                             @Param("endDt") String endDt,
                                             @Param("instCd") String instCd);

    ReportDailyDTO selectReportTmsDate(@Param("occrDt") String occrDt);

    ReportDailyDTO selectReportDayTms(@Param("occrDt") String occrDt,
                                      @Param("occrDt2") String occrDt2,
                                      @Param("totalDt") String totalDt);

    List<ReportDailyDTO> selectReportDayInciSum(@Param("startDt") String startDt,
                                                @Param("endDt") String endDt,
                                                @Param("InciTrnsInstCd") String InciTrnsInstCd,
                                                @Param("instCd") String instCd,
                                                @Param("localCd") String localCd);

    List<ReportDailyDTO> selectReportSumInci(@Param("startDt") String startDt,
                                             @Param("endDt") String endDt,
                                             @Param("InciTrnsInstCd") String InciTrnsInstCd,
                                             @Param("instCd") String instCd,
                                             @Param("localCd") String localCd);

    ReportDailyDTO selectReportYearSumStat(@Param("startDt") String startDt,
                                           @Param("endDt") String endDt,
                                           @Param("instCd") String instCd);

    ReportDailyDTO selectReportWeekEndCnt(@Param("startDt") String startDt,
                                          @Param("endDt") String endDt,
                                          @Param("instCd") String instCd);

    List<ReportDailyDTO> selectReportWeekType(@Param("startDt") String startDt,
                                              @Param("endDt") String endDt,
                                              @Param("instCd") String instCd);

    ReportDailyDTO selectReportResultTotal(@Param("startDt") String startDt,
                                           @Param("endDt") String endDt,
                                           @Param("instCd") String instCd);

    List<ReportDailyDTO> selectReportResultList(@Param("startDt") String startDt,
                                                @Param("endDt") String endDt,
                                                @Param("instCd") String instCd);

    List<ReportDailyDTO> selectReportResultExceptlist(@Param("startDt") String startDt,
                                                      @Param("endDt") String endDt);

    ReportDailyDTO selectReportWeekTms(@Param("occrDt1") String occrDt1,
                                       @Param("occrDt3") String occrDt3,
                                       @Param("totalSDt") String totalSDt,
                                       @Param("totalEDt") String totalEDt);

    List<ReportDailyDTO> selectReportDayInciProc(@Param("occrDt") String occrDt,
                                                 @Param("inciTrnsInstCd") String inciTrnsInstCd,
                                                 @Param("instCd") String instCd,
                                                 @Param("localCd") String localCd);
}

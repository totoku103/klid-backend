package com.klid.api.report.collection.persistence;

import com.klid.api.report.collection.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportCollectionMapper {

    List<ReportHackingDTO> getRetrieveSecurityHackingDetail(@Param("startDt") String startDt,
                                                             @Param("endDt") String endDt,
                                                             @Param("inciNo") String inciNo,
                                                             @Param("instNm") String instNm,
                                                             @Param("hackIp") String hackIp,
                                                             @Param("attackTypeNm") String attackTypeNm,
                                                             @Param("hackCont") String hackCont);

    List<ReportSecurityVulnerabilityDTO> getRetrieveSecurityVulnerabilityDetail(@Param("startDt") String startDt,
                                                                                  @Param("endDt") String endDt,
                                                                                  @Param("instNm") String instNm,
                                                                                  @Param("attackTypeCd") String attackTypeCd);

    List<ReportNoticeDTO> getNoticeListDetail(@Param("startDt") String startDt,
                                                @Param("endDt") String endDt);

    List<ReportSecurityDataDTO> getSecuListDetail(@Param("startDt") String startDt,
                                                    @Param("endDt") String endDt);

    List<ReportDailyDTO> getRetrieveIncidentDetail(@Param("startDt") String startDt,
                                                    @Param("endDt") String endDt,
                                                    @Param("type") String type,
                                                    @Param("inciNo") String inciNo,
                                                    @Param("dmgInstNm") String dmgInstNm,
                                                    @Param("accdTypeCd") String accdTypeCd,
                                                    @Param("inciDttNm") String inciDttNm,
                                                    @Param("inciDttNmExclude") String inciDttNmExclude,
                                                    @Param("termDay") String termDay);

    List<ReportDailyDTO> getRetrieveIncidentCountDetail();

    List<ReportCollectionDTO> selectInciWarnCnt(@Param("weekDt") String weekDt,
                                                 @Param("endDt") String endDt,
                                                 @Param("sAuthMain") String sAuthMain,
                                                 @Param("sInstCd") String sInstCd);

    List<ReportCollectionDTO> selectLocalInciWarnCnt(@Param("startDt") String startDt,
                                                      @Param("endDt") String endDt,
                                                      @Param("sAuthMain") String sAuthMain,
                                                      @Param("sInstCd") String sInstCd);
}

package com.klid.webapp.main.rpt.reportCollection.persistence;

import com.klid.webapp.main.rpt.reportCollection.dto.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("reportCollectionMapper")
public interface ReportCollectionMapper {

    List<ReportHackingDto> getRetrieveSecurityHackingDetail(Map<String, Object> paramMap);

    List<ReportSecurityDataDto> getSecuListDetail(Map<String, Object> paramMap);

    List<ReportNoticeDto> getNoticeListDetail(Map<String, Object> paramMap);

    List<ReportSecurityVulnerabilityDto> getRetrieveSecurityVulnerabilityDetail(Map<String, Object> paramMap);

    List<ReportDailyDto> getRetrieveIncidentDetail(Map<String, Object> paramMap);

    List<ReportCollectionDto> selectInciWarnCnt(Map<String, Object> paramMap);

    List<ReportCollectionDto> selectLocalInciWarnCnt(Map<String, Object> paramMap);

}

package com.klid.webapp.main.rpt.reportCollection.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportCollectionService {

	ReturnData getRetrieveSecurityHackingDetail(Criterion criterion);

	ReturnData getSecuListDetail(Criterion criterion) ;

	ReturnData getNoticeListDetail(Criterion criterion);

	ReturnData getRetrieveSecurityVulnerabilityDetail(Criterion criterion);

	ReturnData getRetrieveIncidentDetail(Criterion criterion) ;

	ReturnData exportNoticeList(HttpServletResponse response, Criterion criterion);

	ReturnData exportSecuList(HttpServletResponse response, Criterion criterion) ;

	ReturnData exportRetrieveSecurityHacking(HttpServletResponse response, Criterion criterion) ;

	ReturnData exportRetrieveSecurityVulnerability(HttpServletResponse response, Criterion criterion) ;

	ReturnData exportRetrieveIncidentDetail(HttpServletResponse response, Criterion criterion);

	ReturnData exportReportCtrsDaily(HttpServletResponse response, Criterion criterion) ;

}

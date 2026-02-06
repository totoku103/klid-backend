package com.klid.webapp.main.rpt.reportSecurityResult.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface ReportSecurityResultService {

	ReturnData getResultTotal(Criterion criterion);

	ReturnData getResultList(Criterion criterion);

	ReturnData getResultExceptlist(Criterion criterion);

	ReturnData makeReportDownload(Criterion criterion);

}

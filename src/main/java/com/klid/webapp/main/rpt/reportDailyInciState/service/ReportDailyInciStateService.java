package com.klid.webapp.main.rpt.reportDailyInciState.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface ReportDailyInciStateService {

	ReturnData getDailyList(Criterion criterion);

	ReturnData getDailyTotList(Criterion criterion);

	ReturnData makeReportDailyInciStateDownload(Criterion criterion);

}

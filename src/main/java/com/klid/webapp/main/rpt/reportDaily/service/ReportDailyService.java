package com.klid.webapp.main.rpt.reportDaily.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface ReportDailyService {

	ReturnData getReportDayStat(Criterion criterion);

}

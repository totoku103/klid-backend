package com.klid.webapp.main.rpt.reportDailyState.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface ReportDailyStateService {

	ReturnData getRotationList(Criterion criterion);

	ReturnData getDailyList(Criterion criterion);

	ReturnData getDailyTotList(Criterion criterion);

	ReturnData getTypeAccidentList(Criterion criterion);

	ReturnData getDetectionList(Criterion criterion);

	ReturnData makeReportDailyStateDownload(Criterion criterion);

}

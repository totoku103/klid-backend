package com.klid.webapp.main.rpt.reportWeeklyState.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface ReportWeeklyStateService {

	ReturnData getRotationList(Criterion criterion);

	ReturnData getWeeklyTotList(Criterion criterion);

	ReturnData getWeeklyList(Criterion criterion);

	ReturnData getTypeAccidentList(Criterion criterion);
	ReturnData getTypeAccidentList_before(Criterion criterion);

	ReturnData getDetectionList(Criterion criterion);

	ReturnData makeReportWeeklyDownload(Criterion criterion);

}

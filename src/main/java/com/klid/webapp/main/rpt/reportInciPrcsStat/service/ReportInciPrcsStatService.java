package com.klid.webapp.main.rpt.reportInciPrcsStat.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface ReportInciPrcsStatService {

	ReturnData getPrcsStatList(Criterion criterion);

	ReturnData exportReportInciPrcsStat(Criterion criterion);

}

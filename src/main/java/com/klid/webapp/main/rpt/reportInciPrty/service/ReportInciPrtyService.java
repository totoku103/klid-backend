package com.klid.webapp.main.rpt.reportInciPrty.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface ReportInciPrtyService {

	ReturnData getPrtyList(Criterion criterion);

	ReturnData exportReportInciPrty(Criterion criterion);

}

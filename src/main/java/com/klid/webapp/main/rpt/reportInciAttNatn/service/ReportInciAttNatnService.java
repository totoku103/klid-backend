package com.klid.webapp.main.rpt.reportInciAttNatn.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface ReportInciAttNatnService {

	ReturnData getAttList(Criterion criterion);

	ReturnData exportReportAttNatn(Criterion criterion);

}

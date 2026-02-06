package com.klid.webapp.main.rpt.reportInciType.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface ReportInciTypeService {

	ReturnData getTypeList(Criterion criterion);

	ReturnData exportReportInciType(Criterion criterion);

}

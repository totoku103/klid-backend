package com.klid.webapp.main.rpt.reportInciLocal.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface ReportInciLocalService {

	ReturnData getLocalList(Criterion criterion);

	ReturnData getInciSidoList(Criterion criterion);

	ReturnData exportReportInciLocal(Criterion criterion);

}

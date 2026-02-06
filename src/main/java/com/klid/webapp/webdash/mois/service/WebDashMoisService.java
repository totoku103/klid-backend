package com.klid.webapp.webdash.mois.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface WebDashMoisService {

	ReturnData getThreatNow(Criterion criterion);

	ReturnData getHmHcUrlCenter(Criterion criterion);

	ReturnData getHmHcUrlRegion(Criterion criterion);

	ReturnData getForgeryRegion(Criterion criterion) ;

    ReturnData getRegionStatus(Criterion criterion) ;

    ReturnData getRegionStatusAuto(Criterion criterion);

    ReturnData getRegionStatusManual(Criterion criterion) ;

	ReturnData getDashConfigList(Criterion criterion) ;

	ReturnData getDashChartSum(Criterion criterion);
}

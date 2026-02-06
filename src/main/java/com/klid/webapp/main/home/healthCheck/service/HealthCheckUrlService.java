package com.klid.webapp.main.home.healthCheck.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface HealthCheckUrlService {
	public ReturnData getHealthCheckUrl(Criterion criterion);

	ReturnData addHealthCheckUrl(Criterion criterion) throws Exception;

	ReturnData editHealthCheckUrl(Criterion criterion);

	ReturnData editWatchOn(Criterion criterion);

	ReturnData editWatchOff(Criterion criterion);
	
	ReturnData getDetailHealthCheckUrl(Criterion criterion);

	ReturnData delHealthCheckUrl(Criterion criterion);

	public ReturnData getHealthCheckHist(Criterion criterion);

	public ReturnData getHealthCheckStat(Criterion criterion);

    ReturnData export(Criterion criterion);

    ReturnData importXls(Criterion criterion);
}

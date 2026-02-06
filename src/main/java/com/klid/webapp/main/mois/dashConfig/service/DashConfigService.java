package com.klid.webapp.main.mois.dashConfig.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface DashConfigService {

	ReturnData getDashConfigList(Criterion criterion);

	ReturnData addDashConfig(Criterion criterion);

	ReturnData editDashConfig(Criterion criterion) ;
}

package com.klid.webapp.webdash.center.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface WebDashCenterService {

     ReturnData getAttNationTop5(Criterion criterion);
     ReturnData getTypeChart(Criterion criterion);
     ReturnData getEvtChart(Criterion criterion);
     ReturnData getEvtAllChart(Criterion criterion);
}

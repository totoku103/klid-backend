package com.klid.webapp.main.sys.riskMgmt.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import jakarta.servlet.http.HttpServletRequest;
public interface RiskMgmtService {

	ReturnData getRiskMgmt(Criterion criterion);

	ReturnData editRiskMgmt(Criterion criterion);

	ReturnData getRiskHistory(Criterion criterion);

	ReturnData addRiskHistory(Criterion criterion, HttpServletRequest request);

	ReturnData delRiskHistory(Criterion criterion) ;

	ReturnData getThreatLevel(Criterion criterion);

	ReturnData getThreatNow(Criterion criterion);

	ReturnData getThreatHist(Criterion criterion);

	ReturnData editThreat(Criterion criterion) ;

	ReturnData getPeriodNow(Criterion criterion);

	ReturnData editPeriod(Criterion criterion) ;
}

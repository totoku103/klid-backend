package com.klid.webapp.main.sys.riskMgmt.persistence;

import com.klid.webapp.main.sys.riskMgmt.dto.RiskMgmtDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RiskMgmtMapper {
	RiskMgmtDto selectRiskMgmt(Map<String, Object> paramMap);

	void updateRiskMgmt(Map<String, Object> paramMap);

	List<RiskMgmtDto> selectRiskHistory(Map<String, Object> paramMap);

	int addRiskHistory(Map<String, Object> paramMap);

	void delRiskHistory(Map<String, Object> paramMap);

	List<RiskMgmtDto> selectThreatLevel(Map<String, Object> paramMap);

	List<RiskMgmtDto> selectThreatNow(Map<String, Object> paramMap);

	List<RiskMgmtDto> selectThreatHist(Map<String, Object> paramMap);

	int mergeThreat(Map<String, Object> paramMap);

	int addThreat(Map<String, Object> paramMap);

	List<RiskMgmtDto> selectPeriodNow(Map<String, Object> paramMap);

	int mergePeriod(Map<String, Object> paramMap);

}

package com.klid.webapp.main.rpt.reportDaily.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.MsgService;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.rpt.reportDaily.persistence.ReportDailyMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("reportDailyService")
public class ReportDailyServiceImpl extends MsgService implements ReportDailyService {

	@Resource(name = "reportDailyMapper")
	private ReportDailyMapper mapper;

	/** 일일 실적 사고처리 현황 조회 */
	@Override
	public ReturnData getReportDayStat(Criterion criterion) {
		return new ReturnData(mapper.getReportDayStat(criterion.getCondition()));
	}

}

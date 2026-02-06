package com.klid.webapp.main.rpt.reportInciDetail.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.MsgService;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.rpt.reportInciDetail.persistence.ReportInciDetailMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("reportInciDetailService")
public class ReportInciDetailServiceImpl extends MsgService implements ReportInciDetailService {

	@Resource(name = "reportInciDetailMapper")
	private ReportInciDetailMapper mapper;

	/** 상세조회 */
	@Override
	public ReturnData getDetailList(Criterion criterion) {
		return new ReturnData(mapper.selectInciDetail(criterion.getCondition()));
	}

}

package com.klid.webapp.main.rpt.reportInciDetail.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.MsgService;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.rpt.reportInciDetail.persistence.ReportInciDetailMapper;
import org.springframework.stereotype.Service;
import org.springframework.context.MessageSource;

@Service("reportInciDetailService")
public class ReportInciDetailServiceImpl extends MsgService implements ReportInciDetailService {


	public ReportInciDetailServiceImpl(MessageSource messageSource, ReportInciDetailMapper mapper) {
				super(messageSource);
		this.mapper = mapper;
	}
	private final ReportInciDetailMapper mapper;

	/** 상세조회 */
	@Override
	public ReturnData getDetailList(Criterion criterion) {
		return new ReturnData(mapper.selectInciDetail(criterion.getCondition()));
	}

}

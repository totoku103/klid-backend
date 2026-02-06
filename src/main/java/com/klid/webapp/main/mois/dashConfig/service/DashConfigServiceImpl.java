package com.klid.webapp.main.mois.dashConfig.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.MsgService;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.mois.dashConfig.persistence.DashConfigMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service("dashConfigService")
public class DashConfigServiceImpl extends MsgService implements DashConfigService {

	private final DashConfigMapper mapper;

	public DashConfigServiceImpl(MessageSource messageSource, DashConfigMapper mapper) {
		super(messageSource);
		this.mapper = mapper;
	}

	/** 행안부 리스트 받아오기 */
	@Override
	public ReturnData getDashConfigList(Criterion criterion) {
		return new ReturnData(mapper.getDashConfigList(criterion.getCondition()));
	}

	/** 행안부 대시보드 등록*/
	@Override
	public ReturnData addDashConfig(Criterion criterion) {
		mapper.addDashConfig(criterion.getCondition());
		return new ReturnData();
	}

	/** 행안부 대시보드 수정*/
	@Override
	public ReturnData editDashConfig(Criterion criterion) {
		mapper.editDashConfig(criterion.getCondition());
		return new ReturnData();
	}

}

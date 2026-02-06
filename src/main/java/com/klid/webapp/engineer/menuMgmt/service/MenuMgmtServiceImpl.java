package com.klid.webapp.engineer.menuMgmt.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.MsgService;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.engineer.menuMgmt.persistence.MenuMgmtMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("menuMgmtService")
public class MenuMgmtServiceImpl extends MsgService implements MenuMgmtService {

	@Resource(name="menuMgmtMapper")
	private MenuMgmtMapper mapper;

	@Override
	public ReturnData getPageList(Criterion criterion) {
		return new ReturnData(mapper.selectPageList(criterion.getCondition()));
	}

	@Override
	public ReturnData getPageGroupList(Criterion criterion) {
		return new ReturnData(mapper.selectPageGroupList(criterion.getCondition()));
	}

	@Override
	public ReturnData getMenuList(Criterion criterion) {
		return new ReturnData(mapper.selectMenuList(criterion.getCondition()));
	}

	
	

}

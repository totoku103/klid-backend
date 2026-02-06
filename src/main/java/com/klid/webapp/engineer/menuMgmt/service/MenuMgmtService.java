package com.klid.webapp.engineer.menuMgmt.service;
import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface MenuMgmtService {
	
	public ReturnData getPageList(Criterion criterion);
	public ReturnData getPageGroupList(Criterion criterion);
	public ReturnData getMenuList(Criterion criterion);
}

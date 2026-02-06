package com.klid.webapp.common.menu.helper;

import java.util.HashMap;
import java.util.Map;

public abstract class IMenuHelper {

	final Map<String, MenuVO> menuList = new HashMap<>();

	public abstract String getUrlByGuid(String guid);

	public Map<String, MenuVO> getMenuList() {
		return menuList;
	}
	
}

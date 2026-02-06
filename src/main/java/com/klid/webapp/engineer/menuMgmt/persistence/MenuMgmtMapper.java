package com.klid.webapp.engineer.menuMgmt.persistence;
import com.klid.webapp.engineer.menuMgmt.dto.MenuMgmtDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuMgmtMapper {

	public List<MenuMgmtDto> selectPageList(Map<String, Object> reqMap);
	public List<MenuMgmtDto> selectPageGroupList(Map<String, Object> reqMap);
	public List<MenuMgmtDto> selectMenuList(Map<String, Object> reqMap);
}

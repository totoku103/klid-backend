package com.klid.api.admin.menu.persistence;

import com.klid.api.admin.menu.dto.MenuMgmtDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 메뉴 관리 Mapper
 */
@Component("apiMenuMgmtMapper")
@Mapper
public interface MenuMgmtMapper {
    List<MenuMgmtDTO> selectPageList(Map<String, Object> params);

    List<MenuMgmtDTO> selectPageGroupList(Map<String, Object> params);

    List<MenuMgmtDTO> selectMenuList(Map<String, Object> params);
}

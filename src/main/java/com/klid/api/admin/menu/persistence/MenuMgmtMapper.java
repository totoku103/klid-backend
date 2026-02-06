package com.klid.api.admin.menu.persistence;

import com.klid.api.admin.menu.dto.MenuMgmtDTO;
import com.klid.api.admin.menu.dto.MenuMgmtSearchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 메뉴 관리 Mapper
 */
@Component("apiMenuMgmtMapper")
@Mapper
public interface MenuMgmtMapper {
    List<MenuMgmtDTO> selectPageList(MenuMgmtSearchDTO searchDTO);

    List<MenuMgmtDTO> selectPageGroupList(MenuMgmtSearchDTO searchDTO);

    List<MenuMgmtDTO> selectMenuList(MenuMgmtSearchDTO searchDTO);
}

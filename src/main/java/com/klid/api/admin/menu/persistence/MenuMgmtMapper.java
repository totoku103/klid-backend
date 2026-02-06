package com.klid.api.admin.menu.persistence;

import com.klid.api.admin.menu.dto.MenuMgmtDTO;
import com.klid.api.admin.menu.dto.MenuMgmtSearchDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMgmtMapper {
    List<MenuMgmtDTO> selectPageList(MenuMgmtSearchDTO searchDTO);

    List<MenuMgmtDTO> selectPageGroupList(MenuMgmtSearchDTO searchDTO);

    List<MenuMgmtDTO> selectMenuList(MenuMgmtSearchDTO searchDTO);
}

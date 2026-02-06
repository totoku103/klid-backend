package com.klid.api.admin.menu.service;

import com.klid.api.admin.menu.dto.MenuMgmtDTO;
import com.klid.api.admin.menu.dto.MenuMgmtSearchDTO;
import com.klid.api.admin.menu.persistence.MenuMgmtMapper;
import com.klid.common.AppGlobal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuMgmtService {

    private final MenuMgmtMapper menuMgmtMapper;

    public List<MenuMgmtDTO> getPageList(MenuMgmtSearchDTO searchDTO) {
        searchDTO.setSiteName(AppGlobal.webSiteName);
        return menuMgmtMapper.selectPageList(searchDTO);
    }

    public List<MenuMgmtDTO> getPageGroupList(MenuMgmtSearchDTO searchDTO) {
        searchDTO.setSiteName(AppGlobal.webSiteName);
        return menuMgmtMapper.selectPageGroupList(searchDTO);
    }

    public List<MenuMgmtDTO> getMenuList(MenuMgmtSearchDTO searchDTO) {
        searchDTO.setSiteName(AppGlobal.webSiteName);
        return menuMgmtMapper.selectMenuList(searchDTO);
    }
}

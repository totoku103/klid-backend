package com.klid.api.admin.menu.service;

import com.klid.api.admin.menu.dto.MenuMgmtDTO;
import com.klid.api.admin.menu.dto.MenuMgmtSearchDTO;
import com.klid.api.admin.menu.persistence.MenuMgmtMapper;
import com.klid.common.AppGlobal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 메뉴 관리 Service
 */
@Service("apiMenuMgmtService")
@RequiredArgsConstructor
public class MenuMgmtService {

    private final MenuMgmtMapper menuMgmtMapper;

    /**
     * 페이지 목록 조회
     */
    public List<MenuMgmtDTO> getPageList(MenuMgmtSearchDTO searchDTO) {
        searchDTO.setSiteName(AppGlobal.webSiteName);
        return menuMgmtMapper.selectPageList(searchDTO);
    }

    /**
     * 페이지 그룹 목록 조회
     */
    public List<MenuMgmtDTO> getPageGroupList(MenuMgmtSearchDTO searchDTO) {
        searchDTO.setSiteName(AppGlobal.webSiteName);
        return menuMgmtMapper.selectPageGroupList(searchDTO);
    }

    /**
     * 메뉴 목록 조회
     */
    public List<MenuMgmtDTO> getMenuList(MenuMgmtSearchDTO searchDTO) {
        searchDTO.setSiteName(AppGlobal.webSiteName);
        return menuMgmtMapper.selectMenuList(searchDTO);
    }
}

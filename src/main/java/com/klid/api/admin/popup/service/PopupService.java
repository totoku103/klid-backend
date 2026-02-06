package com.klid.api.admin.popup.service;

import com.klid.api.admin.popup.dto.*;
import com.klid.api.admin.popup.persistence.PopupMapper;
import com.klid.common.AppGlobal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 팝업 관리 Service
 */
@Service("apiPopupService")
@RequiredArgsConstructor
public class PopupService {

    private final PopupMapper popupMapper;

    /**
     * 페이지 추가
     */
    public int addPage(PageCreateRequest request) {
        request.setSiteName(AppGlobal.siteName);
        return popupMapper.addPage(request);
    }

    /**
     * 페이지 삭제
     */
    public void delPage(PageDeleteRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.delPage(request);
    }

    /**
     * 페이지 수정
     */
    public void savePage(PageSaveRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.savePage(request);
    }

    /**
     * 페이지 그룹 추가
     */
    public int addPageGroup(PageGroupCreateRequest request) {
        request.setSiteName(AppGlobal.siteName);
        return popupMapper.addPageGroup(request);
    }

    /**
     * 페이지 그룹 삭제
     */
    public void delPageGroup(PageGroupDeleteRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.delPageGroup(request);
    }

    /**
     * 페이지 그룹 수정
     */
    public void savePageGroup(PageGroupSaveRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.savePageGroup(request);
    }

    /**
     * 메뉴 추가
     */
    public void addMenu(MenuCreateRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.addMenu(request);
    }

    /**
     * 메뉴 삭제
     */
    public void delMenu(PageDeleteRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.delMenu(request);
    }

    /**
     * 메뉴 수정
     */
    public void saveMenu(MenuSaveRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.saveMenu(request);
    }
}

package com.klid.api.admin.popup.service;

import com.klid.api.admin.popup.persistence.PopupMapper;
import com.klid.common.AppGlobal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    public int addPage(Map<String, Object> params) {
        params.put("siteName", AppGlobal.siteName);
        return popupMapper.addPage(params);
    }

    /**
     * 페이지 삭제
     */
    public void delPage(Map<String, Object> params) {
        params.put("siteName", AppGlobal.siteName);
        popupMapper.delPage(params);
    }

    /**
     * 페이지 수정
     */
    public void savePage(Map<String, Object> params) {
        params.put("siteName", AppGlobal.siteName);
        popupMapper.savePage(params);
    }

    /**
     * 페이지 그룹 추가
     */
    public int addPageGroup(Map<String, Object> params) {
        params.put("siteName", AppGlobal.siteName);
        return popupMapper.addPageGroup(params);
    }

    /**
     * 페이지 그룹 삭제
     */
    public void delPageGroup(Map<String, Object> params) {
        params.put("siteName", AppGlobal.siteName);
        popupMapper.delPageGroup(params);
    }

    /**
     * 페이지 그룹 수정
     */
    public void savePageGroup(Map<String, Object> params) {
        params.put("siteName", AppGlobal.siteName);
        popupMapper.savePageGroup(params);
    }

    /**
     * 메뉴 추가
     */
    public void addMenu(Map<String, Object> params) {
        params.put("siteName", AppGlobal.siteName);
        popupMapper.addMenu(params);
    }

    /**
     * 메뉴 삭제
     */
    public void delMenu(Map<String, Object> params) {
        params.put("siteName", AppGlobal.siteName);
        popupMapper.delMenu(params);
    }

    /**
     * 메뉴 수정
     */
    public void saveMenu(Map<String, Object> params) {
        params.put("siteName", AppGlobal.siteName);
        popupMapper.saveMenu(params);
    }
}

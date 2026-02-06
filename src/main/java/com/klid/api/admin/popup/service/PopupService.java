package com.klid.api.admin.popup.service;

import com.klid.api.admin.popup.dto.*;
import com.klid.api.admin.popup.persistence.PopupMapper;
import com.klid.common.AppGlobal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("apiPopupService")
@RequiredArgsConstructor
public class PopupService {

    private final PopupMapper popupMapper;

    public int addPage(PageCreateRequest request) {
        request.setSiteName(AppGlobal.siteName);
        return popupMapper.addPage(request);
    }

    public void delPage(PageDeleteRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.delPage(request);
    }

    public void savePage(PageSaveRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.savePage(request);
    }

    public int addPageGroup(PageGroupCreateRequest request) {
        request.setSiteName(AppGlobal.siteName);
        return popupMapper.addPageGroup(request);
    }

    public void delPageGroup(PageGroupDeleteRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.delPageGroup(request);
    }

    public void savePageGroup(PageGroupSaveRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.savePageGroup(request);
    }

    public void addMenu(MenuCreateRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.addMenu(request);
    }

    public void delMenu(PageDeleteRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.delMenu(request);
    }

    public void saveMenu(MenuSaveRequest request) {
        request.setSiteName(AppGlobal.siteName);
        popupMapper.saveMenu(request);
    }
}

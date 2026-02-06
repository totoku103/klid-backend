package com.klid.api.admin.popup.persistence;

import com.klid.api.admin.popup.dto.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PopupMapper {

    int addPage(PageCreateRequest request);

    void savePage(PageSaveRequest request);

    void delPage(PageDeleteRequest request);

    int addPageGroup(PageGroupCreateRequest request);

    void savePageGroup(PageGroupSaveRequest request);

    void delPageGroup(PageGroupDeleteRequest request);

    void addMenu(MenuCreateRequest request);

    void saveMenu(MenuSaveRequest request);

    void delMenu(PageDeleteRequest request);
}

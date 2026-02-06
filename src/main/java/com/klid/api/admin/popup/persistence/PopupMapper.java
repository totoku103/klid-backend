package com.klid.api.admin.popup.persistence;

import com.klid.api.admin.popup.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 팝업 관리 Mapper
 */
@Component("apiPopupMapper")
@Mapper
public interface PopupMapper {

    /** 페이지 추가 */
    int addPage(PageCreateRequest request);

    /** 페이지 수정 */
    void savePage(PageSaveRequest request);

    /** 페이지 삭제 */
    void delPage(PageDeleteRequest request);

    /** 페이지 그룹 추가 */
    int addPageGroup(PageGroupCreateRequest request);

    /** 페이지 그룹 수정 */
    void savePageGroup(PageGroupSaveRequest request);

    /** 페이지 그룹 삭제 */
    void delPageGroup(PageGroupDeleteRequest request);

    /** 메뉴 추가 */
    void addMenu(MenuCreateRequest request);

    /** 메뉴 수정 */
    void saveMenu(MenuSaveRequest request);

    /** 메뉴 삭제 */
    void delMenu(PageDeleteRequest request);
}

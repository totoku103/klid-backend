package com.klid.api.admin.popup.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 팝업 관리 Mapper
 */
@Component("apiPopupMapper")
@Mapper
public interface PopupMapper {

    /** 페이지 추가 */
    int addPage(Map<String, Object> params);

    /** 페이지 수정 */
    void savePage(Map<String, Object> params);

    /** 페이지 삭제 */
    void delPage(Map<String, Object> params);

    /** 페이지 그룹 추가 */
    int addPageGroup(Map<String, Object> params);

    /** 페이지 그룹 수정 */
    void savePageGroup(Map<String, Object> params);

    /** 페이지 그룹 삭제 */
    void delPageGroup(Map<String, Object> params);

    /** 메뉴 추가 */
    void addMenu(Map<String, Object> params);

    /** 메뉴 수정 */
    void saveMenu(Map<String, Object> params);

    /** 메뉴 삭제 */
    void delMenu(Map<String, Object> params);
}

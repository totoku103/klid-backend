package com.klid.api.admin.popup.persistence;

import com.klid.api.BaseMapperTest;
import com.klid.common.AppGlobal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * 팝업 관리 Mapper 통합 테스트
 */
@DisplayName("팝업 관리 Mapper 테스트")
class PopupMapperTest extends BaseMapperTest {

    @Autowired
    private PopupMapper popupMapper;

    // ========== 페이지 관련 테스트 ==========

    @Test
    @DisplayName("페이지 추가 - 기본 정보")
    void testAddPage_BasicInfo() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuName", "테스트페이지");
        params.put("menuKind", "USER");
        params.put("isWebuse", "Y");
        params.put("visibleOrder", 1);

        // when
        final int result = popupMapper.addPage(params);

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("페이지 추가 - 최소 정보")
    void testAddPage_MinimalInfo() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuName", "최소페이지");

        // when
        final int result = popupMapper.addPage(params);

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("페이지 추가 - 전체 정보")
    void testAddPage_FullInfo() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuName", "전체정보페이지");
        params.put("menuKind", "ADMIN");
        params.put("isWebuse", "Y");
        params.put("visibleOrder", 10);
        params.put("webIconClass", "fa-icon");
        params.put("pageName", "FullPage");

        // when
        final int result = popupMapper.addPage(params);

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("페이지 수정 - 기본 수정")
    void testSavePage_Basic() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuNo", 1L);
        params.put("menuName", "수정된페이지");

        // when & then
        assertDoesNotThrow(() -> popupMapper.savePage(params));
    }

    @Test
    @DisplayName("페이지 수정 - 여러 필드 수정")
    void testSavePage_MultipleFields() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuNo", 1L);
        params.put("menuName", "복합수정페이지");
        params.put("isWebuse", "N");
        params.put("visibleOrder", 99);
        params.put("webIconClass", "updated-icon");

        // when & then
        assertDoesNotThrow(() -> popupMapper.savePage(params));
    }

    @Test
    @DisplayName("페이지 삭제 - menuNo로 삭제")
    void testDelPage_ByMenuNo() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuNo", 1L);

        // when & then
        assertDoesNotThrow(() -> popupMapper.delPage(params));
    }

    @Test
    @DisplayName("페이지 삭제 - 여러 조건으로 삭제")
    void testDelPage_MultipleConditions() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuNo", 1L);
        params.put("menuKind", "USER");

        // when & then
        assertDoesNotThrow(() -> popupMapper.delPage(params));
    }

    // ========== 페이지 그룹 관련 테스트 ==========

    @Test
    @DisplayName("페이지 그룹 추가 - 기본 정보")
    void testAddPageGroup_BasicInfo() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("pageGroupName", "테스트그룹");
        params.put("menuPageNo", 1L);

        // when
        final int result = popupMapper.addPageGroup(params);

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("페이지 그룹 추가 - 권한 포함")
    void testAddPageGroup_WithAuth() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("pageGroupName", "권한그룹");
        params.put("menuPageNo", 1L);
        params.put("menuAuth", "ADMIN");

        // when
        final int result = popupMapper.addPageGroup(params);

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("페이지 그룹 추가 - 최소 정보")
    void testAddPageGroup_MinimalInfo() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("pageGroupName", "최소그룹");

        // when
        final int result = popupMapper.addPageGroup(params);

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("페이지 그룹 수정 - 기본 수정")
    void testSavePageGroup_Basic() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuPageGrpNo", 1L);
        params.put("pageGroupName", "수정된그룹");

        // when & then
        assertDoesNotThrow(() -> popupMapper.savePageGroup(params));
    }

    @Test
    @DisplayName("페이지 그룹 수정 - 권한 변경")
    void testSavePageGroup_UpdateAuth() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuPageGrpNo", 1L);
        params.put("menuAuth", "USER");

        // when & then
        assertDoesNotThrow(() -> popupMapper.savePageGroup(params));
    }

    @Test
    @DisplayName("페이지 그룹 수정 - 전체 필드 수정")
    void testSavePageGroup_FullUpdate() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuPageGrpNo", 1L);
        params.put("pageGroupName", "완전수정그룹");
        params.put("menuPageNo", 2L);
        params.put("menuAuth", "ADMIN");

        // when & then
        assertDoesNotThrow(() -> popupMapper.savePageGroup(params));
    }

    @Test
    @DisplayName("페이지 그룹 삭제 - 기본 삭제")
    void testDelPageGroup_Basic() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuPageGrpNo", 1L);

        // when & then
        assertDoesNotThrow(() -> popupMapper.delPageGroup(params));
    }

    @Test
    @DisplayName("페이지 그룹 삭제 - 여러 조건")
    void testDelPageGroup_MultipleConditions() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuPageGrpNo", 1L);
        params.put("menuPageNo", 1L);

        // when & then
        assertDoesNotThrow(() -> popupMapper.delPageGroup(params));
    }

    // ========== 메뉴 관련 테스트 ==========

    @Test
    @DisplayName("메뉴 추가 - 기본 정보")
    void testAddMenu_BasicInfo() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuNm", "테스트메뉴");
        params.put("guid", "test-guid-001");

        // when & then
        assertDoesNotThrow(() -> popupMapper.addMenu(params));
    }

    @Test
    @DisplayName("메뉴 추가 - 전체 정보")
    void testAddMenu_FullInfo() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuNm", "전체메뉴");
        params.put("menuPageGrpNo", 1L);
        params.put("guid", "test-guid-002");
        params.put("addYn", 1);

        // when & then
        assertDoesNotThrow(() -> popupMapper.addMenu(params));
    }

    @Test
    @DisplayName("메뉴 추가 - 페이지 그룹 포함")
    void testAddMenu_WithPageGroup() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuNm", "그룹메뉴");
        params.put("menuPageGrpNo", 5L);
        params.put("guid", "test-guid-003");

        // when & then
        assertDoesNotThrow(() -> popupMapper.addMenu(params));
    }

    @Test
    @DisplayName("메뉴 수정 - 기본 수정")
    void testSaveMenu_Basic() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuNo", 1L);
        params.put("menuNm", "수정된메뉴");

        // when & then
        assertDoesNotThrow(() -> popupMapper.saveMenu(params));
    }

    @Test
    @DisplayName("메뉴 수정 - GUID로 수정")
    void testSaveMenu_ByGuid() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("guid", "test-guid-001");
        params.put("menuNm", "GUID수정메뉴");

        // when & then
        assertDoesNotThrow(() -> popupMapper.saveMenu(params));
    }

    @Test
    @DisplayName("메뉴 수정 - 전체 필드 수정")
    void testSaveMenu_FullUpdate() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuNo", 1L);
        params.put("menuNm", "완전수정메뉴");
        params.put("menuPageGrpNo", 3L);
        params.put("guid", "updated-guid");
        params.put("addYn", 0);

        // when & then
        assertDoesNotThrow(() -> popupMapper.saveMenu(params));
    }

    @Test
    @DisplayName("메뉴 삭제 - menuNo로 삭제")
    void testDelMenu_ByMenuNo() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuNo", 1L);

        // when & then
        assertDoesNotThrow(() -> popupMapper.delMenu(params));
    }

    @Test
    @DisplayName("메뉴 삭제 - GUID로 삭제")
    void testDelMenu_ByGuid() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("guid", "test-guid-001");

        // when & then
        assertDoesNotThrow(() -> popupMapper.delMenu(params));
    }

    @Test
    @DisplayName("메뉴 삭제 - 여러 조건으로 삭제")
    void testDelMenu_MultipleConditions() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuNo", 1L);
        params.put("menuPageGrpNo", 1L);
        params.put("guid", "test-guid-001");

        // when & then
        assertDoesNotThrow(() -> popupMapper.delMenu(params));
    }

    // ========== 통합 시나리오 테스트 ==========

    @Test
    @DisplayName("통합 시나리오 - 페이지 CRUD")
    void testPageCRUD() {
        // given - 추가
        final Map<String, Object> addParams = new HashMap<>();
        addParams.put("siteName", AppGlobal.siteName);
        addParams.put("menuName", "CRUD테스트페이지");
        addParams.put("menuKind", "USER");

        // when - 추가
        final int pageId = popupMapper.addPage(addParams);

        // then
        assertThat(pageId).isGreaterThanOrEqualTo(0);

        // given - 수정
        final Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("siteName", AppGlobal.siteName);
        updateParams.put("menuNo", pageId);
        updateParams.put("menuName", "수정된CRUD페이지");

        // when - 수정
        assertDoesNotThrow(() -> popupMapper.savePage(updateParams));

        // given - 삭제
        final Map<String, Object> deleteParams = new HashMap<>();
        deleteParams.put("siteName", AppGlobal.siteName);
        deleteParams.put("menuNo", pageId);

        // when - 삭제
        assertDoesNotThrow(() -> popupMapper.delPage(deleteParams));
    }

    @Test
    @DisplayName("통합 시나리오 - 페이지 그룹 CRUD")
    void testPageGroupCRUD() {
        // given - 추가
        final Map<String, Object> addParams = new HashMap<>();
        addParams.put("siteName", AppGlobal.siteName);
        addParams.put("pageGroupName", "CRUD테스트그룹");
        addParams.put("menuAuth", "ADMIN");

        // when - 추가
        final int groupId = popupMapper.addPageGroup(addParams);

        // then
        assertThat(groupId).isGreaterThanOrEqualTo(0);

        // given - 수정
        final Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("siteName", AppGlobal.siteName);
        updateParams.put("menuPageGrpNo", groupId);
        updateParams.put("pageGroupName", "수정된CRUD그룹");

        // when - 수정
        assertDoesNotThrow(() -> popupMapper.savePageGroup(updateParams));

        // given - 삭제
        final Map<String, Object> deleteParams = new HashMap<>();
        deleteParams.put("siteName", AppGlobal.siteName);
        deleteParams.put("menuPageGrpNo", groupId);

        // when - 삭제
        assertDoesNotThrow(() -> popupMapper.delPageGroup(deleteParams));
    }

    @Test
    @DisplayName("통합 시나리오 - 메뉴 CRUD")
    void testMenuCRUD() {
        // given - 추가
        final Map<String, Object> addParams = new HashMap<>();
        addParams.put("siteName", AppGlobal.siteName);
        addParams.put("menuNm", "CRUD테스트메뉴");
        addParams.put("guid", "crud-test-guid");
        addParams.put("addYn", 1);

        // when - 추가
        assertDoesNotThrow(() -> popupMapper.addMenu(addParams));

        // given - 수정
        final Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("siteName", AppGlobal.siteName);
        updateParams.put("guid", "crud-test-guid");
        updateParams.put("menuNm", "수정된CRUD메뉴");

        // when - 수정
        assertDoesNotThrow(() -> popupMapper.saveMenu(updateParams));

        // given - 삭제
        final Map<String, Object> deleteParams = new HashMap<>();
        deleteParams.put("siteName", AppGlobal.siteName);
        deleteParams.put("guid", "crud-test-guid");

        // when - 삭제
        assertDoesNotThrow(() -> popupMapper.delMenu(deleteParams));
    }

    @Test
    @DisplayName("경계값 테스트 - 빈 파라미터로 페이지 추가")
    void testAddPage_EmptyParams() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);

        // when
        final int result = popupMapper.addPage(params);

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("경계값 테스트 - null 값 포함 파라미터")
    void testAddPage_WithNullValues() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.siteName);
        params.put("menuName", "null테스트");
        params.put("webIconClass", null);

        // when
        final int result = popupMapper.addPage(params);

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }
}

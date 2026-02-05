package com.klid.api.admin.popup.service;

import com.klid.api.BaseServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 팝업 관리 Service 통합 테스트
 */
@DisplayName("팝업 관리 Service 테스트")
class PopupServiceTest extends BaseServiceTest {

    @Autowired
    private PopupService popupService;

    // ========== 페이지 관련 테스트 ==========

    @Test
    @DisplayName("페이지 추가 - 성공")
    void testAddPage_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuName", "테스트페이지");
        params.put("menuKind", "USER");
        params.put("isWebuse", "Y");
        params.put("visibleOrder", 1);

        // when
        final int result = popupService.addPage(params);

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 추가 - siteName 자동 추가 확인")
    void testAddPage_SiteNameAutoAdded() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuName", "자동추가테스트");

        // when
        popupService.addPage(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 수정 - 성공")
    void testSavePage_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);
        params.put("menuName", "수정된페이지");
        params.put("isWebuse", "N");

        // when
        popupService.savePage(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 수정 - 빈 파라미터")
    void testSavePage_EmptyParams() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        popupService.savePage(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 삭제 - 성공")
    void testDelPage_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);

        // when
        popupService.delPage(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 삭제 - 여러 조건")
    void testDelPage_MultipleConditions() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);
        params.put("menuKind", "USER");

        // when
        popupService.delPage(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    // ========== 페이지 그룹 관련 테스트 ==========

    @Test
    @DisplayName("페이지 그룹 추가 - 성공")
    void testAddPageGroup_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("pageGroupName", "테스트그룹");
        params.put("menuPageNo", 1L);
        params.put("menuAuth", "ADMIN");

        // when
        final int result = popupService.addPageGroup(params);

        // then
        assertThat(result).isGreaterThanOrEqualTo(0);
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 그룹 추가 - siteName 자동 추가 확인")
    void testAddPageGroup_SiteNameAutoAdded() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("pageGroupName", "자동추가그룹");

        // when
        popupService.addPageGroup(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 그룹 수정 - 성공")
    void testSavePageGroup_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuPageGrpNo", 1L);
        params.put("pageGroupName", "수정된그룹");
        params.put("menuAuth", "USER");

        // when
        popupService.savePageGroup(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 그룹 수정 - 부분 수정")
    void testSavePageGroup_PartialUpdate() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuPageGrpNo", 1L);
        params.put("menuAuth", "ADMIN");

        // when
        popupService.savePageGroup(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 그룹 삭제 - 성공")
    void testDelPageGroup_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuPageGrpNo", 1L);

        // when
        popupService.delPageGroup(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 그룹 삭제 - 여러 조건")
    void testDelPageGroup_MultipleConditions() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuPageGrpNo", 1L);
        params.put("menuPageNo", 1L);

        // when
        popupService.delPageGroup(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    // ========== 메뉴 관련 테스트 ==========

    @Test
    @DisplayName("메뉴 추가 - 성공")
    void testAddMenu_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNm", "테스트메뉴");
        params.put("menuPageGrpNo", 1L);
        params.put("guid", "test-guid-123");
        params.put("addYn", 1);

        // when
        popupService.addMenu(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("메뉴 추가 - siteName 자동 추가 확인")
    void testAddMenu_SiteNameAutoAdded() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNm", "자동추가메뉴");
        params.put("guid", "auto-guid");

        // when
        popupService.addMenu(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("메뉴 수정 - 성공")
    void testSaveMenu_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);
        params.put("menuNm", "수정된메뉴");
        params.put("addYn", 0);

        // when
        popupService.saveMenu(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("메뉴 수정 - 단일 필드")
    void testSaveMenu_SingleField() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);
        params.put("menuNm", "새이름");

        // when
        popupService.saveMenu(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("메뉴 삭제 - 성공")
    void testDelMenu_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);

        // when
        popupService.delMenu(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("메뉴 삭제 - GUID로 삭제")
    void testDelMenu_ByGuid() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("guid", "test-guid-123");

        // when
        popupService.delMenu(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("메뉴 삭제 - 복합 조건")
    void testDelMenu_MultipleConditions() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);
        params.put("menuPageGrpNo", 1L);
        params.put("guid", "test-guid");

        // when
        popupService.delMenu(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    // ========== 통합 시나리오 테스트 ==========

    @Test
    @DisplayName("통합 시나리오 - 페이지 추가/수정/삭제")
    void testPageLifecycle() {
        // given - 페이지 추가
        final Map<String, Object> addParams = new HashMap<>();
        addParams.put("menuName", "라이프사이클테스트");
        addParams.put("menuKind", "USER");

        // when - 추가
        final int addResult = popupService.addPage(addParams);

        // then
        assertThat(addResult).isGreaterThanOrEqualTo(0);

        // given - 페이지 수정
        final Map<String, Object> saveParams = new HashMap<>();
        saveParams.put("menuNo", addResult);
        saveParams.put("menuName", "수정됨");

        // when - 수정
        popupService.savePage(saveParams);

        // then
        assertThat(saveParams).containsKey("siteName");

        // given - 페이지 삭제
        final Map<String, Object> delParams = new HashMap<>();
        delParams.put("menuNo", addResult);

        // when - 삭제
        popupService.delPage(delParams);

        // then
        assertThat(delParams).containsKey("siteName");
    }

    @Test
    @DisplayName("통합 시나리오 - 페이지 그룹 추가/수정/삭제")
    void testPageGroupLifecycle() {
        // given - 그룹 추가
        final Map<String, Object> addParams = new HashMap<>();
        addParams.put("pageGroupName", "그룹라이프사이클");

        // when - 추가
        final int addResult = popupService.addPageGroup(addParams);

        // then
        assertThat(addResult).isGreaterThanOrEqualTo(0);

        // given - 그룹 수정
        final Map<String, Object> saveParams = new HashMap<>();
        saveParams.put("menuPageGrpNo", addResult);
        saveParams.put("pageGroupName", "수정된그룹");

        // when - 수정
        popupService.savePageGroup(saveParams);

        // then
        assertThat(saveParams).containsKey("siteName");

        // given - 그룹 삭제
        final Map<String, Object> delParams = new HashMap<>();
        delParams.put("menuPageGrpNo", addResult);

        // when - 삭제
        popupService.delPageGroup(delParams);

        // then
        assertThat(delParams).containsKey("siteName");
    }

    @Test
    @DisplayName("통합 시나리오 - 메뉴 추가/수정/삭제")
    void testMenuLifecycle() {
        // given - 메뉴 추가
        final Map<String, Object> addParams = new HashMap<>();
        addParams.put("menuNm", "메뉴라이프사이클");
        addParams.put("guid", "lifecycle-guid");

        // when - 추가
        popupService.addMenu(addParams);

        // then
        assertThat(addParams).containsKey("siteName");

        // given - 메뉴 수정
        final Map<String, Object> saveParams = new HashMap<>();
        saveParams.put("guid", "lifecycle-guid");
        saveParams.put("menuNm", "수정된메뉴");

        // when - 수정
        popupService.saveMenu(saveParams);

        // then
        assertThat(saveParams).containsKey("siteName");

        // given - 메뉴 삭제
        final Map<String, Object> delParams = new HashMap<>();
        delParams.put("guid", "lifecycle-guid");

        // when - 삭제
        popupService.delMenu(delParams);

        // then
        assertThat(delParams).containsKey("siteName");
    }
}

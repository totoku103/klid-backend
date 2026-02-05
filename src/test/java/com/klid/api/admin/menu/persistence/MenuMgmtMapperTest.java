package com.klid.api.admin.menu.persistence;

import com.klid.api.BaseMapperTest;
import com.klid.api.admin.menu.dto.MenuMgmtDTO;
import com.klid.common.AppGlobal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 메뉴 관리 Mapper 통합 테스트
 */
@DisplayName("메뉴 관리 Mapper 테스트")
class MenuMgmtMapperTest extends BaseMapperTest {

    @Autowired
    private MenuMgmtMapper menuMgmtMapper;

    @Test
    @DisplayName("페이지 목록 조회 - 기본 조회")
    void testSelectPageList_Default() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.webSiteName);

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectPageList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("페이지 목록 조회 - 메뉴명으로 필터링")
    void testSelectPageList_FilterByMenuName() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.webSiteName);
        params.put("menuName", "테스트");

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectPageList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("페이지 목록 조회 - 웹 사용여부로 필터링")
    void testSelectPageList_FilterByIsWebuse() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.webSiteName);
        params.put("isWebuse", "Y");

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectPageList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("페이지 목록 조회 - 여러 조건으로 필터링")
    void testSelectPageList_MultipleFilters() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.webSiteName);
        params.put("menuName", "관리");
        params.put("isWebuse", "Y");
        params.put("menuKind", "ADMIN");

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectPageList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("페이지 그룹 목록 조회 - 기본 조회")
    void testSelectPageGroupList_Default() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.webSiteName);

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectPageGroupList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("페이지 그룹 목록 조회 - 메뉴 권한으로 필터링")
    void testSelectPageGroupList_FilterByMenuAuth() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.webSiteName);
        params.put("menuAuth", "ADMIN");

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectPageGroupList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("페이지 그룹 목록 조회 - 페이지 번호로 필터링")
    void testSelectPageGroupList_FilterByMenuPageNo() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.webSiteName);
        params.put("menuPageNo", "1");

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectPageGroupList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("페이지 그룹 목록 조회 - 여러 조건으로 필터링")
    void testSelectPageGroupList_MultipleFilters() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.webSiteName);
        params.put("menuAuth", "ADMIN");
        params.put("menuPageNo", "1");

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectPageGroupList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("메뉴 목록 조회 - 기본 조회")
    void testSelectMenuList_Default() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.webSiteName);

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectMenuList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("메뉴 목록 조회 - 메뉴 종류로 필터링")
    void testSelectMenuList_FilterByMenuKind() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.webSiteName);
        params.put("menuKind", "USER");

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectMenuList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("메뉴 목록 조회 - 표시 순서로 필터링")
    void testSelectMenuList_FilterByVisibleOrder() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.webSiteName);
        params.put("visibleOrder", "1");

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectMenuList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("메뉴 목록 조회 - 여러 조건으로 필터링")
    void testSelectMenuList_MultipleFilters() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", AppGlobal.webSiteName);
        params.put("menuKind", "USER");
        params.put("visibleOrder", "1");
        params.put("isWebuse", "Y");

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectMenuList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("메뉴 목록 조회 - 빈 결과 처리")
    void testSelectMenuList_EmptyResult() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("siteName", "NON_EXISTENT_SITE");

        // when
        final List<MenuMgmtDTO> result = menuMgmtMapper.selectMenuList(params);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}

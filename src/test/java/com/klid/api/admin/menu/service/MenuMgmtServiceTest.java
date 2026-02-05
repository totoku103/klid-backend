package com.klid.api.admin.menu.service;

import com.klid.api.BaseServiceTest;
import com.klid.api.admin.menu.dto.MenuMgmtDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 메뉴 관리 Service 통합 테스트
 */
@DisplayName("메뉴 관리 Service 테스트")
class MenuMgmtServiceTest extends BaseServiceTest {

    @Autowired
    private MenuMgmtService menuMgmtService;

    @Test
    @DisplayName("페이지 목록 조회 - 성공")
    void testGetPageList_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<MenuMgmtDTO> result = menuMgmtService.getPageList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("페이지 목록 조회 - 파라미터와 함께")
    void testGetPageList_WithParameters() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuName", "테스트메뉴");
        params.put("isWebuse", "Y");

        // when
        final List<MenuMgmtDTO> result = menuMgmtService.getPageList(params);

        // then
        assertThat(result).isNotNull();
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 목록 조회 - siteName이 자동으로 추가됨")
    void testGetPageList_SiteNameAutoAdded() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        menuMgmtService.getPageList(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 그룹 목록 조회 - 성공")
    void testGetPageGroupList_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<MenuMgmtDTO> result = menuMgmtService.getPageGroupList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("페이지 그룹 목록 조회 - 파라미터와 함께")
    void testGetPageGroupList_WithParameters() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuAuth", "ADMIN");
        params.put("menuPageNo", "1");

        // when
        final List<MenuMgmtDTO> result = menuMgmtService.getPageGroupList(params);

        // then
        assertThat(result).isNotNull();
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("페이지 그룹 목록 조회 - siteName이 자동으로 추가됨")
    void testGetPageGroupList_SiteNameAutoAdded() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        menuMgmtService.getPageGroupList(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("메뉴 목록 조회 - 성공")
    void testGetMenuList_Success() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<MenuMgmtDTO> result = menuMgmtService.getMenuList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("메뉴 목록 조회 - 파라미터와 함께")
    void testGetMenuList_WithParameters() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuKind", "USER");
        params.put("visibleOrder", "1");

        // when
        final List<MenuMgmtDTO> result = menuMgmtService.getMenuList(params);

        // then
        assertThat(result).isNotNull();
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("메뉴 목록 조회 - siteName이 자동으로 추가됨")
    void testGetMenuList_SiteNameAutoAdded() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        menuMgmtService.getMenuList(params);

        // then
        assertThat(params).containsKey("siteName");
    }

    @Test
    @DisplayName("메뉴 목록 조회 - 빈 파라미터로 조회")
    void testGetMenuList_EmptyParams() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<MenuMgmtDTO> result = menuMgmtService.getMenuList(params);

        // then
        assertThat(result).isNotNull();
    }
}

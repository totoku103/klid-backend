package com.klid.api.admin.menu.controller;

import com.klid.api.admin.menu.dto.MenuMgmtDTO;
import com.klid.api.admin.menu.service.MenuMgmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 메뉴 관리 Controller
 * - 페이지 목록 조회
 * - 페이지 그룹 목록 조회
 * - 메뉴 목록 조회
 */
@RestController("apiMenuMgmtController")
@RequestMapping("/api/admin/menu-management")
@RequiredArgsConstructor
public class MenuMgmtController {

    private final MenuMgmtService menuMgmtService;

    /**
     * 페이지 목록 조회
     */
    @GetMapping("/pages")
    public ResponseEntity<List<MenuMgmtDTO>> getPageList(@RequestParam Map<String, Object> params) {
        final List<MenuMgmtDTO> result = menuMgmtService.getPageList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 페이지 그룹 목록 조회
     */
    @GetMapping("/page-groups")
    public ResponseEntity<List<MenuMgmtDTO>> getPageGroupList(@RequestParam Map<String, Object> params) {
        final List<MenuMgmtDTO> result = menuMgmtService.getPageGroupList(params);
        return ResponseEntity.ok(result);
    }

    /**
     * 메뉴 목록 조회
     */
    @GetMapping("/menus")
    public ResponseEntity<List<MenuMgmtDTO>> getMenuList(@RequestParam Map<String, Object> params) {
        final List<MenuMgmtDTO> result = menuMgmtService.getMenuList(params);
        return ResponseEntity.ok(result);
    }
}

package com.klid.api.admin.menu.controller;

import com.klid.api.admin.menu.dto.MenuMgmtDTO;
import com.klid.api.admin.menu.dto.MenuMgmtSearchDTO;
import com.klid.api.admin.menu.service.MenuMgmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("apiMenuMgmtController")
@RequestMapping("/api/admin/menu-management")
@RequiredArgsConstructor
public class MenuMgmtController {

    private final MenuMgmtService menuMgmtService;

    @GetMapping("/pages")
    public ResponseEntity<List<MenuMgmtDTO>> getPageList(@ModelAttribute MenuMgmtSearchDTO searchDTO) {
        final List<MenuMgmtDTO> result = menuMgmtService.getPageList(searchDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/page-groups")
    public ResponseEntity<List<MenuMgmtDTO>> getPageGroupList(@ModelAttribute MenuMgmtSearchDTO searchDTO) {
        final List<MenuMgmtDTO> result = menuMgmtService.getPageGroupList(searchDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/menus")
    public ResponseEntity<List<MenuMgmtDTO>> getMenuList(@ModelAttribute MenuMgmtSearchDTO searchDTO) {
        final List<MenuMgmtDTO> result = menuMgmtService.getMenuList(searchDTO);
        return ResponseEntity.ok(result);
    }
}

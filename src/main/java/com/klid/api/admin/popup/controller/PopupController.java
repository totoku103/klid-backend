package com.klid.api.admin.popup.controller;

import com.klid.api.admin.popup.service.PopupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 팝업 관리 Controller
 * - 페이지 CRUD
 * - 페이지 그룹 CRUD
 * - 메뉴 CRUD
 */
@RestController("apiPopupController")
@RequestMapping("/api/admin/popup")
@RequiredArgsConstructor
public class PopupController {

    private final PopupService popupService;

    /**
     * 페이지 추가
     */
    @PostMapping("/pages")
    public ResponseEntity<Integer> addPage(@RequestBody Map<String, Object> params) {
        final int result = popupService.addPage(params);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * 페이지 삭제
     */
    @DeleteMapping("/pages")
    public ResponseEntity<Void> delPage(@RequestBody Map<String, Object> params) {
        popupService.delPage(params);
        return ResponseEntity.noContent().build();
    }

    /**
     * 페이지 수정
     */
    @PutMapping("/pages")
    public ResponseEntity<Void> savePage(@RequestBody Map<String, Object> params) {
        popupService.savePage(params);
        return ResponseEntity.ok().build();
    }

    /**
     * 페이지 그룹 추가
     */
    @PostMapping("/page-groups")
    public ResponseEntity<Integer> addPageGroup(@RequestBody Map<String, Object> params) {
        final int result = popupService.addPageGroup(params);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * 페이지 그룹 삭제
     */
    @DeleteMapping("/page-groups")
    public ResponseEntity<Void> delPageGroup(@RequestBody Map<String, Object> params) {
        popupService.delPageGroup(params);
        return ResponseEntity.noContent().build();
    }

    /**
     * 페이지 그룹 수정
     */
    @PutMapping("/page-groups")
    public ResponseEntity<Void> savePageGroup(@RequestBody Map<String, Object> params) {
        popupService.savePageGroup(params);
        return ResponseEntity.ok().build();
    }

    /**
     * 메뉴 추가
     */
    @PostMapping("/menus")
    public ResponseEntity<Void> addMenu(@RequestBody Map<String, Object> params) {
        popupService.addMenu(params);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 메뉴 삭제
     */
    @DeleteMapping("/menus")
    public ResponseEntity<Void> delMenu(@RequestBody Map<String, Object> params) {
        popupService.delMenu(params);
        return ResponseEntity.noContent().build();
    }

    /**
     * 메뉴 수정
     */
    @PutMapping("/menus")
    public ResponseEntity<Void> saveMenu(@RequestBody Map<String, Object> params) {
        popupService.saveMenu(params);
        return ResponseEntity.ok().build();
    }
}

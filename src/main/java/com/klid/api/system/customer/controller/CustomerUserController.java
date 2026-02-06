package com.klid.api.system.customer.controller;

import com.klid.api.system.customer.dto.CustomerUserResponse;
import com.klid.api.system.customer.dto.SmsGroupRequest;
import com.klid.api.system.customer.dto.SmsGroupResponse;
import com.klid.api.system.customer.service.CustomerUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 외부 사용자 및 SMS 그룹 관리 REST API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system/customer-users")
public class CustomerUserController {

    private final CustomerUserService customerUserService;

    /**
     * SMS 전송 팝업 - 내부 사용자 목록 조회
     */
    @PostMapping("/sms-users")
    public ResponseEntity<List<CustomerUserResponse>> getSmsUserList(@RequestParam final Map<String, Object> params) {
        final List<CustomerUserResponse> users = customerUserService.getSmsUserList(params);
        return ResponseEntity.ok(users);
    }

    /**
     * SMS 전송 팝업 - 외부 사용자 목록 조회
     */
    @PostMapping("/sms-external-users")
    public ResponseEntity<List<CustomerUserResponse>> getSmsExternalUserList(@RequestParam final Map<String, Object> params) {
        final List<CustomerUserResponse> users = customerUserService.getSmsOfUserList(params);
        return ResponseEntity.ok(users);
    }

    /**
     * 사용자 전화번호 조회
     */
    @PostMapping("/phone")
    public ResponseEntity<String> getUserPhone(@RequestParam final Map<String, Object> params) {
        final String phone = customerUserService.getUserPhone(params);
        return ResponseEntity.ok(phone);
    }

    /**
     * SMS 그룹 목록 조회
     */
    @GetMapping("/sms-groups")
    public ResponseEntity<List<SmsGroupResponse>> getSmsGroupList(@RequestParam final Map<String, Object> params) {
        final List<SmsGroupResponse> groups = customerUserService.getSmsGroup(params);
        return ResponseEntity.ok(groups);
    }

    /**
     * SMS 그룹 등록
     */
    @PostMapping("/sms-groups")
    public ResponseEntity<Void> addSmsGroup(@RequestBody final SmsGroupRequest request) {
        customerUserService.addSmsGroup(request);
        return ResponseEntity.ok().build();
    }

    /**
     * SMS 그룹 수정
     */
    @PutMapping("/sms-groups/{groupId}")
    public ResponseEntity<Void> editSmsGroup(
            @PathVariable final String groupId,
            @RequestBody final SmsGroupRequest request) {
        customerUserService.editSmsGroup(groupId, request);
        return ResponseEntity.ok().build();
    }

    /**
     * SMS 그룹 삭제
     */
    @DeleteMapping("/sms-groups/{groupId}")
    public ResponseEntity<Void> deleteSmsGroup(@PathVariable final String groupId) {
        customerUserService.delSmsGroup(groupId);
        return ResponseEntity.ok().build();
    }
}

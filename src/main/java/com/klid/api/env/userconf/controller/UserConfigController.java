package com.klid.api.env.userconf.controller;

import com.klid.api.env.userconf.dto.CodeDTO;
import com.klid.api.env.userconf.dto.UserDTO;
import com.klid.api.env.userconf.service.UserConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/env/users")
public class UserConfigController {

    private final UserConfigService userConfigService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getList(@RequestParam final Map<String, Object> params) {
        final List<UserDTO> list = userConfigService.getList(params);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<UserDTO>> getContactList(@RequestParam final Map<String, Object> params) {
        final List<UserDTO> list = userConfigService.getContactList(params);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getDetail(@PathVariable final String userId) {
        final Map<String, Object> detail = userConfigService.getDetail(userId);
        return ResponseEntity.ok(detail);
    }

    @GetMapping("/check-duplicate")
    public ResponseEntity<Integer> checkDuplicate(@RequestParam final String userId) {
        final int count = userConfigService.checkDuplicate(userId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/auth-list")
    public ResponseEntity<List<CodeDTO>> getAuthList(@RequestParam final Map<String, Object> params) {
        final List<CodeDTO> list = userConfigService.getAuthList(params);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/check-my-id")
    public ResponseEntity<Map<String, Object>> checkMyId(@RequestParam final String userId) {
        final Map<String, Object> result = userConfigService.checkMyId(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/check-auth")
    public ResponseEntity<Integer> checkUserAuth() {
        final int result = userConfigService.checkUserAuth();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/push-users")
    public ResponseEntity<List<UserDTO>> getPushUsers(@RequestParam final Map<String, Object> params) {
        final List<UserDTO> list = userConfigService.getPushUsers(params);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody final UserDTO dto) {
        userConfigService.addUser(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> editUser(@PathVariable final String userId, @RequestBody final UserDTO dto) {
        userConfigService.editUser(userId, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/self")
    public ResponseEntity<Void> editSelfUser(@RequestBody final UserDTO dto) {
        userConfigService.editSelfUser(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Void> editPassword(@PathVariable final String userId, @RequestBody final Map<String, String> body) {
        final String password = body.get("password");
        userConfigService.editPassword(userId, password);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/password-check")
    public ResponseEntity<String> passwordCheck(@PathVariable final String userId, @RequestBody final Map<String, String> body) {
        final String prePassword = body.get("prePassword");
        final String newPassword = body.get("password");
        userConfigService.passwordCheck(userId, prePassword, newPassword);
        return ResponseEntity.ok("변경되었습니다.");
    }

    @PostMapping("/{userId}/password-reset")
    public ResponseEntity<Void> passwordReset(@PathVariable final String userId) {
        userConfigService.passwordReset(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/lock-reset")
    public ResponseEntity<Void> lockReset(@PathVariable final String userId) {
        userConfigService.lockReset(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password-reset-all")
    public ResponseEntity<Integer> allPasswordReset() {
        final int count = userConfigService.allPasswordReset();
        return ResponseEntity.ok(count);
    }

    @PutMapping("/expire/password")
    public ResponseEntity<Void> expirePasswordChange(@RequestBody final Map<String, String> body) {
        final String password = body.get("password");
        userConfigService.expirePasswordChange(password);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable final String userId) {
        userConfigService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/info")
    public ResponseEntity<UserDTO> getUserInfo(@RequestBody final Map<String, String> body) {
        final String userId = body.get("userId");
        final UserDTO info = userConfigService.getUserInfo(userId);
        return ResponseEntity.ok(info);
    }
}

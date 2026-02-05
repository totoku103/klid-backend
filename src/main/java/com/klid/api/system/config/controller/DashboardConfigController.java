package com.klid.api.system.config.controller;

import com.klid.api.system.config.dto.DashboardConfigRequest;
import com.klid.api.system.config.dto.DashboardConfigResponse;
import com.klid.api.system.config.service.DashboardConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 행안부 대시보드 설정 관리 REST API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system/dashboard-config")
public class DashboardConfigController {

    private final DashboardConfigService dashboardConfigService;

    /**
     * 행안부 대시보드 설정 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<DashboardConfigResponse>> getDashConfigList(@RequestParam final Map<String, Object> params) {
        final List<DashboardConfigResponse> configs = dashboardConfigService.getDashConfigList(params);
        return ResponseEntity.ok(configs);
    }

    /**
     * 행안부 대시보드 설정 등록
     */
    @PostMapping
    public ResponseEntity<Void> addDashConfig(@RequestBody final DashboardConfigRequest request) {
        dashboardConfigService.addDashConfig(request);
        return ResponseEntity.ok().build();
    }

    /**
     * 행안부 대시보드 설정 수정
     */
    @PutMapping("/{configId}")
    public ResponseEntity<Void> editDashConfig(
            @PathVariable final String configId,
            @RequestBody final DashboardConfigRequest request) {
        dashboardConfigService.editDashConfig(configId, request);
        return ResponseEntity.ok().build();
    }
}

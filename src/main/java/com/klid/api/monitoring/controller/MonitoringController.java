package com.klid.api.monitoring.controller;

import com.klid.api.monitoring.dto.MonitoringDetailDTO;
import com.klid.api.monitoring.dto.MonitoringStatsDTO;
import com.klid.api.monitoring.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monitoring")
public class MonitoringController {

    private final MonitoringService monitoringService;

    @GetMapping("/stats")
    public ResponseEntity<MonitoringStatsDTO> getMonitoringStats(
            @RequestParam(required = false) final String sInstCd,
            @RequestParam(required = false) final String sAuthMain) {
        final MonitoringStatsDTO result = monitoringService.getMonitoringStats(sInstCd, sAuthMain);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/detail")
    public ResponseEntity<List<MonitoringDetailDTO>> getMonitoringDetail(
            @RequestParam(required = false) final String sInstCd,
            @RequestParam(required = false) final String time1,
            @RequestParam(required = false) final String time2) {
        final List<MonitoringDetailDTO> result = monitoringService.getMonitoringDetail(sInstCd, time1, time2);
        return ResponseEntity.ok(result);
    }
}

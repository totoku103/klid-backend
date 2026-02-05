package com.klid.api.dashboard.controller;

import com.klid.api.dashboard.dto.*;
import com.klid.api.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/threat-status")
    public ResponseEntity<ThreatStatusDTO> getThreatStatus(
            @RequestParam(required = false) final String instCd) {
        final ThreatStatusDTO result = dashboardService.getThreatStatus(instCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/period-setting")
    public ResponseEntity<PeriodSettingDTO> getPeriodSetting(
            @RequestParam(required = false) final String instCd) {
        final PeriodSettingDTO result = dashboardService.getPeriodSetting(instCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/period-status")
    public ResponseEntity<PeriodStatusDTO> getPeriodStatus(
            @RequestParam(required = false) final String sAuthMain,
            @RequestParam(required = false) final String sInstCd) {
        final PeriodStatusDTO result = dashboardService.getPeriodStatus(sAuthMain, sInstCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/today-status")
    public ResponseEntity<TodayStatusDTO> getTodayStatus(
            @RequestParam(required = false) final String sAuthMain,
            @RequestParam(required = false) final String sInstCd,
            @RequestParam(required = false) final String atype) {
        final TodayStatusDTO result = dashboardService.getTodayStatus(sAuthMain, sInstCd, atype);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/year-status")
    public ResponseEntity<YearStatusDTO> getYearStatus(
            @RequestParam(required = false) final String sAuthMain,
            @RequestParam(required = false) final String sInstCd,
            @RequestParam(required = false) final String atype) {
        final YearStatusDTO result = dashboardService.getYearStatus(sAuthMain, sInstCd, atype);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/accident-type-top5")
    public ResponseEntity<List<AccidentTypeRankDTO>> getAccidentTypeTop5(
            @RequestParam(required = false) final String sAuthMain,
            @RequestParam(required = false) final String instCd,
            @RequestParam(required = false) final String dateType,
            @RequestParam(required = false) final String startDt,
            @RequestParam(required = false) final String endDt) {
        final List<AccidentTypeRankDTO> result = dashboardService.getAccidentTypeTop5(
                sAuthMain, instCd, dateType, startDt, endDt);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/institution-top5")
    public ResponseEntity<List<InstitutionRankDTO>> getInstitutionTop5(
            @RequestParam(required = false) final String sAuthMain,
            @RequestParam(required = false) final String instCd,
            @RequestParam(required = false) final String dateType,
            @RequestParam(required = false) final String startDt,
            @RequestParam(required = false) final String endDt,
            @RequestParam(required = false) final String sortType,
            @RequestParam(required = false) final String topInstView) {
        final List<InstitutionRankDTO> result = dashboardService.getInstitutionTop5(
                sAuthMain, instCd, dateType, startDt, endDt, sortType, topInstView);
        return ResponseEntity.ok(result);
    }
}

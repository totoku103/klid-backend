package com.klid.api.webdash.mois.controller;

import com.klid.api.webdash.mois.service.WebDashMoisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("apiWebDashMoisController")
@RequiredArgsConstructor
@RequestMapping("/api/webdash/mois")
public class WebDashMoisController {

    private final WebDashMoisService service;

    @GetMapping("/threat-now")
    public ResponseEntity<Object> getThreatNow(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getThreatNow(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/hm-hc-url-center")
    public ResponseEntity<Object> getHmHcUrlCenter(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getHmHcUrlCenter(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/hm-hc-url-region")
    public ResponseEntity<Object> getHmHcUrlRegion(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getHmHcUrlRegion(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/forgery-region")
    public ResponseEntity<Object> getForgeryRegion(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getForgeryRegion(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/region-status")
    public ResponseEntity<Object> getRegionStatus(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getRegionStatus(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/region-status-auto")
    public ResponseEntity<Integer> getRegionStatusAuto(@RequestParam final Map<String, Object> reqMap) {
        final Integer result = service.getRegionStatusAuto(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/region-status-manual")
    public ResponseEntity<Object> getRegionStatusManual(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getRegionStatusManual(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/dash-config-list")
    public ResponseEntity<Object> getDashConfigList(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getDashConfigList(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/dash-chart-sum")
    public ResponseEntity<Object> getDashChartSum(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getDashChartSum(reqMap);
        return ResponseEntity.ok(result);
    }
}

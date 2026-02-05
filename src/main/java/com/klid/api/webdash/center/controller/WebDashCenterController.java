package com.klid.api.webdash.center.controller;

import com.klid.api.webdash.center.dto.WebDashCenterDTO;
import com.klid.api.webdash.center.service.WebDashCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController("apiWebDashCenterController")
@RequiredArgsConstructor
@RequestMapping("/api/webdash/center")
public class WebDashCenterController {

    private final WebDashCenterService service;

    @GetMapping("/att-nation-top5")
    public ResponseEntity<List<WebDashCenterDTO>> getAttNationTop5(@RequestParam final Map<String, Object> reqMap) {
        addTimeTypeParam(reqMap);
        final List<WebDashCenterDTO> result = service.getAttNationTop5(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/type-chart")
    public ResponseEntity<List<Map<String, Integer>>> getTypeChart(@RequestParam final Map<String, Object> reqMap) {
        addTimeTypeParam(reqMap);
        final List<Map<String, Integer>> result = service.getTypeChart(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/evt-chart")
    public ResponseEntity<Map<String, List<Map<String, Integer>>>> getEvtChart(@RequestParam final Map<String, Object> reqMap) {
        final Map<String, List<Map<String, Integer>>> result = service.getEvtChart(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/evt-all-chart")
    public ResponseEntity<Map<String, List<Map<String, Integer>>>> getEvtAllChart(@RequestParam final Map<String, Object> reqMap) {
        final Map<String, List<Map<String, Integer>>> result = service.getEvtAllChart(reqMap);
        return ResponseEntity.ok(result);
    }

    /**
     * 시간대별 타입 파라미터 추가 (0~6시: 0, 그 외: 1)
     */
    private void addTimeTypeParam(final Map<String, Object> reqMap) {
        final Calendar cal = Calendar.getInstance();
        final int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 0 && hour < 6) {
            reqMap.put("atype", 0);
        } else {
            reqMap.put("atype", 1);
        }
    }
}

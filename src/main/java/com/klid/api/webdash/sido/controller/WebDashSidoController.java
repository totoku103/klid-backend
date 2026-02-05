package com.klid.api.webdash.sido.controller;

import com.klid.api.webdash.sido.service.WebDashSidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("apiWebDashSidoController")
@RequiredArgsConstructor
@RequestMapping("/api/webdash/sido")
public class WebDashSidoController {

    private final WebDashSidoService service;

    @GetMapping("/notice-list")
    public ResponseEntity<Object> getNoticeList(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getNoticeList(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/secu-list")
    public ResponseEntity<Object> getSecuList(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getSecuList(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/region-status-manual")
    public ResponseEntity<Object> getRegionStatusManual(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getRegionStatusManual(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/forgery-check")
    public ResponseEntity<Object> getForgeryCheck(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getForgeryCheck(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/hc-check")
    public ResponseEntity<Object> getHcCheck(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getHcCheck(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/process")
    public ResponseEntity<Object> getProcess(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getProcess(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sido-list")
    public ResponseEntity<Object> getSidoList(@RequestParam final Map<String, Object> reqMap) {
        final Object result = service.getSidoList(reqMap);
        return ResponseEntity.ok(result);
    }
}

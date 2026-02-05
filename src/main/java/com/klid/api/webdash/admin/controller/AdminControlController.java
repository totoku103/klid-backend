package com.klid.api.webdash.admin.controller;

import com.klid.api.webdash.admin.dto.*;
import com.klid.api.webdash.admin.service.AdminControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController("apiAdminControlController")
@RequiredArgsConstructor
@RequestMapping("/api/webdash/admin")
public class AdminControlController {

    private final AdminControlService service;

    @GetMapping("/incident-status")
    public ResponseEntity<IncidentDTO> getIncidentStatus(@RequestParam final Map<String, Object> reqMap) {
        addTimeTypeParam(reqMap);
        final IncidentDTO result = service.getIncidentStatus(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/inci-cnt")
    public ResponseEntity<List<InciCntDTO>> getInciCnt(@RequestParam final Map<String, Object> reqMap) {
        addTimeTypeParam(reqMap);
        final List<InciCntDTO> result = service.getInciCnt(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/tbzledge-cnt")
    public ResponseEntity<List<TbzledgeCntDTO>> getTbzledgeCnt(@RequestParam final Map<String, Object> reqMap) {
        addTimeTypeParam(reqMap);
        final List<TbzledgeCntDTO> result = service.getTbzledgeCnt(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/local-inci-cnt")
    public ResponseEntity<List<InciCntDTO>> getLocalInciCnt(@RequestParam final Map<String, Object> reqMap) {
        addTimeTypeParam(reqMap);
        final List<InciCntDTO> result = service.getLocalInciCnt(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/local-status")
    public ResponseEntity<List<LocalStatusDTO>> getLocalStatus(@RequestParam final Map<String, Object> reqMap) {
        final List<LocalStatusDTO> result = service.getLocalStatus(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/url-status")
    public ResponseEntity<UrlStatusDTO> getUrlStatus(@RequestParam final Map<String, Object> reqMap) {
        final UrlStatusDTO result = service.getUrlStatus(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sys-error-status")
    public ResponseEntity<List<SysErrorDTO>> getSysErrorStatus(@RequestParam final Map<String, Object> reqMap) {
        final List<SysErrorDTO> result = service.getSysErrorStatus(reqMap);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/inci-type-cnt")
    public ResponseEntity<List<String>> getInciTypeCnt(@RequestParam final Map<String, Object> reqMap) {
        addTimeTypeParam(reqMap);
        final List<String> result = service.getInciTypeCnt(reqMap);
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

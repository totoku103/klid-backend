package com.klid.api.env.instip.controller;

import com.klid.api.env.instip.dto.InstIPMgmtDTO;
import com.klid.api.env.instip.service.InstitutionIPService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/env/institution-ips")
public class InstitutionIPController {

    private final InstitutionIPService institutionIPService;

    @GetMapping
    public ResponseEntity<List<InstIPMgmtDTO>> getList(@RequestParam final Map<String, Object> params) {
        final List<InstIPMgmtDTO> list = institutionIPService.getList(params);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/by-institution")
    public ResponseEntity<List<InstIPMgmtDTO>> getByInstitution(@RequestParam final String instCd) {
        final List<InstIPMgmtDTO> list = institutionIPService.getByInstitution(instCd);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody final InstIPMgmtDTO dto) {
        institutionIPService.add(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{seq}")
    public ResponseEntity<Void> update(@PathVariable final Long seq, @RequestBody final InstIPMgmtDTO dto) {
        institutionIPService.update(seq, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody final List<InstIPMgmtDTO> items) {
        institutionIPService.delete(items);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/export")
    public ResponseEntity<Map<String, String>> export(@RequestBody final Map<String, Object> params) throws Exception {
        final Map<String, String> result = institutionIPService.export(params);
        return ResponseEntity.ok(result);
    }
}

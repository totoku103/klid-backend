package com.klid.api.env.inst.controller;

import com.klid.api.env.inst.dto.InstMgmtDTO;
import com.klid.api.env.inst.service.InstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/env/institutions")
public class InstitutionController {

    private final InstitutionService institutionService;

    @GetMapping
    public ResponseEntity<List<InstMgmtDTO>> getList(@RequestParam final Map<String, Object> params) {
        final List<InstMgmtDTO> list = institutionService.getList(params);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{instCd}")
    public ResponseEntity<InstMgmtDTO> getInfo(@PathVariable final String instCd) {
        final InstMgmtDTO info = institutionService.getInfo(instCd);
        return ResponseEntity.ok(info);
    }

    @GetMapping("/check-duplicate")
    public ResponseEntity<String> checkDuplicate(@RequestParam final String instCd) {
        final String result = institutionService.checkDuplicate(instCd);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody final InstMgmtDTO dto) {
        institutionService.add(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{instCd}")
    public ResponseEntity<Void> update(@PathVariable final String instCd, @RequestBody final InstMgmtDTO dto) {
        institutionService.update(instCd, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody final List<String> instCds) {
        institutionService.delete(instCds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/export")
    public ResponseEntity<Map<String, String>> export(@RequestBody final Map<String, Object> params) throws Exception {
        final Map<String, String> result = institutionService.export(params);
        return ResponseEntity.ok(result);
    }
}

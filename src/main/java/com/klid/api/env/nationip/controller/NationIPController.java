package com.klid.api.env.nationip.controller;

import com.klid.api.env.nationip.dto.NationIPMgmtDTO;
import com.klid.api.env.nationip.service.NationIPService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/env/nation-ips")
public class NationIPController {

    private final NationIPService nationIPService;

    @GetMapping("/nations")
    public ResponseEntity<List<NationIPMgmtDTO>> getNationList(@RequestParam final Map<String, Object> params) {
        final List<NationIPMgmtDTO> list = nationIPService.getNationList(params);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/nations/{nationCd}")
    public ResponseEntity<NationIPMgmtDTO> getNationInfo(@PathVariable final String nationCd) {
        final Map<String, Object> params = new java.util.HashMap<>();
        params.put("nationCd", nationCd);
        final NationIPMgmtDTO info = nationIPService.getNationInfo(params);
        return ResponseEntity.ok(info);
    }

    @GetMapping("/domains")
    public ResponseEntity<List<NationIPMgmtDTO>> getDomainList() {
        final List<NationIPMgmtDTO> list = nationIPService.getDomainList();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/by-nation-code")
    public ResponseEntity<NationIPMgmtDTO> getByNationCode(@RequestBody final Map<String, Object> params) {
        final NationIPMgmtDTO result = nationIPService.getByNationCode(params);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<NationIPMgmtDTO>> getIPList(@RequestParam final String nationCd) {
        final List<NationIPMgmtDTO> list = nationIPService.getIPList(nationCd);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadCSV(@RequestParam("file") final MultipartFile file) {
        nationIPService.uploadCSV(file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete() {
        nationIPService.delete();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/nations/export")
    public ResponseEntity<Map<String, String>> exportNations(@RequestBody final Map<String, Object> params) throws Exception {
        final Map<String, String> result = nationIPService.exportNations(params);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/export")
    public ResponseEntity<Map<String, String>> exportIPs(
            @RequestParam final String nationCd,
            @RequestParam(required = false) final String nationNm,
            @RequestParam(required = false) final String continental) throws Exception {
        final Map<String, String> result = nationIPService.exportIPs(nationCd, nationNm, continental);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/nations/{nationCd}/detail")
    public ResponseEntity<NationIPMgmtDTO> getNationDetail(@PathVariable final String nationCd) {
        final NationIPMgmtDTO detail = nationIPService.getNationDetail(nationCd);
        return ResponseEntity.ok(detail);
    }

    @PutMapping("/nations/{nationCd}")
    public ResponseEntity<Void> editNation(@PathVariable final String nationCd, @RequestBody final NationIPMgmtDTO dto) {
        nationIPService.editNation(nationCd, dto);
        return ResponseEntity.ok().build();
    }
}

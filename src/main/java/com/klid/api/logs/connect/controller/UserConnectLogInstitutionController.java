package com.klid.api.logs.connect.controller;

import com.klid.api.logs.connect.dto.InstitutionChartDTO;
import com.klid.api.logs.connect.dto.InstitutionSearchReqDTO;
import com.klid.api.logs.connect.service.UserConnectLogInstitutionService;
import com.klid.api.logs.common.dto.InstitutionSummaryGridResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs/user-connect/institution")
public class UserConnectLogInstitutionController {

    private final UserConnectLogInstitutionService userConnectLogInstitutionService;

    @PostMapping("/grid/data")
    public ResponseEntity<List<InstitutionSummaryGridResDTO>> getData(@RequestBody final InstitutionSearchReqDTO searchReqDTO) {
        log.info("{}", searchReqDTO);
        final List<InstitutionSummaryGridResDTO> gridData =
                userConnectLogInstitutionService.getGridData(
                        searchReqDTO.getSystemType(),
                        searchReqDTO.getInstitutionCode(),
                        searchReqDTO.getMonth(),
                        searchReqDTO.getCodeLevel());
        return ResponseEntity.ok(gridData);
    }

    @PostMapping("/chart/data")
    public ResponseEntity<InstitutionChartDTO> getChartData(@RequestBody final InstitutionSearchReqDTO searchReqDTO) {
        log.info("{}", searchReqDTO);
        final InstitutionChartDTO chatData =
                userConnectLogInstitutionService.getChatData(
                        searchReqDTO.getSystemType(),
                        searchReqDTO.getInstitutionCode(),
                        searchReqDTO.getMonth(),
                        searchReqDTO.getCodeLevel());
        return ResponseEntity.ok(chatData);
    }
}

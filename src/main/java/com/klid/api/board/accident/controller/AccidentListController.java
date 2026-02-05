package com.klid.api.board.accident.controller;

import com.klid.api.board.accident.dto.AccidentListItemDTO;
import com.klid.api.board.accident.dto.AccidentSearchRequestDTO;
import com.klid.api.board.accident.service.AccidentListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accidents")
public class AccidentListController {

    private final AccidentListService accidentListService;

    /**
     * 사고 신고 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<AccidentListItemDTO>> getAccidentList(@ModelAttribute final AccidentSearchRequestDTO searchRequest) {
        final List<AccidentListItemDTO> accidents = accidentListService.getAccidentList(searchRequest);
        return ResponseEntity.ok(accidents);
    }

    /**
     * 사고 신고 삭제
     */
    @DeleteMapping("/{inciNo}")
    public ResponseEntity<Void> deleteAccident(@PathVariable final String inciNo) {
        accidentListService.deleteAccident(inciNo);
        return ResponseEntity.noContent().build();
    }
}

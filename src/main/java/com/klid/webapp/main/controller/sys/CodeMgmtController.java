package com.klid.webapp.main.controller.sys;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.common.code.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/sys")
@RestController
@RequiredArgsConstructor
public class CodeMgmtController {

	private final CodeService service;

	//코드관리 목록
    @RequestMapping(value="getCodeList")
    public ReturnData getCodeList(@RequestParam Map<String, Object> reqMap) {
        return service.getCodeList(new Criterion(reqMap));
    }

	//코드 등록
	@RequestMapping(value = "addCode", method = RequestMethod.POST)
	public ReturnData addCode(@RequestBody Map<String, Object> reqMap) {
            return service.addCode(new Criterion(reqMap, false));
	}

    @RequestMapping(value = "editCode", method = RequestMethod.POST)
    public ReturnData editCode(@RequestBody Map<String, Object> reqMap) {
        return service.editCode(new Criterion(reqMap, false));
    }

    @RequestMapping(value="getCodeDuplCnt")
    public ReturnData getCodeDuplCnt(@RequestParam Map<String, Object> reqMap) {
            return service.getCodeDuplCnt(new Criterion(reqMap));
    }

    //공휴일 등록
    @RequestMapping(value = "addWeekDay", method = RequestMethod.POST)
    public ReturnData addWeekDay(@RequestBody Map<String, Object> reqMap) {
            return service.addWeekDay(new Criterion(reqMap, false));
    }

    //공휴일 삭제
    @RequestMapping(value = "delWeekDay", method = RequestMethod.POST)
    public ReturnData delWeekDay(@RequestBody Map<String, Object> reqMap) {
        return service.delWeekDay(new Criterion(reqMap, false));
    }

    // 외부사용자 조회
    @RequestMapping(value="getCustUserList")
    public ReturnData getCustUserList(@RequestParam Map<String, Object> reqMap) {
            return service.getCustUserList(new Criterion(reqMap));
    }
    // 외부사용자 등록
    @RequestMapping(value="addCustUser", method = RequestMethod.POST)
    public ReturnData addCustUser(@RequestBody Map<String, Object> reqMap) {
        return service.addCustUser(new Criterion(reqMap));
    }
    // 외부사용자 수정
    @RequestMapping(value="editCustUser", method = RequestMethod.POST)
    public ReturnData editCustUser(@RequestBody Map<String, Object> reqMap) {
        return service.editCustUser(new Criterion(reqMap));
    }
    // 외부사용자 삭제
    @RequestMapping(value="delCustUser", method = RequestMethod.POST)
    public ReturnData delCustUser(@RequestBody Map<String, Object> reqMap) {
        return service.delCustUser(new Criterion(reqMap));
    }

    // 게시판 관리 조회
    @RequestMapping(value="getBoardMgmtList")
    public ReturnData getBoardMgmtList(@RequestParam Map<String, Object> reqMap) {
        return service.getBoardMgmtList(new Criterion(reqMap));
    }

    // 게시판 관리 조회
    @RequestMapping(value="getBoardMgmt")
    public ReturnData getBoardMgmt(@RequestParam Map<String, Object> reqMap) {
        return service.getBoardMgmt(new Criterion(reqMap));
    }

    // 게시판관리 수정
    @RequestMapping(value="editBoardMgmt", method = RequestMethod.POST)
    public ReturnData editBoardMgmt(@RequestBody Map<String, Object> reqMap) {
        return service.editBoardMgmt(new Criterion(reqMap, false));
    }

    //게시판관리 상세설정
    @RequestMapping(value="getDetailBoardMgmtList")
    public ReturnData getDetailBoardMgmtList(@RequestParam Map<String, Object> reqMap) {
        return service.getDetailBoardMgmtList(new Criterion(reqMap));
    }

}

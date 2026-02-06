package com.klid.webapp.main.controller.env;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.env.instMgmt.service.InstMgmtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/env/instMgmt")
@RestController
@RequiredArgsConstructor
public class InstMgmtController {

	private final InstMgmtService service;

	/** 게시판 리스트 받아오기 */
	@RequestMapping(value = "getInstMgmtList")
	public ReturnData getInstMgmtList(@RequestParam Map<String, Object> reqMap) {
		return service.getInstMgmtList(new Criterion(reqMap));
	}

	/** 기관관리 조회 */
	@RequestMapping(value = "getInstMgmtInfo")
	public ReturnData getInstMgmtInfo(@RequestBody Map<String, Object> reqMap) {
			return service.getInstMgmtInfo(new Criterion(reqMap));
	}

	/** 기관관리 코드 중복 조회 */
	@RequestMapping(value = "getInstCdChk")
	public ReturnData getInstCdChk(@RequestBody Map<String, Object> reqMap) {
		return service.getInstCdChk(new Criterion(reqMap));
	}
	
	/** 기관 추가 */
	@RequestMapping(value = "addInstMgmt", method = RequestMethod.POST)
	public ReturnData addInstMgmt(@RequestBody Map<String, Object> reqMap) {
			return service.addInstMgmt(new Criterion(reqMap, false));
	}

	/** 기관 수정 */
	@RequestMapping(value = "saveInstMgmt", method = RequestMethod.POST)
	public ReturnData saveInstMgmt(@RequestBody Map<String, Object> reqMap) {
		return service.saveInstMgmt(new Criterion(reqMap, false));
	}

	/** 기관 삭제 */
	@RequestMapping(value = "delInstMgmt")
	public ReturnData delInstMgmt(@RequestBody Map<String, Object> reqMap) {
		return service.delInstMgmt(new Criterion(reqMap));
	}
	
	/** 엑셀 출력 */
	@RequestMapping(value="export")
	public ReturnData export(@RequestBody Map<String, Object> reqMap, HttpServletResponse response) {
		return service.export(response, new Criterion(reqMap));
	}

}

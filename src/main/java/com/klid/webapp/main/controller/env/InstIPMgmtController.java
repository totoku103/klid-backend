package com.klid.webapp.main.controller.env;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.common.SessionManager;
import com.klid.webapp.main.env.instIPMgmt.service.InstIPMgmtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/env/instIPMgmt")
@RestController
@RequiredArgsConstructor
public class InstIPMgmtController {

	private final InstIPMgmtService service;

	/** 기관IP대역 리스트 조회 */
	@RequestMapping(value = "getInstIPMgmtList")
	public ReturnData getInstIPMgmtList(@RequestParam Map<String, Object> reqMap) {
		return service.getInstIPMgmtList(new Criterion(reqMap));
	}

	/** 기관별 IP정보 리스트 조회  */
	@RequestMapping(value = "getInstIPMgmtList_instCd")
	public ReturnData getInstIPMgmtList_instCd(@RequestBody Map<String, Object> reqMap) {
			return service.getInstIPMgmtList_instCd(new Criterion(reqMap));
	}
	
	/** 기관IP 추가 */
	@RequestMapping(value = "addInstIPMgmt", method = RequestMethod.POST)
	public ReturnData addInstIPMgmt(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
		//사용자 ID 정보
		String usrId = SessionManager.getUser().getUserId();
		reqMap.put("usrId", usrId);

		// 사용자 IP 정보
		String usrIp = request.getRemoteAddr();
		reqMap.put("usrIp", usrIp);

		return service.addInstIPMgmt(new Criterion(reqMap, false));
	}

	/** 기관IP 수정 */
	@RequestMapping(value = "saveInstIPMgmt", method = RequestMethod.POST)
	public ReturnData saveInstIPMgmt(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
		//사용자 ID 정보
		String usrId = SessionManager.getUser().getUserId();
		reqMap.put("usrId", usrId);

		// 사용자 IP 정보
		String usrIp = request.getRemoteAddr();
		reqMap.put("usrIp", usrIp);

		return service.saveInstIPMgmt(new Criterion(reqMap, false));
	}

	/** 기관IP 삭제 */
	@RequestMapping(value = "delInstIPMgmt")
	public ReturnData delInstIPMgmt(@RequestBody Map<String, Object> reqMap) {
			return service.delInstIPMgmt(new Criterion(reqMap));
	}
	
	/** 엑셀 출력 */
	@RequestMapping(value="export")
	public ReturnData export(@RequestBody Map<String, Object> reqMap, HttpServletResponse response) {
		return service.export(response, new Criterion(reqMap));
	}

}

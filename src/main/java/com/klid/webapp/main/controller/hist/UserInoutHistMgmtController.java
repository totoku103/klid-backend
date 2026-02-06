package com.klid.webapp.main.controller.hist;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.hist.userInoutHist.service.UserInoutHistMgmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/main/hist/userInoutHist")
@RestController
@RequiredArgsConstructor
public class UserInoutHistMgmtController {

	private final UserInoutHistMgmtService service;

	/** 접근이력 테이블의 유저 리스트 조회*/
	@RequestMapping(value = "getLogUserList")
	public ReturnData getLogUserList(@RequestParam Map<String, Object> reqMap) {
		return service.getLogUserList(new Criterion(reqMap));
	}
	
	/** 접근이력 리스트 조회 */
	@RequestMapping(value = "getUserInoutHist")
	public ReturnData getUserInoutHist(@RequestParam Map<String, Object> reqMap) {
		return service.getUserInoutHist(new Criterion(reqMap));
	}
}

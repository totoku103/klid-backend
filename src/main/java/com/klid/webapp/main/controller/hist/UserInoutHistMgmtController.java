package com.klid.webapp.main.controller.hist;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.hist.userInoutHist.service.UserInoutHistMgmtService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/main/hist/userInoutHist")
@Controller
public class UserInoutHistMgmtController {

	@Resource(name = "userInoutHistMgmtService")
	private UserInoutHistMgmtService service;

	/** 접근이력 테이블의 유저 리스트 조회*/
	@RequestMapping(value = "getLogUserList")
	public @ResponseBody ReturnData getLogUserList(@RequestParam Map<String, Object> reqMap) {
		return service.getLogUserList(new Criterion(reqMap));
	}
	
	/** 접근이력 리스트 조회 */
	@RequestMapping(value = "getUserInoutHist")
	public @ResponseBody ReturnData getUserInoutHist(@RequestParam Map<String, Object> reqMap) {
		return service.getUserInoutHist(new Criterion(reqMap));
	}
}

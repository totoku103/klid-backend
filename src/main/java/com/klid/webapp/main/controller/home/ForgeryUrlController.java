package com.klid.webapp.main.controller.home;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.home.forgery.service.ForgeryUrlService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/home/forgeryUrl")
@Controller
public class ForgeryUrlController {

	@Resource(name = "forgeryUrlService")
	private ForgeryUrlService service;

	/** 위변조 URL 목록 */
	@RequestMapping(value = "getForgeryUrl")
	public @ResponseBody ReturnData getForgeryUrl(@RequestParam Map<String, Object> reqMap) {
		return service.getForgeryUrl(new Criterion(reqMap));
	}

	/** 위변조 URL 이력 목록 */
	@RequestMapping(value = "getForgeryUrlHist")
	public @ResponseBody ReturnData getForgeryUrlHist(@RequestParam Map<String, Object> reqMap) {
		return service.getForgeryUrlHist(new Criterion(reqMap));
	}

	/** 메인 홈페이지 모니터링 조회 */
	@PostMapping(value = "getMainForgeryHm")
	public @ResponseBody ReturnData getMainForgeryHm(@RequestParam Map<String, Object> reqMap) {
		return service.getMainForgeryHm(new Criterion(reqMap, false));
	}
	
	/** 메인 홈페이지 모니터링 수치 통계 */
	@PostMapping(value = "getMainForgeryCnt")
	public @ResponseBody ReturnData getMainForgeryCnt(@RequestParam Map<String, Object> reqMap) {
		return service.getMainForgeryCnt(new Criterion(reqMap, false));
	}

	@RequestMapping(value = "getByInstNm")
	public @ResponseBody ReturnData getByInstNm(@RequestBody Map<String, Object> reqMap) {
		return service.getByInstNm(new Criterion(reqMap));
	}
}

package com.klid.webapp.common.controller;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.common.code.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/common/code")
@RestController
@RequiredArgsConstructor
public class CodeController {

	private final CodeService service;
	
	//
	@RequestMapping(value="getCommonCode")
	public ReturnData getCommonCode(@RequestParam Map<String, Object> reqMap) {
			return service.getCommonCode(new Criterion(reqMap));
	}

	// 지역코드 조회
	@RequestMapping(value="getLocalCode")
	public ReturnData getLocalCode(@RequestParam Map<String, Object> reqMap) {
		return service.getLocalCode(new Criterion(reqMap));
	}

	// 공통코드 - 설문타입 목록
	@RequestMapping(value="getSurveyType")
	public ReturnData getSurveyType(@RequestParam Map<String, Object> reqMap) {
		return service.getSurveyType(new Criterion(reqMap));
	}
	@RequestMapping(value="getCodeFilePath")
	public ReturnData getCodeFilePath(@RequestParam Map<String, Object> reqMap) {
		return service.getCodeFilePath(new Criterion(reqMap));
	}

    //대시보드 상단 메시지
	@RequestMapping(value="getDashTextCode")
	public ReturnData getDashTextCode(@RequestParam Map<String, Object> reqMap) {
		return service.getDashTextCode(new Criterion(reqMap));
	}

	@RequestMapping(value="getNoticeSrcType")
	public ReturnData getNoticeSrcType(@RequestParam Map<String, Object> reqMap) {
		return service.getNoticeSrcType(new Criterion(reqMap));
	}
}

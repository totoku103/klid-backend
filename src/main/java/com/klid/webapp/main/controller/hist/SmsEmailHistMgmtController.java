package com.klid.webapp.main.controller.hist;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.hist.smsEmailHist.service.SmsEmailHistMgmtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/main/hist/smsEmailHist")
@RestController
@RequiredArgsConstructor
public class SmsEmailHistMgmtController {

	private final SmsEmailHistMgmtService service;

	@RequestMapping(value = "getSmsHist")
	public ReturnData getSmsHist(@RequestParam Map<String, Object> reqMap) {
		return service.getSmsHist(new Criterion(reqMap));
	}

	@RequestMapping(value = "getEmailHist")
	public ReturnData getEmailHist(@RequestParam Map<String, Object> reqMap) {
		return service.getEmailHist(new Criterion(reqMap));
	}
	
	/** 엑셀 출력 (SMS) */
	@RequestMapping(value="export_sms")
	public ReturnData export_sms(@RequestBody Map<String, Object> reqMap, HttpServletResponse response) {
		return service.export_sms(response, new Criterion(reqMap));
	}
	
	/** 엑셀 출력 (E-MAIL) */
	@RequestMapping(value="export_email")
	public ReturnData export_email(@RequestBody Map<String, Object> reqMap, HttpServletResponse response) {
		return service.export_email(response, new Criterion(reqMap));
	}
}

package com.klid.webapp.main.controller.sms;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/main/sms")
@RestController
@RequiredArgsConstructor
public class SmsController {

	private final SmsService service;

	@RequestMapping(value = "addSmsMessage", method = RequestMethod.POST)
	public ReturnData addSmsMessage(@RequestBody Map<String, Object> reqMap) {
		return service.addSmsMessage(new Criterion(reqMap, false));
	}

}

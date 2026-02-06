package com.klid.webapp.main.controller.hist;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.hist.userActHist.service.UserActHistService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/main/hist/userActHist")
@Controller
public class UserActHistController {

	@Resource(name = "userActHistService")
	private UserActHistService service;

	@RequestMapping(value = "getUserActHistList")
	public @ResponseBody ReturnData getUserActHistList(@RequestParam Map<String, Object> reqMap) {
		return service.getUserActHist(new Criterion(reqMap));
	}
}

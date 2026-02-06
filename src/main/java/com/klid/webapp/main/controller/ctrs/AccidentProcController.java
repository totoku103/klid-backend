package com.klid.webapp.main.controller.ctrs;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.rpt.reportDaily.service.ReportDailyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/main/ctrs/accidentProc")
@Controller
public class AccidentProcController {

	@Resource(name = "reportDailyService")
	private ReportDailyService service;

	@RequestMapping(value = "getReportDayStat")
	public @ResponseBody ReturnData getReportDayStat(@RequestParam Map<String, Object> reqMap) {
		return service.getReportDayStat(new Criterion(reqMap));
	}
}

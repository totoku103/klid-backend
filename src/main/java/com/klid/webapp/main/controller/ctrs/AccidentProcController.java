package com.klid.webapp.main.controller.ctrs;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.rpt.reportDaily.service.ReportDailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/main/ctrs/accidentProc")
@RestController
@RequiredArgsConstructor
public class AccidentProcController {

	private final ReportDailyService service;

	@RequestMapping(value = "getReportDayStat")
	public ReturnData getReportDayStat(@RequestParam Map<String, Object> reqMap) {
		return service.getReportDayStat(new Criterion(reqMap));
	}
}

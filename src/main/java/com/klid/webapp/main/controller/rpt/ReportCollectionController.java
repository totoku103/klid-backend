package com.klid.webapp.main.controller.rpt;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.rpt.reportCollection.service.ReportCollectionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/main/rpt/reportCollection")
@RestController
@RequiredArgsConstructor
public class ReportCollectionController {

	private final ReportCollectionService service;

	@RequestMapping(value = "getRetrieveSecurityHackingDetail")
	public ReturnData getRetrieveSecurityHackingDetail(@RequestParam Map<String, Object> reqMap) {
			return service.getRetrieveSecurityHackingDetail(new Criterion(reqMap));
	}

	@RequestMapping(value = "getSecuListDetail")
	public ReturnData getSecuListDetail(@RequestParam Map<String, Object> reqMap) {
			return service.getSecuListDetail(new Criterion(reqMap));
	}

	@RequestMapping(value = "getNoticeListDetail")
	public ReturnData getNoticeListDetail(@RequestParam Map<String, Object> reqMap) {
		return service.getNoticeListDetail(new Criterion(reqMap));
	}

	@RequestMapping(value = "getRetrieveSecurityVulnerabilityDetail")
	public ReturnData getRetrieveSecurityVulnerabilityDetail(@RequestParam Map<String, Object> reqMap) {
		return service.getRetrieveSecurityVulnerabilityDetail(new Criterion(reqMap));
	}

	/** 공지사항현황 엑셀 출력 */
	@RequestMapping(value="exportNoticeList")
	public ReturnData exportNoticeList(@RequestBody Map<String, Object> reqMap, HttpServletResponse response) {
		return service.exportNoticeList(response, new Criterion(reqMap));
	}

	@RequestMapping(value = "getRetrieveIncidentDetail")
	public ReturnData getRetrieveIncidentDetail(@RequestParam Map<String, Object> reqMap) {
			return service.getRetrieveIncidentDetail(new Criterion(reqMap));
	}

	/** 보안자료실현황 엑셀 출력 */
	@RequestMapping(value="exportSecuList")
	public ReturnData exportSecuList(@RequestBody Map<String, Object> reqMap, HttpServletResponse response) {
			return service.exportSecuList(response, new Criterion(reqMap));
	}

	/** 해킹관리대장 엑셀 출력 */
	@RequestMapping(value="exportRetrieveSecurityHacking")
	public ReturnData exportRetrieveSecurityHacking(@RequestBody Map<String, Object> reqMap, HttpServletResponse response) {
			return service.exportRetrieveSecurityHacking(response, new Criterion(reqMap));
	}

	/** 취약점관리대장 엑셀 출력 */
	@RequestMapping(value="exportRetrieveSecurityVulnerability")
	public ReturnData exportRetrieveSecurityVulnerability(@RequestBody Map<String, Object> reqMap, HttpServletResponse response) {
			return service.exportRetrieveSecurityVulnerability(response, new Criterion(reqMap));
	}

	/** 처리중현황 엑셀 출력 */
	@RequestMapping(value="exportRetrieveIncidentDetail")
	public ReturnData exportRetrieveIncidentDetail(@RequestBody Map<String, Object> reqMap, HttpServletResponse response) {
			return service.exportRetrieveIncidentDetail(response, new Criterion(reqMap));
	}

	/** 일일운영현황 엑셀 출력 */
	@RequestMapping(value="exportReportCtrsDaily")
	public ReturnData exportReportCtrsDaily(@RequestBody Map<String, Object> reqMap, HttpServletResponse response) {
			return service.exportReportCtrsDaily(response, new Criterion(reqMap));
	}
}

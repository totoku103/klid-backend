package com.klid.webapp.main.controller.home;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ErrorInfo;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.home.healthCheck.service.HealthCheckUrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/home/healthCheckUrl")
@RestController
@RequiredArgsConstructor
public class HealthCheckUrlController {

	private final HealthCheckUrlService service;

	/** 헬스체크 URL 목록 */
	@RequestMapping(value = "getHealthCheckUrl")
	public ReturnData getHealthCheckUrl(@RequestParam Map<String, Object> reqMap) {
			return service.getHealthCheckUrl(new Criterion(reqMap));
	}

	/** 헬스체크 URL 등록 */
	@RequestMapping(value="addHealthCheckUrl" , method = RequestMethod.POST)
	public ReturnData HealthCheckUrl(@RequestBody Map<String, Object> reqMap) {
		try {
			return service.addHealthCheckUrl(new Criterion(reqMap, false));
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnData(new ErrorInfo(e));
		}
	}

	/** 헬스체크 URL 수정 */
	@RequestMapping(value = "editHealthCheckUrl", method = RequestMethod.POST)
	public ReturnData editHealthCheckUrl(@RequestBody Map<String, Object> reqMap) {
		return service.editHealthCheckUrl(new Criterion(reqMap));
	}

	/** 헬스체크 URL 집중관리등록 */
	@RequestMapping(value = "editWatchOn", method = RequestMethod.POST)
	public ReturnData editWatchOn(@RequestBody Map<String, Object> reqMap) {
		return service.editWatchOn(new Criterion(reqMap));
	}

	/** 헬스체크 URL 집중관리해제 */
	@RequestMapping(value = "editWatchOff", method = RequestMethod.POST)
	public ReturnData editWatchOff(@RequestBody Map<String, Object> reqMap) {
		return service.editWatchOff(new Criterion(reqMap));
	}

	/** 헬스체크 URL 상세 */
	@RequestMapping(value = "getDetailHealthCheckUrl")
	public ReturnData getDetailHealthCheckUrl(@RequestParam Map<String, Object> reqMap) {
		return service.getDetailHealthCheckUrl(new Criterion(reqMap));
	}

	/** 헬스체크 URL 삭제 */
	@RequestMapping(value = "delHealthCheckUrl", method = RequestMethod.POST)
	public ReturnData delHealthCheckUrl(@RequestBody Map<String, Object> reqMap) {
			return service.delHealthCheckUrl(new Criterion(reqMap, false));
	}

	/** 헬스체크 장애이력 목록 */
	@RequestMapping(value = "getHealthCheckHist")
	public ReturnData getHealthCheckHist(@RequestParam Map<String, Object> reqMap) {
		return service.getHealthCheckHist(new Criterion(reqMap));
	}

	/** 헬스체크 상태 조회 */
	@RequestMapping(value = "getHealthCheckStat")
	public ReturnData getHealthCheckStat(@RequestParam Map<String, Object> reqMap) {
		return service.getHealthCheckStat(new Criterion(reqMap));
	}

	/** 엑셀 출력 */
	@RequestMapping(value="export")
	public ReturnData export(@RequestBody Map<String, Object> reqMap, HttpServletResponse response) {
		return service.export(new Criterion(reqMap));
	}

	/** 엑셀 Import */
	@RequestMapping(value="importXls")
	public ReturnData importXls(@RequestParam Map<String, Object> reqMap, HttpServletResponse response) {
		return service.importXls(new Criterion(reqMap));
	}
}

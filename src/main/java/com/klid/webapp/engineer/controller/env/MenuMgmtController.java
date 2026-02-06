package com.klid.webapp.engineer.controller.env;

import com.klid.common.AppGlobal;
import com.klid.common.CommonController;
import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.engineer.menuMgmt.service.MenuMgmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/engineer/menuMgmt")
@RestController("engineer.menuMgmtController")
@RequiredArgsConstructor
public class MenuMgmtController extends CommonController {

	private final MenuMgmtService service;

	
	@RequestMapping(value="getPageList")
	public ReturnData getPageList(@RequestParam Map<String, Object> reqMap) {
		Criterion criterion = new Criterion(reqMap);
		criterion.addParam("siteName", AppGlobal.webSiteName);
			
		return service.getPageList(criterion);
	}
	
	@RequestMapping(value="getPageGroupList")
	public ReturnData getPageGroupList(@RequestParam Map<String, Object> reqMap) {
		Criterion criterion = new Criterion(reqMap);
		criterion.addParam("siteName", AppGlobal.webSiteName);

		return service.getPageGroupList(criterion);
	}
	
	@RequestMapping(value="getMenuList")
	public ReturnData getMenuList(@RequestParam Map<String, Object> reqMap) {
		Criterion criterion = new Criterion(reqMap);
		criterion.addParam("siteName", AppGlobal.webSiteName);

		return service.getMenuList(criterion);
	}
	
	
}

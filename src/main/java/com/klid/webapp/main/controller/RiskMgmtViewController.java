package com.klid.webapp.main.controller;
import com.klid.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main/sys")
@Controller
public class RiskMgmtViewController extends CommonController {
	
	//위협등급 관리 목록
	@RequestMapping("riskMgmt.do") public void riskMgmt(Model model){
		setBaseInfo(model);
	}
}

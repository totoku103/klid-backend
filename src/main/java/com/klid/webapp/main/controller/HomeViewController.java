package com.klid.webapp.main.controller;

import com.klid.common.CommonController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main/home")
@Controller
public class HomeViewController extends CommonController {
	//위변조URL
	@RequestMapping("forgeryUrl.do") public void userConf(Model model, HttpServletRequest req) {
		if(req.getParameter("instCd") != null){
			model.addAttribute("instCd", req.getParameter("instCd"));
		}
		setBaseInfo(model);
	}

	//위변조URL이력
	@RequestMapping("forgeryUrlHist.do") public void forgeryUrlHist(Model model, HttpServletRequest req) {
		if(req.getParameter("instCd") != null){
			model.addAttribute("instCd", req.getParameter("instCd"));
		}
		setBaseInfo(model);
	}

	//헬스체크URL
	@RequestMapping("healthCheckUrl.do") public void healthCheckUrl(Model model, HttpServletRequest req) {
		if(req.getParameter("instCd") != null){
			model.addAttribute("instCd", req.getParameter("instCd"));
		}
		setBaseInfo(model);
	}

	//헬스체크 장애이력
	@RequestMapping("healthCheckHist.do") public void healthCheckHist(Model model) { setBaseInfo(model); }

	//헬스체크 현황조회
	@RequestMapping("healthCheckStat.do") public void healthCheckStat(Model model) { setBaseInfo(model); }
	
}

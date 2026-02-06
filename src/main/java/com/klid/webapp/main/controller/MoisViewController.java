package com.klid.webapp.main.controller;

import com.klid.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main/mois")
@Controller
public class MoisViewController extends CommonController {
	//행안부 대시보드
	@RequestMapping("dashConfig.do") public void dashConfig(Model model) { setBaseInfo(model); }
	
}

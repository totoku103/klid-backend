package com.klid.webapp.main.controller;
import com.klid.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main/acc")
@Controller
public class AccidentViewController extends CommonController {
	
	//사고신고 및 처리현황 목록
	@RequestMapping("accidentApplyList.do") public void accidentApplyList(Model model){
		setBaseInfo(model);
	}
}

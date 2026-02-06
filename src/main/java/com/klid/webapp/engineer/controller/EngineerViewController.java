package com.klid.webapp.engineer.controller;

import com.klid.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/engineer")
@Controller("engineerViewController")
public class EngineerViewController extends CommonController {

	// 메뉴설정
	@RequestMapping("menuMgmt") public void menuMgmt(Model model) { setBaseInfo(model); }
	
	// 메뉴권한설정
	@RequestMapping("menuGrpMgmt") public void menuGrpMgmt(Model model) { setBaseInfo(model); }

	// 기본그룹관리
	@RequestMapping("defGrpConf") public void defGrpConf(Model model) { setBaseInfo(model); }
	
	// 권한그룹관리
	@RequestMapping("authGrpConf") public void authGrpConf(Model model) { setBaseInfo(model); }

	//사고접수 이메일 암호화 설정
	@RequestMapping("encrySync") public void encrySync(Model model) { setBaseInfo(model); }

	//비밀번호 전체 초기화
	@RequestMapping("passReset") public void passReset(Model model) { setBaseInfo(model); }
	
}

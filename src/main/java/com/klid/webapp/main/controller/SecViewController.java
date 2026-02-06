package com.klid.webapp.main.controller;
import com.klid.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main/sec")
@Controller
public class SecViewController extends CommonController {
	
	//보안>공지사항
	@RequestMapping("noticeBoardList.do") public void noticeBoardList(Model model){
		setBaseInfo(model);
	}
	
	//보안>보안자료실
	@RequestMapping("resourceBoardList.do") public void resourceBoardList(Model model){
		setBaseInfo(model);
	}

	//보안>침해대응정보공유
	@RequestMapping("shareBoardList.do") public void shareBoardList(Model model){
		setBaseInfo(model);
	}

	//문의센터>문의/의견
	@RequestMapping("qnaBoardList.do") public void qnaBoardList(Model model) {
		setBaseInfo(model);
	}
	
	//운영관리>인수인계
	@RequestMapping("takeOverBoardList.do") public void takeOverBoardList(Model model) {
		setBaseInfo(model);
	}

	//행안부
	@RequestMapping("moisBoardList.do") public void moisBoardList(Model model){
		setBaseInfo(model);
	}
}

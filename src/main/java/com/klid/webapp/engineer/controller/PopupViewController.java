package com.klid.webapp.engineer.controller;

import com.klid.common.CommonController;
import com.klid.webapp.engineer.popup.service.PopupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/engineer/popup")
@Controller("engineer.popupViewController")
@RequiredArgsConstructor
public class PopupViewController extends CommonController {

	private final PopupService service;
	
	@RequestMapping("/pPageAdd") public void pPageAdd(Model model) {}
	@RequestMapping("/pPageGroupAdd") public void pPageGroupAdd(Model model) {}
	@RequestMapping("/pMenuAdd") public void pMenuAdd(Model model) {}
	
}

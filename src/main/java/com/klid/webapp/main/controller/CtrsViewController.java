package com.klid.webapp.main.controller;

import com.klid.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main/ctrs")
@Controller
public class CtrsViewController extends CommonController {

	@RequestMapping("accidentProcState.do") public void accidentProcState(Model model) { setBaseInfo(model); }

}

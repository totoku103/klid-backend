package com.klid.webapp.webdash.controller;

import com.klid.common.CommonController;
import com.klid.webapp.common.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/webdash")
@Controller
public class WebDashViewController extends CommonController {
	@RequestMapping("{path}") public String dashView(Model model, @PathVariable("path") String path, HttpServletRequest request) {
       String dashLocal =  request.getParameter("dashLocal");
	    if(dashLocal == null){
            //dashLocal = "10";
            dashLocal = Integer.toString(SessionManager.getUser().getLocalCd());//20190412 시도대시보드 주소복사시 서울페이지가 나온다고 하여 수정
        }
        model.addAttribute("dashLocal",dashLocal);
		return "/webdash/"+path;
	}
}

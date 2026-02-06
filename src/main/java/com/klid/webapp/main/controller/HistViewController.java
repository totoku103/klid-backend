package com.klid.webapp.main.controller;

import com.klid.common.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main/hist/")
@Controller
public class HistViewController extends CommonController {
	/** SMS-EMAIL 이력관리 */
    @RequestMapping(value="smsEmailHistMgmt.do")
    public void smsEmailHistMgmt(Model model) {
        setBaseInfo(model);
    }
    
    /** 사용자접속 로그관리 */
    @RequestMapping(value="userInoutHistMgmt.do")
    public void userInoutHistMgmt(Model model) {
        setBaseInfo(model);
    }

    @RequestMapping(value="userActHist.do")
    public void userActHist(Model model) {
        setBaseInfo(model);
    }
    
}
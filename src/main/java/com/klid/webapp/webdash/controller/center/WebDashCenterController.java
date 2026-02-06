package com.klid.webapp.webdash.controller.center;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ErrorInfo;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.webdash.center.service.WebDashCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Map;

@RequestMapping("/api/webdash/center/webDashCenter")
@RestController
@RequiredArgsConstructor
public class WebDashCenterController {

    private final WebDashCenterService service;

    //공격국가 TOP5
    @RequestMapping(value = "getAttNationTop5")
    public ReturnData getIncidentStatus(@RequestParam Map<String, Object> reqMap) {
        try {
            Criterion criterion = new Criterion(reqMap, false);

            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            if (hour >= 0 && hour < 6) {
                criterion.addParam("atype", 0);
            } else criterion.addParam("atype", 1);

            return service.getAttNationTop5(criterion);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(new ErrorInfo(e));
        }
    }
    //유형별차트
    @RequestMapping(value = "getTypeChart")
    public ReturnData getTypeChart(@RequestParam Map<String, Object> reqMap) {
        try {
            Criterion criterion = new Criterion(reqMap, false);

            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            if (hour >= 0 && hour < 6) {
                criterion.addParam("atype", 0);
            } else criterion.addParam("atype", 1);
            return service.getTypeChart(criterion);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(new ErrorInfo(e));
        }
    }
    
    @RequestMapping(value = "getEvtChart")
    public ReturnData getEvtChart(@RequestParam Map<String, Object> reqMap) {
        try {
            return service.getEvtChart(new Criterion(reqMap, false));
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(new ErrorInfo(e));
        }
    }

    @RequestMapping(value = "getEvtAllChart")
    public ReturnData getEvtAllChart(@RequestParam Map<String, Object> reqMap) {
        try {
            return service.getEvtAllChart(new Criterion(reqMap, false));
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(new ErrorInfo(e));
        }
    }
}

package com.klid.webapp.webdash.controller;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ErrorInfo;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.webdash.adminControl.service.AdminControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Map;

@RequestMapping("/api/webdash/adminControl")
@RestController("webdash.adminControlController")
@RequiredArgsConstructor
public class AdminControlController {

    private final AdminControlService service;

    @RequestMapping(value = "getIncidentStatus")
    public ReturnData getIncidentStatus(@RequestParam Map<String, Object> reqMap) {
        try {
            Criterion criterion = new Criterion(reqMap, false);

            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            if (hour >= 0 && hour < 6) {
                criterion.addParam("atype", 0);
            } else criterion.addParam("atype", 1);

            return service.getIncidentStatus(criterion);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(new ErrorInfo(e));
        }
    }

    @RequestMapping(value = "getInciCnt")
    public ReturnData getInciCnt(@RequestParam Map<String, Object> reqMap) {
        try {
            Criterion criterion = new Criterion(reqMap, false);

            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            if (hour >= 0 && hour < 6) {
                criterion.addParam("atype", 0);
            } else criterion.addParam("atype", 1);
            return service.getInciCnt(criterion);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(new ErrorInfo(e));
        }
    }

    @RequestMapping(value = "getTbzledgeCnt")
    public ReturnData getTbzledgeCnt(@RequestParam Map<String, Object> reqMap) {
        try {
            Criterion criterion = new Criterion(reqMap, false);

            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            if (hour >= 0 && hour < 6) {
                criterion.addParam("atype", 0);
            } else criterion.addParam("atype", 1);
            return service.getTbzledgeCnt(criterion);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(new ErrorInfo(e));
        }
    }

    @RequestMapping(value = "getLocalInciCnt")
    public ReturnData getLocalInciCnt(@RequestParam Map<String, Object> reqMap) {
        try {
            Criterion criterion = new Criterion(reqMap, false);

            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            if (hour >= 0 && hour < 6) {
                criterion.addParam("atype", 0);
            } else criterion.addParam("atype", 1);
            return service.getLocalInciCnt(criterion);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(new ErrorInfo(e));
        }
    }

    @RequestMapping(value = "getLocalStatus")
    public ReturnData getLocalStatus(@RequestParam Map<String, Object> reqMap) {
        try {
            return service.getLocalStatus(new Criterion(reqMap, false));
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(new ErrorInfo(e));
        }
    }

    @RequestMapping(value = "getUrlStatus")
    public ReturnData getUrlStatus(@RequestParam Map<String, Object> reqMap) {
        try {
            return service.getUrlStatus(new Criterion(reqMap, false));
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(new ErrorInfo(e));
        }
    }

    @RequestMapping(value = "getSysErrorStatus")
    public ReturnData getSysErrorStatus(@RequestParam Map<String, Object> reqMap) {
        try {
            return service.getSysErrorStatus(new Criterion(reqMap, false));
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(new ErrorInfo(e));
        }
    }

    @RequestMapping(value = "getInciTypeCnt")
    public ReturnData getInciTypeCnt(@RequestParam Map<String, Object> reqMap) {
        try {
            Criterion criterion = new Criterion(reqMap, false);

            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            if (hour >= 0 && hour < 6) {
                criterion.addParam("atype", 0);
            } else criterion.addParam("atype", 1);
            return service.getInciTypeCnt(criterion);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnData(new ErrorInfo(e));
        }
    }

}

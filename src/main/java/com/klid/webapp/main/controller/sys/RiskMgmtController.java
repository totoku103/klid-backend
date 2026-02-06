package com.klid.webapp.main.controller.sys;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.sys.riskMgmt.service.RiskMgmtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/sys")
@RestController
@RequiredArgsConstructor
public class RiskMgmtController {

	private final RiskMgmtService service;

    @RequestMapping(value="getRiskMgmt")
    public ReturnData getRiskMgmt(@RequestParam Map<String, Object> reqMap) {
            return service.getRiskMgmt(new Criterion(reqMap));
    }

    @RequestMapping(value = "editRiskMgmt", method = RequestMethod.POST)
    public ReturnData editAccidentApply(@RequestBody Map<String, Object> reqMap) {
        return service.editRiskMgmt(new Criterion(reqMap, false));
    }

    @RequestMapping(value="getRiskHistory")
    public ReturnData getRiskHistory(@RequestParam Map<String, Object> reqMap) {
        return service.getRiskHistory(new Criterion(reqMap));
    }

    @RequestMapping(value="addRiskHistory" , method = RequestMethod.POST)
    public ReturnData addRiskHistory(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
            return service.addRiskHistory(new Criterion(reqMap, false), request);
    }

    @RequestMapping(value = "delRiskHistory", method = RequestMethod.POST)
    public ReturnData delRiskHistory(@RequestBody Map<String, Object> reqMap) {
        return service.delRiskHistory(new Criterion(reqMap, false));
    }

    @RequestMapping(value="getThreatLevel")
    public ReturnData getThreatLevel(@RequestParam Map<String, Object> reqMap) {
        return service.getThreatLevel(new Criterion(reqMap));
    }

    @RequestMapping(value="getThreatNow")
    public ReturnData getThreatNow(@RequestParam Map<String, Object> reqMap) {
        return service.getThreatNow(new Criterion(reqMap));
    }

    @RequestMapping(value="getThreatHist")
    public ReturnData getThreatHist(@RequestParam Map<String, Object> reqMap) {
        return service.getThreatHist(new Criterion(reqMap));
    }

    @RequestMapping(value = "editThreat", method = RequestMethod.POST)
    public ReturnData editThreat(@RequestBody Map<String, Object> reqMap) {
            return service.editThreat(new Criterion(reqMap, false));
    }

    @RequestMapping(value="getPeriodNow")
    public ReturnData getPeriodNow(@RequestParam Map<String, Object> reqMap) {
        return service.getPeriodNow(new Criterion(reqMap));
    }

    @RequestMapping(value = "editPeriod", method = RequestMethod.POST)
    public ReturnData editPeriod(@RequestBody Map<String, Object> reqMap) {
        return service.editPeriod(new Criterion(reqMap, false));
    }
}

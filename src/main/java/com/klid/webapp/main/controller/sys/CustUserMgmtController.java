package com.klid.webapp.main.controller.sys;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.sys.custUserMgmt.service.CustUserMgmtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/sys/custUserMgmt")
@RestController
@RequiredArgsConstructor
public class CustUserMgmtController {

    private final CustUserMgmtService service;

    /** SMS 전송 팝업 사용자 목록 조회 */
    @PostMapping(value = "getSmsUserList")
    public ReturnData getSmsUserList(@RequestParam Map<String, Object> reqMap) {
        return service.getSmsUserList(new Criterion(reqMap, false));
    }

    /** SMS 전송 팝업 외부 사용자 목록 조회 */
    @PostMapping(value = "getSmsOfUserList")
    public ReturnData getSmsOfUserList(@RequestParam Map<String, Object> reqMap) {
        return service.getSmsOfUserList(new Criterion(reqMap, false));
    }

    /** 사용자 폰번호 조회 */
    @PostMapping(value = "getUserPhone")
    public ReturnData getUserPhone(@RequestParam Map<String, Object> reqMap) {
        return service.selectUserPhone(new Criterion(reqMap, false));
    }

    @RequestMapping(value = "getSmsGroup")
    public ReturnData getSmsGroup(@RequestParam Map<String, Object> reqMap) {
        return service.getSmsGroup(new Criterion(reqMap));
    }

    @RequestMapping(value="addSmsGroup" , method = RequestMethod.POST)
    public ReturnData addSmsGroup(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
        return service.addSmsGroup(new Criterion(reqMap));
    }

    @RequestMapping(value="editSmsGroup" , method = RequestMethod.POST)
    public ReturnData editSmsGroup(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
        return service.editSmsGroup(new Criterion(reqMap));
    }

    @RequestMapping(value="delSmsGroup" , method = RequestMethod.POST)
    public ReturnData delSmsGroup(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
        return service.delSmsGroup(new Criterion(reqMap));
    }

}

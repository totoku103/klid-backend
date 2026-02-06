package com.klid.webapp.webdash.controller.sido;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.webdash.sido.service.WebDashSidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/webdash/sido/webDashSido")
@RestController
@RequiredArgsConstructor
public class WebDashSidoController {

    private final WebDashSidoService service;

    /**공지사항리스트 */
    @RequestMapping(value = "getNoticeList")
    public ReturnData getNoticeList(@RequestParam Map<String, Object> reqMap) {
        return service.getNoticeList(new Criterion(reqMap, false));
    }

    /**보안리스트 */
    @RequestMapping(value = "getSecuList")
    public ReturnData getSecuList(@RequestParam Map<String, Object> reqMap) {
        return service.getSecuList(new Criterion(reqMap, false));
    }

    /**수동차단*/
    @RequestMapping(value = "getRegionStatusManual")
    public ReturnData getRegionStatusManual(@RequestParam Map<String, Object> reqMap) {
            return service.getRegionStatusManual(new Criterion(reqMap, false));
    }

    /**위변조*/
    @RequestMapping(value = "getForgeryCheck")
    public ReturnData getForgeryCheck(@RequestParam Map<String, Object> reqMap) {
        return service.getForgeryCheck(new Criterion(reqMap, false));
    }

    /**헬스체크*/
    @RequestMapping(value = "getHcCheck")
    public ReturnData getHcCheck(@RequestParam Map<String, Object> reqMap) {
        return service.getHcCheck(new Criterion(reqMap, false));
    }

    /**처리현황*/
    @RequestMapping(value = "getProcess")
    public ReturnData getProcess(@RequestParam Map<String, Object> reqMap) {
        return service.getProcess(new Criterion(reqMap, false));
    }

    /**시도리스트*/
    @RequestMapping(value = "getSidoList")
    public ReturnData getSidoList(@RequestParam Map<String, Object> reqMap) {
        return service.getSidoList(new Criterion(reqMap, false));
    }

}

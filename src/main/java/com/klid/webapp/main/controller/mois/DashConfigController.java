package com.klid.webapp.main.controller.mois;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.mois.dashConfig.service.DashConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/main/mois/dashConfig")
@RestController
@RequiredArgsConstructor
public class DashConfigController {

    private final DashConfigService service;

    /**행안부 리스트 조회*/
    @RequestMapping(value = "getDashConfigList" )
    public ReturnData getDashConfigList(@RequestParam Map<String, Object> reqMap) {
        return service.getDashConfigList(new Criterion(reqMap, false));
    }

    /** 행안부 대시보드 등록 */
    @PostMapping(value = "addDashConfig")
    public ReturnData addDashConfig(@RequestParam Map<String, Object> reqMap) {
        return service.addDashConfig(new Criterion(reqMap, false));
    }

    /** 행안부 대시보드 수정 */
    @PostMapping(value = "editDashConfig")
    public ReturnData editDashConfig(@RequestParam Map<String, Object> reqMap) {
            return service.editDashConfig(new Criterion(reqMap, false));
    }
}

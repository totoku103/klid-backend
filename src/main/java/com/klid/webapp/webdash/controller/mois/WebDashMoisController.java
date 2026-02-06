package com.klid.webapp.webdash.controller.mois;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.webdash.mois.service.WebDashMoisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/api/webdash/mois/webDashMois")
@RestController
@RequiredArgsConstructor
public class WebDashMoisController {

    private final WebDashMoisService service;

    /**행안부 사이버 위기경보 */
    @RequestMapping(value = "getThreatNow")
    public ReturnData getThreatNow(@RequestParam Map<String, Object> reqMap) {
        return service.getThreatNow(new Criterion(reqMap, false));
    }

    /**홈페이지 모니터링 (중앙행정기관)  */
    @RequestMapping(value = "getHmHcUrlCenter")
    public ReturnData getHmHcUrlCenter(@RequestParam Map<String, Object> reqMap) {
        return service.getHmHcUrlCenter(new Criterion(reqMap, false));
    }

    /**홈페이지 모니터링 (지방자치단체)  */
    @RequestMapping(value = "getHmHcUrlRegion")
    public ReturnData getHmHcUrlRegion(@RequestParam Map<String, Object> reqMap) {
        return service.getHmHcUrlRegion(new Criterion(reqMap, false));
    }

    /**홈페이지 위변조 (지방자치단체)  */
    @RequestMapping(value = "getForgeryRegion")
    public ReturnData getForgeryRegion(@RequestParam Map<String, Object> reqMap) {
        return service.getForgeryRegion(new Criterion(reqMap, false));
    }

    /**지방자치단체 사이버위협 대응현황 (지도표시)*/
    @RequestMapping(value = "getRegionStatus")
    public ReturnData getRegionStatus(@RequestParam Map<String, Object> reqMap) {
            return service.getRegionStatus(new Criterion(reqMap, false));
    }

    /**지방자치단체 사이버위협 대응현황 (자동차단)*/
    @RequestMapping(value = "getRegionStatusAuto")
    public ReturnData getRegionStatusAuto(@RequestParam Map<String, Object> reqMap) {
            return service.getRegionStatusAuto(new Criterion(reqMap, false));
    }

    /**지방자치단체 사이버위협 대응현황 (수동차단)*/
    @RequestMapping(value = "getRegionStatusManual")
    public ReturnData getRegionStatusManual(@RequestParam Map<String, Object> reqMap) {
        return service.getRegionStatusManual(new Criterion(reqMap, false));
    }

    /**행안부 리스트 조회*/
    @RequestMapping(value = "getDashConfigList" )
    public ReturnData getDashConfigList(@RequestParam Map<String, Object> reqMap) {
        return service.getDashConfigList(new Criterion(reqMap, false));
    }

    /**행안부 중앙,지방 차트 합계 */
    @RequestMapping(value = "getDashChartSum")
    public ReturnData getDashChartSum(@RequestParam Map<String, Object> reqMap) {
        return service.getDashChartSum(new Criterion(reqMap, false));
    }
}

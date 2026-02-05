package com.klid.api.logs.connect.service;

import com.klid.api.logs.connect.dto.InstitutionChartDTO;
import com.klid.api.logs.connect.dto.InstitutionChartYAxisDTO;
import com.klid.api.logs.connect.persistence.UserConnectLogInstitutionMapper;
import com.klid.api.logs.common.dto.InstitutionSummaryGridResDTO;
import com.klid.api.logs.common.dto.ThirdPartySystemType;
import com.klid.api.logs.common.service.InstitutionAggregateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserConnectLogInstitutionService {

    private final InstitutionAggregateService institutionAggregateService;
    private final UserConnectLogInstitutionMapper userConnectLogInstitutionMapper;

    public List<InstitutionSummaryGridResDTO> getGridData(final ThirdPartySystemType systemType,
                                                           final String institutionCode,
                                                           final String month,
                                                           final Integer codeLevel) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final List<InstitutionSummaryGridResDTO> result;
        if (ThirdPartySystemType.CTRS.equals(systemType)) {
            log.debug("CTRS Search. {} {}", institutionCode, month);
            final List<InstitutionSummaryGridResDTO> institutionSummaryGridResDTOS =
                    userConnectLogInstitutionMapper.selectCTRSSystemGridList(institutionCode, codeLevel, month);
            result = institutionAggregateService.aggregate(institutionSummaryGridResDTOS);
        } else {
            log.debug("System Search. {} {} {}", systemType, institutionCode, month);
            result = userConnectLogInstitutionMapper.selectOtherSystemGridList(systemType.name(), institutionCode, month);
        }

        stopWatch.stop();
        log.info("result size: {}, elapsed time: {} ms", result.size(), stopWatch.getTotalTimeMillis());
        return result;
    }

    public InstitutionChartDTO getChatData(final ThirdPartySystemType systemType,
                                           final String institutionCode,
                                           final String month,
                                           final Integer codeLevel) {
        final List<Integer> chartXAxis = userConnectLogInstitutionMapper.selectAllSystemChartXAxisList(month);
        final List<InstitutionChartYAxisDTO> chartData;

        if (ThirdPartySystemType.CTRS.equals(systemType)) {
            chartData = userConnectLogInstitutionMapper.selectCTRSChartDataList(institutionCode, codeLevel, month);
        } else {
            chartData = userConnectLogInstitutionMapper.selectOtherChartDataList(systemType.name(), institutionCode, month);
        }

        final List<Integer> sortChartXAxis = chartXAxis.stream().sorted().collect(Collectors.toList());
        final Map<String, List<InstitutionChartYAxisDTO>> instCodeCollect = chartData.stream()
                .filter(d -> d.getInstitutionCode() != null)
                .collect(Collectors.groupingBy(d -> d.getInstitutionCode() + "/" + d.getInstitutionName(), Collectors.toList()));

        final Map<String, List<InstitutionChartYAxisDTO>> instCodeCollectFullTime = new HashMap<>();
        for (final String instCode : instCodeCollect.keySet()) {
            final List<InstitutionChartYAxisDTO> instInfo = instCodeCollect.get(instCode);
            final Map<Integer, InstitutionChartYAxisDTO> timeCollect =
                    instInfo.stream().collect(Collectors.toMap(InstitutionChartYAxisDTO::getYmd, d -> d));

            final List<InstitutionChartYAxisDTO> instTimelineList = new ArrayList<>();
            for (final Integer time : sortChartXAxis) {
                final InstitutionChartYAxisDTO info = timeCollect.get(time);
                if (info == null) continue;

                final InstitutionChartYAxisDTO institutionChartYAxisDTO = new InstitutionChartYAxisDTO();
                institutionChartYAxisDTO.setYmd(time);
                institutionChartYAxisDTO.setInstitutionCode(info.getInstitutionCode());
                institutionChartYAxisDTO.setInstitutionName(info.getInstitutionName());
                institutionChartYAxisDTO.setTotalCount(info.getTotalCount());
                instTimelineList.add(institutionChartYAxisDTO);
            }
            instCodeCollectFullTime.put(instCode, instTimelineList);
        }

        final InstitutionChartDTO institutionChartDTO = new InstitutionChartDTO();
        institutionChartDTO.setChartXAxis(sortChartXAxis);
        institutionChartDTO.setChartData(instCodeCollectFullTime);
        return institutionChartDTO;
    }
}

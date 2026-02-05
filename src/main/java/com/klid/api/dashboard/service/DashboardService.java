package com.klid.api.dashboard.service;

import com.klid.api.dashboard.dto.*;
import com.klid.api.dashboard.persistence.DashboardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardMapper dashboardMapper;

    public ThreatStatusDTO getThreatStatus(final String instCd) {
        return dashboardMapper.selectThreatNow(instCd);
    }

    public PeriodSettingDTO getPeriodSetting(final String instCd) {
        return dashboardMapper.selectPeriodNow(instCd);
    }

    public PeriodStatusDTO getPeriodStatus(final String sAuthMain, final String sInstCd) {
        return dashboardMapper.selectPeriodStatus(sAuthMain, sInstCd);
    }

    public TodayStatusDTO getTodayStatus(final String sAuthMain, final String sInstCd, final String atype) {
        return dashboardMapper.selectTodayStatus(sAuthMain, sInstCd, atype);
    }

    public YearStatusDTO getYearStatus(final String sAuthMain, final String sInstCd, final String atype) {
        return dashboardMapper.selectYearStatus(sAuthMain, sInstCd, atype);
    }

    public List<AccidentTypeRankDTO> getAccidentTypeTop5(
            final String sAuthMain,
            final String instCd,
            final String dateType,
            final String startDt,
            final String endDt) {
        return dashboardMapper.selectInciTypeList(sAuthMain, instCd, dateType, startDt, endDt);
    }

    public List<InstitutionRankDTO> getInstitutionTop5(
            final String sAuthMain,
            final String instCd,
            final String dateType,
            final String startDt,
            final String endDt,
            final String sortType,
            final String topInstView) {

        if ("SIDO".equals(sortType)) {
            return dashboardMapper.selectInciSidoList(sAuthMain, instCd, dateType, startDt, endDt, sortType, topInstView);
        } else {
            return dashboardMapper.selectInciLocalList(sAuthMain, instCd, dateType, startDt, endDt, sortType, topInstView);
        }
    }
}

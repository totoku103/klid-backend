package com.klid.api.dashboard.persistence;

import com.klid.api.dashboard.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DashboardMapper {

    ThreatStatusDTO selectThreatNow(@Param("instCd") String instCd);

    PeriodSettingDTO selectPeriodNow(@Param("instCd") String instCd);

    PeriodStatusDTO selectPeriodStatus(
            @Param("sAuthMain") String sAuthMain,
            @Param("sInstCd") String sInstCd
    );

    TodayStatusDTO selectTodayStatus(
            @Param("sAuthMain") String sAuthMain,
            @Param("sInstCd") String sInstCd,
            @Param("atype") String atype
    );

    YearStatusDTO selectYearStatus(
            @Param("sAuthMain") String sAuthMain,
            @Param("sInstCd") String sInstCd,
            @Param("atype") String atype
    );

    List<AccidentTypeRankDTO> selectInciTypeList(
            @Param("sAuthMain") String sAuthMain,
            @Param("instCd") String instCd,
            @Param("dateType") String dateType,
            @Param("startDt") String startDt,
            @Param("endDt") String endDt
    );

    List<InstitutionRankDTO> selectInciLocalList(
            @Param("sAuthMain") String sAuthMain,
            @Param("instCd") String instCd,
            @Param("dateType") String dateType,
            @Param("startDt") String startDt,
            @Param("endDt") String endDt,
            @Param("sortType") String sortType,
            @Param("topInstView") String topInstView
    );

    List<InstitutionRankDTO> selectInciSidoList(
            @Param("sAuthMain") String sAuthMain,
            @Param("instCd") String instCd,
            @Param("dateType") String dateType,
            @Param("startDt") String startDt,
            @Param("endDt") String endDt,
            @Param("sortType") String sortType,
            @Param("topInstView") String topInstView
    );
}

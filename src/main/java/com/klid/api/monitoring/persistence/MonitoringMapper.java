package com.klid.api.monitoring.persistence;

import com.klid.api.monitoring.dto.MonitoringDetailDTO;
import com.klid.api.monitoring.dto.MonitoringStatsDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoringMapper {

    MonitoringStatsDTO selectMainForgeryCnt(
            @Param("sInstCd") String sInstCd,
            @Param("sAuthMain") String sAuthMain
    );

    List<MonitoringDetailDTO> selectMainForgeryHm(
            @Param("sInstCd") String sInstCd,
            @Param("time1") String time1,
            @Param("time2") String time2
    );
}

package com.klid.api.monitoring.service;

import com.klid.api.monitoring.dto.MonitoringDetailDTO;
import com.klid.api.monitoring.dto.MonitoringStatsDTO;
import com.klid.api.monitoring.persistence.MonitoringMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitoringService {

    private final MonitoringMapper monitoringMapper;

    public MonitoringStatsDTO getMonitoringStats(final String sInstCd, final String sAuthMain) {
        return monitoringMapper.selectMainForgeryCnt(sInstCd, sAuthMain);
    }

    public List<MonitoringDetailDTO> getMonitoringDetail(
            final String sInstCd,
            final String time1,
            final String time2) {
        return monitoringMapper.selectMainForgeryHm(sInstCd, time1, time2);
    }
}

package com.klid.api.system.config.service;

import com.klid.api.system.config.dto.DashboardConfigRequest;
import com.klid.api.system.config.dto.DashboardConfigResponse;
import com.klid.api.system.config.persistence.DashboardConfigMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 행안부 대시보드 설정 관리 서비스
 */
@Service
@RequiredArgsConstructor
public class DashboardConfigService {

    private final DashboardConfigMapper dashboardConfigMapper;

    /**
     * 행안부 대시보드 설정 목록 조회
     */
    public List<DashboardConfigResponse> getDashConfigList(final Map<String, Object> params) {
        return dashboardConfigMapper.selectDashConfigList(params);
    }

    /**
     * 행안부 대시보드 설정 등록
     */
    @Transactional
    public void addDashConfig(final DashboardConfigRequest request) {
        dashboardConfigMapper.insertDashConfig(request);
    }

    /**
     * 행안부 대시보드 설정 수정
     */
    @Transactional
    public void editDashConfig(final String configId, final DashboardConfigRequest request) {
        request.setConfigId(configId);
        dashboardConfigMapper.updateDashConfig(request);
    }
}

package com.klid.api.system.config.persistence;

import com.klid.api.system.config.dto.DashboardConfigRequest;
import com.klid.api.system.config.dto.DashboardConfigResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 행안부 대시보드 설정 Mapper
 */
@Mapper
public interface DashboardConfigMapper {

    List<DashboardConfigResponse> selectDashConfigList(Map<String, Object> params);

    void insertDashConfig(DashboardConfigRequest request);

    void updateDashConfig(DashboardConfigRequest request);
}

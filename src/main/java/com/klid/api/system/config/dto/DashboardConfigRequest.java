package com.klid.api.system.config.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 행안부 대시보드 설정 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class DashboardConfigRequest {
    private String configId;
    private String configName;
    private String configValue;
    private String configDesc;
    private String useYn;
    private Integer sortOrder;
}

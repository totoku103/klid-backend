package com.klid.webapp.main.mois.dashConfig.persistence;

import com.klid.webapp.main.mois.dashConfig.dto.DashConfigDto;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("dashConfigMapper")
public interface DashConfigMapper {
    /** 행안부 리스트 받아오기 */
    DashConfigDto getDashConfigList(Map<String, Object> paramMap);

    /** 행안부 대시보드 등록 */
	int addDashConfig(Map<String, Object> paramMap);

    /** 행안부 대시보드 수정 */
    int editDashConfig(Map<String, Object> paramMap);
}

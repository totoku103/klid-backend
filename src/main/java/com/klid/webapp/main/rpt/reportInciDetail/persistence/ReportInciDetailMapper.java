package com.klid.webapp.main.rpt.reportInciDetail.persistence;

import com.klid.webapp.main.rpt.reportDaily.dto.ReportDailyDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportInciDetailMapper {

	/**일일 실적 사고처리 현황  누계 조회*/
	List<ReportDailyDto> selectInciDetail(Map<String, Object> paramMap);
}

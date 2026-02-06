package com.klid.webapp.main.rpt.reportInciPrcsStat.persistence;

import com.klid.webapp.main.rpt.reportInciPrcsStat.dto.ReportInciPrcsStatDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("reportInciPrcsStatMapper")
public interface ReportInciPrcsStatMapper {

	/** 사고유형 그리드 조회*/
	List<ReportInciPrcsStatDto> selectInciPrcsStat(Map<String, Object> paramMap);

}

package com.klid.webapp.main.rpt.reportInciPrty.persistence;

import com.klid.webapp.main.rpt.reportInciPrty.dto.ReportInciPrtyDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportInciPrtyMapper {

	/** 사고유형 그리드 조회*/
	List<ReportInciPrtyDto> selectInciPrtyList(Map<String, Object> paramMap);

}

package com.klid.webapp.main.rpt.reportInciType.persistence;

import com.klid.webapp.main.rpt.reportInciType.dto.ReportInciTypeDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("reportInciTypeMapper")
public interface ReportInciTypeMapper {

	/** 사고유형 그리드 조회*/
	List<ReportInciTypeDto> selectInciTypeList(Map<String, Object> paramMap);

}

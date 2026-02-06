package com.klid.webapp.main.rpt.reportInciAttNatn.persistence;

import com.klid.webapp.main.rpt.reportInciAttNatn.dto.ReportInciAttNatnDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("reportInciAttNatnMapper")
public interface ReportInciAttNatnMapper {

	/** 시도 그리드 조회*/
	List<ReportInciAttNatnDto> selectAttNatnList(Map<String, Object> paramMap);

}

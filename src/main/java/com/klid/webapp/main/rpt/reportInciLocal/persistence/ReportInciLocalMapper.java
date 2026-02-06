package com.klid.webapp.main.rpt.reportInciLocal.persistence;

import com.klid.webapp.main.rpt.reportInciLocal.dto.ReportInciLocalDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("reportInciLocalMapper")
public interface ReportInciLocalMapper {

	/** 시도 그리드 조회*/
	List<ReportInciLocalDto> selectInciLocalList(Map<String, Object> paramMap);

	List<ReportInciLocalDto> selectInciSidoList(Map<String, Object> paramMap);

}

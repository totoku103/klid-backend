package com.klid.webapp.main.rpt.reportDailyInciState.persistence;

import com.klid.webapp.main.rpt.reportDailyState.dto.ReportDailyStateDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("reportDailyInciStateMapper")
public interface ReportDailyInciStateMapper {

	List<ReportDailyStateDto> selectReportDayInciProc(Map<String, Object> paramMap);

	List<ReportDailyStateDto> selectReportSumInci(Map<String, Object> paramMap);
}

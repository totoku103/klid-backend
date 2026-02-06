package com.klid.webapp.main.rpt.reportDailyState.persistence;

import com.klid.webapp.main.rpt.reportDaily.dto.ReportDailyDto;
import com.klid.webapp.main.rpt.reportDailyState.dto.ReportDailyStateDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("reportDailyStateMapper")
public interface ReportDailyStateMapper {

	/**일일 실적 사고처리 현황  누계 조회*/
	List<ReportDailyStateDto> getRotationList(Map<String, Object> paramMap);

	List<ReportDailyDto> selectReportDayStat(Map<String, Object> paramMap);

	List<ReportDailyDto> selectReportSumStat(Map<String, Object> paramMap);

	List<ReportDailyDto> selectReportDayType(Map<String, Object> paramMap);

	List<ReportDailyStateDto> selectReportDayTms(Map<String, Object> paramMap);

	List<ReportDailyDto>  selectReportSum(Map<String, Object> paramMap);

	List<ReportDailyDto> selectReportTypeSum(Map<String, Object> paramMap);
	List<ReportDailyDto> selectReportNcsc(Map<String, Object> paramMap);

}

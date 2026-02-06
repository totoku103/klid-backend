package com.klid.webapp.main.rpt.reportWeeklyState.persistence;

import com.klid.webapp.main.rpt.reportDaily.dto.ReportDailyDto;
import com.klid.webapp.main.rpt.reportWeeklyState.dto.ReportWeeklyStateDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportWeeklyStateMapper {

	List<ReportWeeklyStateDto> getRotationList(Map<String, Object> paramMap);

	List<ReportDailyDto> selectReportWeekStat(Map<String, Object> paramMap);

	List<ReportDailyDto> selectReportTypeSum(Map<String, Object> paramMap);

	List<ReportWeeklyStateDto> selectReportYearSumStat(Map<String, Object> paramMap);

	List<ReportDailyDto> selectReportWeekType(Map<String, Object> paramMap);

	List<ReportWeeklyStateDto> getDetectionList(Map<String, Object> paramMap);

	List<ReportDailyDto> selectReportWeekSum(Map<String, Object> paramMap);
}

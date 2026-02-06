package com.klid.webapp.main.rpt.reportSecurityResult.persistence;

import com.klid.webapp.main.rpt.reportSecurityResult.dto.ReportResultListDto;
import com.klid.webapp.main.rpt.reportSecurityResult.dto.ReportResultTotalDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportSecurityResultMapper {

	List<ReportResultTotalDto> selectResultTotal(Map<String, Object> paramMap);

	List<ReportResultTotalDto> selectResultList(Map<String, Object> paramMap);

	List<ReportResultTotalDto> selectResultExceptlist(Map<String, Object> paramMap);

	List<ReportResultListDto> selectSecurityTitle(Map<String, Object> paramMap);

}

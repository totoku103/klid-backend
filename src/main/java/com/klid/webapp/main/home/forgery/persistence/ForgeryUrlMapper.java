package com.klid.webapp.main.home.forgery.persistence;

import com.klid.webapp.main.env.instIPMgmt.dto.InstIPMgmtDto;
import com.klid.webapp.main.home.forgery.dto.ForgeryUrlDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("forgeryUrlMapper")
public interface ForgeryUrlMapper {
	public List<InstIPMgmtDto> selectForgeryUrl(Map<String, Object> paramMap);

	public List<InstIPMgmtDto> selectForgeryUrlHist(Map<String, Object> paramMap);

	//메인화면 홈페이지 모니터링
	public List<ForgeryUrlDto> selectMainForgeryHm(Map<String, Object> paramMap);
	
	//메인화면 홈페이지 모니터링 수치 통계
	public ForgeryUrlDto selectMainForgeryCnt(Map<String, Object> paramMap);

	ForgeryUrlDto getByInstNm(Map<String, Object> paramMap);

}

package com.klid.webapp.main.home.healthCheck.persistence;

import com.klid.webapp.main.home.healthCheck.dto.HealthCheckUrlDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HealthCheckUrlMapper {
	public List<HealthCheckUrlDto> selectHealthCheckUrl(Map<String, Object> paramMap);

	int addHealthCheckUrl(Map<String, Object> paramMap);

	void editHealthCheckUrl(Map<String, Object> paramMap);

	void editWatchOn(Map<String, Object> paramMap);

	void editWatchOff(Map<String, Object> paramMap);
	
	HealthCheckUrlDto selectDetailHealthCheckUrl(Map<String, Object> paramMap);

	void delHealthCheckUrl(Map<String, Object> paramMap);

	public List<HealthCheckUrlDto> selectHealthCheckHist(Map<String, Object> paramMap);

	public List<HealthCheckUrlDto> selectUrls(Map<String, Object> paramMap);

	public List<Map<String, Object>> selectHealthCheckStat(Map<String, Object> paramMap);

	public List<Integer> selectRelateInstCd(Map<String, Object> paramMap);

	void addHealthCheckMultiUrl(Map<String, Object> paramMap);

	public int selectLastSeq();

}

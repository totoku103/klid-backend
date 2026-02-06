package com.klid.webapp.common.policy.persistence;

import com.klid.webapp.common.dto.PolicyDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PolicyConfMapper {

	
	// 정책 조회
	List<PolicyDto> selectPolicyConfInfo(Map<String, Object> paramMap);
	
	//정책 저장
	void updatePolicyConfig(Map<String, Object> paramMap);

}
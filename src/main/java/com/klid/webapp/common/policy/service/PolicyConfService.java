package com.klid.webapp.common.policy.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface PolicyConfService {

	// 정책 조회
	ReturnData getPolicyConfInfo();

	// 정책 저장
	ReturnData savePolicyConf(Criterion criterion);

}

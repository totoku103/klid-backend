package com.klid.webapp.main.sms.persistence;

import com.klid.webapp.main.sms.dto.SmsInfoDto;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SmsMapper {
	void insertSmsHist(Map<String, Object> paramMap);

	SmsInfoDto getSmsInfo(Map<String, Object> paramMap);
}

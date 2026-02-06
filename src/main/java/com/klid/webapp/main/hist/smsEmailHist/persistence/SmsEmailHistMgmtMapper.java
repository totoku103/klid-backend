package com.klid.webapp.main.hist.smsEmailHist.persistence;

import com.klid.webapp.main.hist.smsEmailHist.dto.SmsEmailHistMgmtDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("smsEmailHistMgmtMapper")
public interface SmsEmailHistMgmtMapper {

    List<SmsEmailHistMgmtDto> selectSmsHist(Map<String, Object> paramMap);

    List<SmsEmailHistMgmtDto> selectEmailHist(Map<String, Object> paramMap);

}

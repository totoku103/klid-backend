package com.klid.webapp.webdash.center.persistence;

import com.klid.webapp.webdash.center.dto.WebDashCenterDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WebDashCenterMapper {

     List<WebDashCenterDto> selectAttNationTop5(Map<String, Object> paramMap);
     List<WebDashCenterDto> selectTypeChart(Map<String, Object> paramMap);
     List<WebDashCenterDto> selectEvtChart(Map<String, Object> paramMap);
     List<WebDashCenterDto> selectEvtAllChart(Map<String, Object> paramMap);
}

package com.klid.api.webdash.center.persistence;

import com.klid.api.webdash.center.dto.WebDashCenterDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("apiWebDashCenterMapper")
@Mapper
public interface WebDashCenterMapper {

    List<WebDashCenterDTO> selectAttNationTop5(Map<String, Object> paramMap);

    List<WebDashCenterDTO> selectTypeChart(Map<String, Object> paramMap);

    List<WebDashCenterDTO> selectEvtChart(Map<String, Object> paramMap);

    List<WebDashCenterDTO> selectEvtAllChart(Map<String, Object> paramMap);
}

package com.klid.api.webdash.mois.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("apiWebDashMoisMapper")
@Mapper
public interface WebDashMoisMapper {

    Object getThreatNow(Map<String, Object> paramMap);

    Object getHmHcUrlCenter(Map<String, Object> paramMap);

    Object getHmHcUrlRegion(Map<String, Object> paramMap);

    Object getForgeryRegion(Map<String, Object> paramMap);

    Object getRegionStatus(Map<String, Object> paramMap);

    List<String> getRegionStatusAuto(Map<String, Object> paramMap);

    Object getRegionStatusManual(Map<String, Object> paramMap);

    Object getDashConfigList(Map<String, Object> paramMap);

    Object getDashChartSum(Map<String, Object> paramMap);
}

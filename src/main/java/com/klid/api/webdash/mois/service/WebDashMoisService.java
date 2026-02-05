package com.klid.api.webdash.mois.service;

import com.klid.api.webdash.mois.persistence.WebDashMoisMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("apiWebDashMoisService")
@RequiredArgsConstructor
public class WebDashMoisService {

    private final WebDashMoisMapper mapper;

    public Object getThreatNow(final Map<String, Object> paramMap) {
        return mapper.getThreatNow(paramMap);
    }

    public Object getHmHcUrlCenter(final Map<String, Object> paramMap) {
        return mapper.getHmHcUrlCenter(paramMap);
    }

    public Object getHmHcUrlRegion(final Map<String, Object> paramMap) {
        return mapper.getHmHcUrlRegion(paramMap);
    }

    public Object getForgeryRegion(final Map<String, Object> paramMap) {
        return mapper.getForgeryRegion(paramMap);
    }

    public Object getRegionStatus(final Map<String, Object> paramMap) {
        return mapper.getRegionStatus(paramMap);
    }

    public Integer getRegionStatusAuto(final Map<String, Object> paramMap) {
        final List<String> dbList = mapper.getRegionStatusAuto(paramMap);
        int sum = 0;

        if (!CollectionUtils.isEmpty(dbList)) {
            for (final String sumJson : dbList) {
                final JSONObject jsonObj = new JSONObject(sumJson);
                final Iterator<String> keys = jsonObj.keys();
                while (keys.hasNext()) {
                    final String key = keys.next();
                    final int value = jsonObj.getInt(key);
                    sum = sum + value;
                }
            }
        }
        return sum;
    }

    public Object getRegionStatusManual(final Map<String, Object> paramMap) {
        return mapper.getRegionStatusManual(paramMap);
    }

    public Object getDashConfigList(final Map<String, Object> paramMap) {
        return mapper.getDashConfigList(paramMap);
    }

    public Object getDashChartSum(final Map<String, Object> paramMap) {
        return mapper.getDashChartSum(paramMap);
    }
}

package com.klid.api.webdash.admin.service;

import com.klid.api.webdash.admin.dto.*;
import com.klid.api.webdash.admin.persistence.AdminControlMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service("apiAdminControlService")
@RequiredArgsConstructor
public class AdminControlService {

    private final AdminControlMapper mapper;

    public IncidentDTO getIncidentStatus(final Map<String, Object> paramMap) {
        return mapper.selectIncidentStatus(paramMap);
    }

    public List<InciCntDTO> getInciCnt(final Map<String, Object> paramMap) {
        final List<String> dbList = mapper.selectInciCnt(paramMap);
        final List<InciCntDTO> inciList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(dbList)) {
            final Map<String, Integer> map = new HashMap<>();
            for (final String sumJson : dbList) {
                final JSONObject jsonObj = new JSONObject(sumJson);
                final Iterator<String> keys = jsonObj.keys();
                while (keys.hasNext()) {
                    final String key = keys.next();
                    final int value = jsonObj.getInt(key);
                    if (map.containsKey(key)) {
                        map.put(key, map.get(key) + value);
                    } else {
                        map.put(key, value);
                    }
                }
            }

            final Iterator<String> mapKeys = map.keySet().iterator();
            while (mapKeys.hasNext()) {
                final String mapKey = mapKeys.next();
                final InciCntDTO dto = new InciCntDTO(mapKey, map.get(mapKey));

                if (!dto.getName().equals("기타")) {
                    inciList.add(dto);
                }
            }
        }
        return inciList;
    }

    public List<TbzledgeCntDTO> getTbzledgeCnt(final Map<String, Object> paramMap) {
        return mapper.selectTbzledgeCnt(paramMap);
    }

    public List<InciCntDTO> getLocalInciCnt(final Map<String, Object> paramMap) {
        final List<String> dbList = mapper.selectLocalInciCnt(paramMap);
        final List<InciCntDTO> inciList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(dbList)) {
            final Map<String, Integer> map = new HashMap<>();
            for (final String sumJson : dbList) {
                final JSONObject jsonObj = new JSONObject(sumJson);
                final Iterator<String> keys = jsonObj.keys();
                while (keys.hasNext()) {
                    final String key = keys.next();
                    final int value = jsonObj.getInt(key);
                    if (map.containsKey(key)) {
                        map.put(key, map.get(key) + value);
                    } else {
                        map.put(key, value);
                    }
                }
            }

            if ("AUTH_MAIN_2".equals(paramMap.get("sAuthMain"))) {
                inciList.add(new InciCntDTO("서울", 0));
                inciList.add(new InciCntDTO("부산", 0));
                inciList.add(new InciCntDTO("대구", 0));
                inciList.add(new InciCntDTO("인천", 0));
                inciList.add(new InciCntDTO("광주", 0));
                inciList.add(new InciCntDTO("대전", 0));
                inciList.add(new InciCntDTO("울산", 0));
                inciList.add(new InciCntDTO("세종", 0));
                inciList.add(new InciCntDTO("경기", 0));
                inciList.add(new InciCntDTO("강원", 0));
                inciList.add(new InciCntDTO("충북", 0));
                inciList.add(new InciCntDTO("충남", 0));
                inciList.add(new InciCntDTO("전북", 0));
                inciList.add(new InciCntDTO("전남", 0));
                inciList.add(new InciCntDTO("경북", 0));
                inciList.add(new InciCntDTO("경남", 0));
                inciList.add(new InciCntDTO("제주", 0));

                final Iterator<String> mapKeys = map.keySet().iterator();
                while (mapKeys.hasNext()) {
                    final String mapKey = mapKeys.next();
                    for (int i = 0; i < inciList.size(); i++) {
                        if (inciList.get(i).getName().equals(mapKey)) {
                            inciList.set(i, new InciCntDTO(mapKey, map.get(mapKey)));
                        }
                    }
                }
            } else {
                final Iterator<String> mapKeys = map.keySet().iterator();
                while (mapKeys.hasNext()) {
                    final String mapKey = mapKeys.next();
                    final InciCntDTO dto = new InciCntDTO(mapKey, map.get(mapKey));
                    inciList.add(dto);
                }
            }
        }
        return inciList;
    }

    public List<LocalStatusDTO> getLocalStatus(final Map<String, Object> paramMap) {
        return mapper.selectLocalStatus(paramMap);
    }

    public UrlStatusDTO getUrlStatus(final Map<String, Object> paramMap) {
        return mapper.selectUrlStatus(paramMap);
    }

    public List<SysErrorDTO> getSysErrorStatus(final Map<String, Object> paramMap) {
        final Object hostNm = paramMap.get("hostNm");
        if (hostNm == null || hostNm.toString().isBlank()) {
            throw new IllegalArgumentException("hostNm 파라미터는 필수입니다.");
        }
        return mapper.selectSysErrorStatus(paramMap);
    }

    public List<String> getInciTypeCnt(final Map<String, Object> paramMap) {
        return mapper.selectInciTypeCnt(paramMap);
    }
}

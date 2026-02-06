package com.klid.api.webdash.sido.persistence;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface WebDashSidoMapper {

    Object getNoticeList(Map<String, Object> paramMap);

    Object getSecuList(Map<String, Object> paramMap);

    Object getRegionStatusManual(Map<String, Object> paramMap);

    Object getForgeryCheck(Map<String, Object> paramMap);

    Object getHcCheck(Map<String, Object> paramMap);

    Object getProcess(Map<String, Object> paramMap);

    Object getSidoList(Map<String, Object> paramMap);
}

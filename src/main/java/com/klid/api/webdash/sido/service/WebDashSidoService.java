package com.klid.api.webdash.sido.service;

import com.klid.api.webdash.sido.persistence.WebDashSidoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class WebDashSidoService {

    private final WebDashSidoMapper mapper;

    public Object getNoticeList(final Map<String, Object> paramMap) {
        return mapper.getNoticeList(paramMap);
    }

    public Object getSecuList(final Map<String, Object> paramMap) {
        return mapper.getSecuList(paramMap);
    }

    public Object getRegionStatusManual(final Map<String, Object> paramMap) {
        return mapper.getRegionStatusManual(paramMap);
    }

    public Object getForgeryCheck(final Map<String, Object> paramMap) {
        return mapper.getForgeryCheck(paramMap);
    }

    public Object getHcCheck(final Map<String, Object> paramMap) {
        return mapper.getHcCheck(paramMap);
    }

    public Object getProcess(final Map<String, Object> paramMap) {
        return mapper.getProcess(paramMap);
    }

    public Object getSidoList(final Map<String, Object> paramMap) {
        return mapper.getSidoList(paramMap);
    }
}

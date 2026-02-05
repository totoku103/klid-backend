package com.klid.api.logs.connect.service;

import com.klid.api.logs.connect.dto.UserConnectLogPeriodResDTO;
import com.klid.api.logs.connect.persistence.UserConnectLogPeriodMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserConnectLogPeriodService {

    private final UserConnectLogPeriodMapper userConnectLogPeriodMapper;

    public List<UserConnectLogPeriodResDTO> getData(final String systemType, final String startYmd, final String endYmd) {
        log.info("systemType: {}, startYmd: {}, endYmd: {}", systemType, startYmd, endYmd);
        return userConnectLogPeriodMapper.selectUserConnectLogPeriodData(systemType, startYmd, endYmd);
    }
}

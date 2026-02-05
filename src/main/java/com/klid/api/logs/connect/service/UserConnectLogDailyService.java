package com.klid.api.logs.connect.service;

import com.klid.api.logs.connect.dto.UserConnectLogDailyResDTO;
import com.klid.api.logs.connect.persistence.UserConnectLogDailyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserConnectLogDailyService {

    private final UserConnectLogDailyMapper userConnectLogDailyMapper;

    public List<UserConnectLogDailyResDTO> getData(final String yyyyMMdd, final String systemType) {
        log.info("yyyyMMdd: {}, systemType: {}", yyyyMMdd, systemType);
        return userConnectLogDailyMapper.selectUserConnectLogDailyData(yyyyMMdd, systemType);
    }
}

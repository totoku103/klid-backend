package com.klid.api.logs.action.service;

import com.klid.api.logs.action.dto.UserActionLogDailyResDTO;
import com.klid.api.logs.action.persistence.UserActionLogDailyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserActionLogDailyService {

    private final UserActionLogDailyMapper userActionLogDailyMapper;

    public List<UserActionLogDailyResDTO> getData(final String yyyyMMdd, final String systemType) {
        log.info("yyyyMMdd: {}, systemType: {}", yyyyMMdd, systemType);
        return userActionLogDailyMapper.selectUserActionLogDailyData(yyyyMMdd, systemType);
    }
}

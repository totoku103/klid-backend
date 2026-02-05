package com.klid.api.logs.action.service;

import com.klid.api.logs.action.dto.UserActionLogPeriodResDTO;
import com.klid.api.logs.action.persistence.UserActionLogPeriodMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserActionLogPeriodService {

    private final UserActionLogPeriodMapper userActionLogPeriodMapper;

    public List<UserActionLogPeriodResDTO> getData(final String systemType, final String startYmd, final String endYmd) {
        log.info("systemType: {}, startYmd: {}, endYmd: {}", systemType, startYmd, endYmd);
        return userActionLogPeriodMapper.selectUserActionLogPeriodData(systemType, startYmd, endYmd);
    }
}

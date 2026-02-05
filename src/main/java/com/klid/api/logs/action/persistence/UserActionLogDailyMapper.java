package com.klid.api.logs.action.persistence;

import com.klid.api.logs.action.dto.UserActionLogDailyResDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActionLogDailyMapper {

    List<UserActionLogDailyResDTO> selectUserActionLogDailyData(
            @Param("yyyyMMdd") String yyyyMMdd,
            @Param("systemType") String systemType);
}

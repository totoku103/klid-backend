package com.klid.api.logs.connect.persistence;

import com.klid.api.logs.connect.dto.UserConnectLogDailyResDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserConnectLogDailyMapper {

    List<UserConnectLogDailyResDTO> selectUserConnectLogDailyData(
            @Param("yyyyMMdd") String yyyyMMdd,
            @Param("systemType") String systemType);
}

package com.klid.api.logs.connect.persistence;

import com.klid.api.logs.connect.dto.UserConnectLogPeriodResDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserConnectLogPeriodMapper {

    List<UserConnectLogPeriodResDTO> selectUserConnectLogPeriodData(
            @Param("systemType") String systemType,
            @Param("startYmd") String startYmd,
            @Param("endYmd") String endYmd);
}

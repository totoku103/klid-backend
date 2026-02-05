package com.klid.api.logs.action.persistence;

import com.klid.api.logs.action.dto.UserActionLogPeriodResDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActionLogPeriodMapper {

    List<UserActionLogPeriodResDTO> selectUserActionLogPeriodData(
            @Param("systemType") String systemType,
            @Param("startYmd") String startYmd,
            @Param("endYmd") String endYmd);
}

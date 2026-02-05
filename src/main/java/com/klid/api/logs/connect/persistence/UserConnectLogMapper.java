package com.klid.api.logs.connect.persistence;

import com.klid.api.logs.connect.dto.SummaryResDTO;
import com.klid.api.logs.connect.dto.UserConnectGridReqDTO;
import com.klid.api.logs.connect.dto.UserConnectGridResDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserConnectLogMapper {
    SummaryResDTO selectCtrsLoginCntPrevDay();
    SummaryResDTO selectCtrsLoginCntPrevWeek();
    SummaryResDTO selectCtrsLoginCntPrevMonth();

    SummaryResDTO selectOtherLoginCntPrevDay();
    SummaryResDTO selectOtherLoginCntPrevWeek();
    SummaryResDTO selectOtherLoginCntPrevMonth();

    List<UserConnectGridResDTO> selectCtrsUserConnectGridList(@Param("dto") UserConnectGridReqDTO dto);
    List<UserConnectGridResDTO> selectOtherUserConnectGridList(@Param("dto") UserConnectGridReqDTO dto);
}

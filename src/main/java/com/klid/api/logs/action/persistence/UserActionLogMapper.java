package com.klid.api.logs.action.persistence;

import com.klid.api.logs.action.dto.SummaryResDTO;
import com.klid.api.logs.action.dto.UserActionGridReqDTO;
import com.klid.api.logs.action.dto.UserActionGridResDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActionLogMapper {
    SummaryResDTO selectCtrsActionCntPrevDay();
    SummaryResDTO selectCtrsActionCntPrevWeek();
    SummaryResDTO selectCtrsActionCntPrevMonth();

    SummaryResDTO selectOtherActionCntPrevDay();
    SummaryResDTO selectOtherActionCntPrevWeek();
    SummaryResDTO selectOtherActionCntPrevMonth();

    List<UserActionGridResDTO> selectCtrsUserActionGridList(@Param("dto") UserActionGridReqDTO dto);
    List<UserActionGridResDTO> selectOtherUserActionGridList(@Param("dto") UserActionGridReqDTO dto);
}

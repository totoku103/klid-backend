package com.klid.api.logs.action.service;

import com.klid.api.logs.action.dto.SummaryCounterResDTO;
import com.klid.api.logs.action.dto.SummaryResDTO;
import com.klid.api.logs.action.dto.UserActionGridReqDTO;
import com.klid.api.logs.action.dto.UserActionGridResDTO;
import com.klid.api.logs.action.persistence.UserActionLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserActionLogService {

    private final UserActionLogMapper userActionLogMapper;

    public SummaryCounterResDTO getSummaryInfo() {
        final SummaryResDTO ctrsPrevDay = userActionLogMapper.selectCtrsActionCntPrevDay();
        final SummaryResDTO ctrsPrevWeek = userActionLogMapper.selectCtrsActionCntPrevWeek();
        final SummaryResDTO ctrsPrevMonth = userActionLogMapper.selectCtrsActionCntPrevMonth();

        final SummaryResDTO otherPrevDay = userActionLogMapper.selectOtherActionCntPrevDay();
        final SummaryResDTO otherPrevWeek = userActionLogMapper.selectOtherActionCntPrevWeek();
        final SummaryResDTO otherPrevMonth = userActionLogMapper.selectOtherActionCntPrevMonth();

        final SummaryCounterResDTO summaryCounterResDTO = new SummaryCounterResDTO();
        summaryCounterResDTO.setCtrsPrevDay(ctrsPrevDay);
        summaryCounterResDTO.setCtrsPrevWeek(ctrsPrevWeek);
        summaryCounterResDTO.setCtrsPrevMonth(ctrsPrevMonth);
        summaryCounterResDTO.setOtherPrevDay(otherPrevDay);
        summaryCounterResDTO.setOtherPrevWeek(otherPrevWeek);
        summaryCounterResDTO.setOtherPrevMonth(otherPrevMonth);

        return summaryCounterResDTO;
    }

    public List<UserActionGridResDTO> getGridList(final UserActionGridReqDTO userActionGridReqDTO) {
        List<UserActionGridResDTO> ctrsList = null;
        if ("CTRS".equalsIgnoreCase(userActionGridReqDTO.getSystemType())
                || "".equalsIgnoreCase(userActionGridReqDTO.getSystemType())) {
            ctrsList = userActionLogMapper.selectCtrsUserActionGridList(userActionGridReqDTO);
        }

        List<UserActionGridResDTO> otherList = null;
        if ("VMS".equalsIgnoreCase(userActionGridReqDTO.getSystemType())
                || "CTSS".equalsIgnoreCase(userActionGridReqDTO.getSystemType())
                || "".equalsIgnoreCase(userActionGridReqDTO.getSystemType())) {
            otherList = userActionLogMapper.selectOtherUserActionGridList(userActionGridReqDTO);
        }

        final ArrayList<UserActionGridResDTO> result = new ArrayList<>();
        if (ctrsList != null) {
            result.addAll(ctrsList);
        }
        if (otherList != null) {
            result.addAll(otherList);
        }

        result.sort((a, b) -> {
            if (Objects.equals(a.getRegDate(), b.getRegDate())) {
                return 0;
            } else {
                return a.getRegDate() > b.getRegDate() ? -1 : 1;
            }
        });

        return result;
    }
}

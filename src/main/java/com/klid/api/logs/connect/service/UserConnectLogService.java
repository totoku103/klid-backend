package com.klid.api.logs.connect.service;

import com.klid.api.logs.connect.dto.SummaryCounterResDTO;
import com.klid.api.logs.connect.dto.SummaryResDTO;
import com.klid.api.logs.connect.dto.UserConnectGridReqDTO;
import com.klid.api.logs.connect.dto.UserConnectGridResDTO;
import com.klid.api.logs.connect.persistence.UserConnectLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserConnectLogService {

    private final UserConnectLogMapper userConnectLogMapper;

    public SummaryCounterResDTO getSummaryInfo() {
        final SummaryResDTO ctrsPrevDay = userConnectLogMapper.selectCtrsLoginCntPrevDay();
        final SummaryResDTO ctrsPrevWeek = userConnectLogMapper.selectCtrsLoginCntPrevWeek();
        final SummaryResDTO ctrsPrevMonth = userConnectLogMapper.selectCtrsLoginCntPrevMonth();

        final SummaryResDTO otherPrevDay = userConnectLogMapper.selectOtherLoginCntPrevDay();
        final SummaryResDTO otherPrevWeek = userConnectLogMapper.selectOtherLoginCntPrevWeek();
        final SummaryResDTO otherPrevMonth = userConnectLogMapper.selectOtherLoginCntPrevMonth();

        final SummaryCounterResDTO summaryCounterResDTO = new SummaryCounterResDTO();
        summaryCounterResDTO.setCtrsPrevDay(ctrsPrevDay);
        summaryCounterResDTO.setCtrsPrevWeek(ctrsPrevWeek);
        summaryCounterResDTO.setCtrsPrevMonth(ctrsPrevMonth);
        summaryCounterResDTO.setOtherPrevDay(otherPrevDay);
        summaryCounterResDTO.setOtherPrevWeek(otherPrevWeek);
        summaryCounterResDTO.setOtherPrevMonth(otherPrevMonth);

        return summaryCounterResDTO;
    }

    public List<UserConnectGridResDTO> getGridList(final UserConnectGridReqDTO userConnectGridReqDTO) {
        List<UserConnectGridResDTO> ctrsList = null;
        if ("CTRS".equalsIgnoreCase(userConnectGridReqDTO.getSystem())
                || "".equalsIgnoreCase(userConnectGridReqDTO.getSystem())) {
            ctrsList = userConnectLogMapper.selectCtrsUserConnectGridList(userConnectGridReqDTO);
        }

        List<UserConnectGridResDTO> otherList = null;
        if ("VMS".equalsIgnoreCase(userConnectGridReqDTO.getSystem())
                || "CTSS".equalsIgnoreCase(userConnectGridReqDTO.getSystem())
                || "".equalsIgnoreCase(userConnectGridReqDTO.getSystem())) {
            otherList = userConnectLogMapper.selectOtherUserConnectGridList(userConnectGridReqDTO);
        }

        final ArrayList<UserConnectGridResDTO> result = new ArrayList<>();
        if (ctrsList != null) {
            result.addAll(ctrsList);
        }
        if (otherList != null) {
            result.addAll(otherList);
        }

        result.sort((a, b) -> {
            if (Objects.equals(a.getYmdhms(), b.getYmdhms())) {
                return 0;
            } else {
                return a.getYmdhms() > b.getYmdhms() ? -1 : 1;
            }
        });

        return result;
    }
}

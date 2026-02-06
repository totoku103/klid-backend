package com.klid.webapp.main.hist.userActHist.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.MsgService;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.hist.userActHist.persistence.UserActHistMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("userActHistService")
public class UserActHistServiceImpl extends MsgService implements UserActHistService {

    @Resource(name = "userActHistMapper")
    private UserActHistMapper mapper;

	@Override
	public ReturnData getUserActHist(Criterion criterion){
		return new ReturnData(mapper.selectUserActHist(criterion.getCondition()));
	}
}

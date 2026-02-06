package com.klid.webapp.main.hist.userInoutHist.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.MsgService;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.hist.userInoutHist.persistence.UserInoutHistMgmtMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service("userInoutHistMgmtService")
public class UserInoutHistMgmtServiceImpl extends MsgService implements UserInoutHistMgmtService {

    private final UserInoutHistMgmtMapper mapper;

    public UserInoutHistMgmtServiceImpl(MessageSource messageSource, UserInoutHistMgmtMapper mapper) {
        super(messageSource);
        this.mapper = mapper;
    }

	@Override
	public ReturnData getLogUserList(Criterion criterion){
		return new ReturnData(mapper.selectLogUserList(criterion.getCondition()));
	}

	@Override
	public ReturnData getUserInoutHist(Criterion criterion){
		return new ReturnData(mapper.selectUserInoutHist(criterion.getCondition()));
	}
}

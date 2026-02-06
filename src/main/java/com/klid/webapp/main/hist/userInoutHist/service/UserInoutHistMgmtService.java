package com.klid.webapp.main.hist.userInoutHist.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface UserInoutHistMgmtService {

    public ReturnData getLogUserList(Criterion criterion);

    public ReturnData getUserInoutHist(Criterion criterion);

}

package com.klid.webapp.main.sys.custUserMgmt.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface CustUserMgmtService {

    ReturnData getSmsUserList(Criterion criterion);

    ReturnData getSmsOfUserList(Criterion criterion);

    ReturnData selectUserPhone(Criterion criterion);

    ReturnData getSmsGroup(Criterion criterion);

    ReturnData addSmsGroup(Criterion criterion);

    ReturnData editSmsGroup(Criterion criterion);

    ReturnData delSmsGroup(Criterion criterion);

}


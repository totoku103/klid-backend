package com.klid.webapp.main.env.userConf.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface UserConfService {
	//사용자 연락처 목록
	public ReturnData getUserAddrList(Criterion criterion);

	//사용자 목록
	public ReturnData getUserConfList(Criterion criterion) ;
	
	ReturnData addUser(Criterion criterion) ;

	ReturnData editUser(Criterion criterion) ;

	ReturnData editSelfUser(Criterion criterion);

	ReturnData editUserPassword(Criterion criterion);
	
	ReturnData getUserIdDuplicateCnt(Criterion criterion) ;

	ReturnData getDetailUser(Criterion criterion);

	public ReturnData getAuthList(Criterion criterion) ;

	ReturnData userPassReset(Criterion criterion) ;

	ReturnData userLockReset(Criterion criterion);

	ReturnData passwordCheck(Criterion criterion);

	ReturnData updateLoginFailCnt(Criterion criterion) ;

	ReturnData updateLoginFailCntReset(Criterion criterion) ;

	ReturnData updateLoginLock(Criterion criterion) ;

	ReturnData delUser(Criterion criterion) ;

	ReturnData getPushUsers(Criterion criterion) ;

	int getAllUserPassReset(Criterion criterion) ;

	ReturnData checkMyId(Criterion criterion) ;

	ReturnData checkUserAuth(Criterion criterion) ;
}

package com.klid.webapp.main.home.forgery.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface ForgeryUrlService {
	public ReturnData getForgeryUrl(Criterion criterion);

	public ReturnData getForgeryUrlHist(Criterion criterion);

	public ReturnData getMainForgeryHm(Criterion criterion);
	
	public ReturnData getMainForgeryCnt(Criterion criterion) ;

	public ReturnData getByInstNm(Criterion criterion) ;
}

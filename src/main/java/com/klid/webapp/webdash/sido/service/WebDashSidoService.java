package com.klid.webapp.webdash.sido.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface WebDashSidoService {

    ReturnData getNoticeList(Criterion criterion) ;

    ReturnData getSecuList(Criterion criterion) ;

    ReturnData getRegionStatusManual(Criterion criterion) ;

    ReturnData getForgeryCheck(Criterion criterion) ;

    ReturnData getHcCheck(Criterion criterion) ;

    ReturnData getProcess(Criterion criterion) ;

    ReturnData getSidoList(Criterion criterion) ;
}

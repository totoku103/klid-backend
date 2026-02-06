package com.klid.webapp.main.hist.smsEmailHist.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import jakarta.servlet.http.HttpServletResponse;

public interface SmsEmailHistMgmtService {

    public ReturnData getSmsHist(Criterion criterion);

    public ReturnData getEmailHist(Criterion criterion) ;

	public ReturnData export_sms(HttpServletResponse response, Criterion criterion);

	public ReturnData export_email(HttpServletResponse response, Criterion criterion);

}

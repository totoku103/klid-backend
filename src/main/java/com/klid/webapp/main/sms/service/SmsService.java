package com.klid.webapp.main.sms.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface SmsService {
    ReturnData addSmsMessage(Criterion criterion) ;
}

package com.klid.webapp.engineer.popup.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface PopupService {

	ReturnData addPage(Criterion criterion);

	ReturnData savePage(Criterion criterion);

	ReturnData delPage(Criterion criterion);

	ReturnData addPageGroup(Criterion criterion) throws Exception;
	
	ReturnData savePageGroup(Criterion criterion);
	
	ReturnData delPageGroup(Criterion criterion);
	
	ReturnData addMenu(Criterion criterion);
	
	ReturnData saveMenu(Criterion criterion);
	
	ReturnData delMenu(Criterion criterion);
	
}

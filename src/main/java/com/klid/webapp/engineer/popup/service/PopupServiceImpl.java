package com.klid.webapp.engineer.popup.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.MsgService;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.engineer.popup.persistence.PopupMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service("popupService")
public class PopupServiceImpl extends MsgService implements PopupService {

	private final PopupMapper mapper;

	public PopupServiceImpl(MessageSource messageSource, PopupMapper mapper) {
		super(messageSource);
		this.mapper = mapper;
	}

	@Override
	public ReturnData addPage(Criterion criterion){
		return new ReturnData(mapper.addPage(criterion.getCondition()));
	}

	@Override
	public ReturnData savePage(Criterion criterion) {
		ReturnData returnData = new ReturnData();
		mapper.savePage(criterion.getCondition());
		returnData.setResultData(getEditOkMessage());
		return returnData;
	}

	@Override
	public ReturnData delPage(Criterion criterion) {
		mapper.delPage(criterion.getCondition());
		return new ReturnData(criterion.getCondition());
	}

	
	@Override
	public ReturnData addPageGroup(Criterion criterion) throws Exception {
		return new ReturnData(mapper.addPageGroup(criterion.getCondition()));
	}

	@Override
	public ReturnData savePageGroup(Criterion criterion){
		ReturnData returnData = new ReturnData();
		mapper.savePageGroup(criterion.getCondition());
		returnData.setResultData(getEditOkMessage());
		return returnData;
	}

	@Override
	public ReturnData delPageGroup(Criterion criterion) {
		mapper.delPageGroup(criterion.getCondition());
		return new ReturnData(criterion.getCondition());
	}

	@Override
	public ReturnData addMenu(Criterion criterion){
		ReturnData returnData = new ReturnData();
		mapper.addMenu(criterion.getCondition());
		returnData.setResultData(getEditOkMessage());
		return returnData;
	}

	@Override
	public ReturnData saveMenu(Criterion criterion) {
		ReturnData returnData = new ReturnData();
		mapper.saveMenu(criterion.getCondition());
		returnData.setResultData(getEditOkMessage());
		return returnData;
	}

	@Override
	public ReturnData delMenu(Criterion criterion){
		mapper.delMenu(criterion.getCondition());
		return new ReturnData(criterion.getCondition());
	}
	

}

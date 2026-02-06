package com.klid.webapp.main.sec.shareBoard.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.sec.shareBoard.dto.ShareBoardDto;

import java.util.List;

public interface ShareBoardService {

	ReturnData getPostBoardList(Criterion criterion) throws Exception;

	ReturnData getBoardList(Criterion criterion);

	ReturnData getBoardContents(Criterion criterion);

	ReturnData addBoard(Criterion criterion);

	ReturnData editBoard(Criterion criterion);

	ReturnData delBoard(Criterion criterion) ;
	
	ReturnData getShareBoardSidoCnt(Criterion criterion);

	ReturnData checkAuth(Criterion criterion) ;

	List<ShareBoardDto> getBoardDetail(Criterion criterion);

}

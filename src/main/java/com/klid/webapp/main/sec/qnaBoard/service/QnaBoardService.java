package com.klid.webapp.main.sec.qnaBoard.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface QnaBoardService {

	ReturnData getPostBoardList(Criterion criterion) ;

	ReturnData getBoardList(Criterion criterion);

	ReturnData getBoardContents(Criterion criterion);

	ReturnData addBoard(Criterion criterion) ;

	ReturnData addComment(Criterion criterion);

	ReturnData editBoard(Criterion criterion);

	ReturnData delBoard(Criterion criterion) ;

	ReturnData getMainQnaList(Criterion criterion) ;

	ReturnData checkAuth(Criterion criterion) ;
}

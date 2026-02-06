package com.klid.webapp.main.sec.resourceBoard.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

public interface ResourceBoardService {

	ReturnData getPostBoardList(Criterion criterion) ;

	ReturnData getBoardList(Criterion criterion);

	ReturnData getBoardContents(Criterion criterion) ;

	ReturnData addBoard(Criterion criterion) ;

	ReturnData editBoard(Criterion criterion);

	ReturnData delBoard(Criterion criterion) ;

	ReturnData checkAuth(Criterion criterion) ;

	ReturnData getMoisBoardList(Criterion criterion);

	ReturnData getMoisBoardContents(Criterion criterion) ;

	ReturnData addMoisBoard(Criterion criterion) ;

	ReturnData editMoisBoard(Criterion criterion);

	ReturnData delMoisBoard(Criterion criterion) ;
}

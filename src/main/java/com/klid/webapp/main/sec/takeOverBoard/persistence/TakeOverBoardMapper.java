package com.klid.webapp.main.sec.takeOverBoard.persistence;

import com.klid.webapp.main.sec.takeOverBoard.dto.TakeOverBoardDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TakeOverBoardMapper {

	/** 인수인계 리스트 조회 */
	List<TakeOverBoardDto> selectBoardList(Map<String, Object> paramMap);

	/** 인수인계 정보 조회 */
	TakeOverBoardDto selectBoardInfo(Map<String, Object> paramMap);

	/** 인수인계 추가 */
	int insertBoard(Map<String, Object> paramMap);

	/** 인수인계 수정 */
	void updateBoard(Map<String, Object> paramMap);
	
	/** 인수인계 글 - 확인상태로 변경 */
	void insertBoardConfirm(Map<String, Object> paramMap);
	
	/** 인수인계 글 - 종결상태로 변경 */
	void updateBoard_finish(Map<String, Object> paramMap);
	
	/** 인수인계 답글 리스트 조회 */
	List<TakeOverBoardDto> selectAnsBoardList(Map<String, Object> paramMap);
	
	/** 인수인계 답글 추가 */
	int insertAnsBoard(Map<String, Object> paramMap);
	
}

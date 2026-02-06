package com.klid.webapp.main.controller.sec;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.sec.qnaBoard.service.QnaBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/sec/qnaBoard")
@RestController
@RequiredArgsConstructor
public class QnaBoardController {

	private final QnaBoardService service;
	
	/** 게시판 최근리스트 받아오기 */
	@RequestMapping(value = "getPostBoardList")
	public ReturnData getPostBoardList(@RequestParam Map<String, Object> reqMap) {
			return service.getPostBoardList(new Criterion(reqMap));
	}
	
	/** 타입으로 게시판 리스트 받아오기 */
	@RequestMapping(value = "getBoardList")
	public ReturnData getBoardList(@RequestParam Map<String, Object> reqMap) {
			return service.getBoardList(new Criterion(reqMap));
	}

	/** 게시판 번호로 컨텐츠 보기 */
	@RequestMapping(value = "getBoardContents")
	public ReturnData getBoardContentsByNo(@RequestParam Map<String, Object> reqMap) {
			return service.getBoardContents(new Criterion(reqMap));
	}

	/** 게시판 글쓰고 작성한 글 번호 받아오기 */
	@RequestMapping(value = "addBoard", method = RequestMethod.POST)
	public ReturnData addBoard(@RequestBody Map<String, Object> reqMap) {
			return service.addBoard(new Criterion(reqMap, false));
	}
	
	/** 코멘트 쓰고 번호 받아오기 */
	@RequestMapping(value = "addComment", method = RequestMethod.POST)
	public ReturnData addComment(@RequestBody Map<String, Object> reqMap) {
		return service.addComment(new Criterion(reqMap, false));
	}
	/** 게시판 번호로 게시판 글 수정하기 */
	@RequestMapping(value = "editBoard", method = RequestMethod.POST)
	public ReturnData editBoard(@RequestBody Map<String, Object> reqMap) {
		return service.editBoard(new Criterion(reqMap, false));
	}

	/** 게시판 번호로 게시판 글 삭제하기 */
	@RequestMapping(value = "delBoard")
	public ReturnData delBoard(@RequestParam Map<String, Object> reqMap) {
			return service.delBoard(new Criterion(reqMap));
	}

	/** 메인 문의/의견 리스트 조회 */
	@PostMapping(value = "getMainQnaList")
	public ReturnData getMainQnaList(@RequestParam Map<String, Object> reqMap) {
			return service.getMainQnaList(new Criterion(reqMap, false));
	}

	@RequestMapping(value = "checkAuth")
	public ReturnData checkId(@RequestParam Map<String, Object> reqMap) {
		return service.checkAuth(new Criterion(reqMap));
	}
}


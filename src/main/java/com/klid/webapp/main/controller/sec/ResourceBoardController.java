package com.klid.webapp.main.controller.sec;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ErrorInfo;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.sec.resourceBoard.service.ResourceBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/sec/resourceBoard")
@RestController
@RequiredArgsConstructor
public class ResourceBoardController {

	private final ResourceBoardService service;

	/** 게시판 리스트 받아오기 */
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

	/** 게시판 번호로 게시판 글 수정하기 */
	@RequestMapping(value = "editBoard", method = RequestMethod.POST)
	public ReturnData editBoard(@RequestBody Map<String, Object> reqMap) {
		return service.editBoard(new Criterion(reqMap, false));
	}

	/** 게시판 번호로 게시판 글 삭제하기 */
	@RequestMapping(value = "delBoard")
	public ReturnData delBoard(@RequestParam Map<String, Object> reqMap) {
		try {
			return service.delBoard(new Criterion(reqMap));
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnData(new ErrorInfo(e));
		}
	}

	@RequestMapping(value = "checkAuth")
	public ReturnData checkId(@RequestParam Map<String, Object> reqMap) {
		return service.checkAuth(new Criterion(reqMap));
	}

	//행안부 게시판 추가. 운영서버 소스 형상관리 및 패치 이슈로 자료실 java에 공유
	@RequestMapping(value = "getMoisBoardList")
	public ReturnData getMoisBoardList(@RequestParam Map<String, Object> reqMap) {
		return service.getMoisBoardList(new Criterion(reqMap));
	}

	/** 게시판 번호로 컨텐츠 보기 */
	@RequestMapping(value = "getMoisBoardContents")
	public ReturnData getMoisBoardContents(@RequestParam Map<String, Object> reqMap) {
			return service.getMoisBoardContents(new Criterion(reqMap));
	}

	@RequestMapping(value = "addMoisBoard", method = RequestMethod.POST)
	public ReturnData addMoisBoard(@RequestBody Map<String, Object> reqMap) {
		return service.addMoisBoard(new Criterion(reqMap, false));
	}

	@RequestMapping(value = "editMoisBoard", method = RequestMethod.POST)
	public ReturnData editMoisBoard(@RequestBody Map<String, Object> reqMap) {
		return service.editMoisBoard(new Criterion(reqMap, false));
	}

	@RequestMapping(value = "delMoisBoard")
	public ReturnData delMoisBoard(@RequestParam Map<String, Object> reqMap) {
		try {
			return service.delMoisBoard(new Criterion(reqMap));
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnData(new ErrorInfo(e));
		}
	}
}

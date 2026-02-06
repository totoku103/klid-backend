package com.klid.webapp.main.controller.sec;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.sec.takeOverBoard.service.TakeOverBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/sec/takeOverBoard")
@RestController
@RequiredArgsConstructor
public class TakeOverBoardController {

	private final TakeOverBoardService service;

	/** 게시판 리스트 받아오기 */
	@RequestMapping(value = "getBoardList")
	public ReturnData getBoardList(@RequestParam Map<String, Object> reqMap) {
		return service.getBoardList(new Criterion(reqMap));
	}

	/** 게시판 번호로 컨텐츠 보기 */
	@RequestMapping(value = "getBoardInfo")
	public ReturnData getBoardInfo(@RequestParam Map<String, Object> reqMap) {
		return service.getBoardInfo(new Criterion(reqMap));
	}

	/** 게시판 글 추가 */
	@RequestMapping(value = "addBoard", method = RequestMethod.POST)
	public ReturnData addBoard(@RequestBody Map<String, Object> reqMap) {
		return service.addBoard(new Criterion(reqMap, false));
	}

	/** 인수인계 수정 */
	@RequestMapping(value = "editBoard", method = RequestMethod.POST)
	public ReturnData editBoard(@RequestBody Map<String, Object> reqMap) {
		return service.editBoard(new Criterion(reqMap, false));
	}

	/** 인수인계 수정  - 확인상태로 변경 */
	@RequestMapping(value = "addBoardConfirm", method = RequestMethod.POST)
	public ReturnData addBoardConfirm(@RequestBody Map<String, Object> reqMap) {
		return service.addBoardConfirm(new Criterion(reqMap, false));
	}

	/** 인수인계 수정 - 종결상태로 변경 */
	@RequestMapping(value = "editBoard_finish", method = RequestMethod.POST)
	public ReturnData editBoard_finish(@RequestBody Map<String, Object> reqMap) {
			return service.editBoard_finish(new Criterion(reqMap, false));
	}
	
	/** 답글 리스트 조회 */
	@RequestMapping(value = "getAnsBoardList")
	public ReturnData getAnsBoardList(@RequestParam Map<String, Object> reqMap) {
		return service.getAnsBoardList(new Criterion(reqMap));
	}

	/** 답글 추가 */
	@RequestMapping(value = "addAnsBoard", method = RequestMethod.POST)
	public ReturnData addAnsBoard(@RequestBody Map<String, Object> reqMap) {
		return service.addAnsBoard(new Criterion(reqMap, false));
	}

}

package com.klid.webapp.main.controller.sec;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ErrorInfo;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.sec.resourceBoard.service.ResourceBoardService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/sec/resourceBoard")
@Controller
public class ResourceBoardController {

	@Resource(name = "resourceBoardService")
	private ResourceBoardService service;

	/** 게시판 리스트 받아오기 */
	@RequestMapping(value = "getBoardList")
	public @ResponseBody ReturnData getBoardList(@RequestParam Map<String, Object> reqMap) {
		return service.getBoardList(new Criterion(reqMap));
	}

	/** 게시판 번호로 컨텐츠 보기 */
	@RequestMapping(value = "getBoardContents")
	public @ResponseBody ReturnData getBoardContentsByNo(@RequestParam Map<String, Object> reqMap) {
			return service.getBoardContents(new Criterion(reqMap));
	}

	/** 게시판 글쓰고 작성한 글 번호 받아오기 */
	@RequestMapping(value = "addBoard", method = RequestMethod.POST)
	public @ResponseBody ReturnData addBoard(@RequestBody Map<String, Object> reqMap) {
		return service.addBoard(new Criterion(reqMap, false));
	}

	/** 게시판 번호로 게시판 글 수정하기 */
	@RequestMapping(value = "editBoard", method = RequestMethod.POST)
	public @ResponseBody ReturnData editBoard(@RequestBody Map<String, Object> reqMap) {
		return service.editBoard(new Criterion(reqMap, false));
	}

	/** 게시판 번호로 게시판 글 삭제하기 */
	@RequestMapping(value = "delBoard")
	public @ResponseBody ReturnData delBoard(@RequestParam Map<String, Object> reqMap) {
		try {
			return service.delBoard(new Criterion(reqMap));
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnData(new ErrorInfo(e));
		}
	}

	@RequestMapping(value = "checkAuth")
	public@ResponseBody ReturnData checkId(@RequestParam Map<String, Object> reqMap) {
		return service.checkAuth(new Criterion(reqMap));
	}

	//행안부 게시판 추가. 운영서버 소스 형상관리 및 패치 이슈로 자료실 java에 공유
	@RequestMapping(value = "getMoisBoardList")
	public @ResponseBody ReturnData getMoisBoardList(@RequestParam Map<String, Object> reqMap) {
		return service.getMoisBoardList(new Criterion(reqMap));
	}

	/** 게시판 번호로 컨텐츠 보기 */
	@RequestMapping(value = "getMoisBoardContents")
	public @ResponseBody ReturnData getMoisBoardContents(@RequestParam Map<String, Object> reqMap) {
			return service.getMoisBoardContents(new Criterion(reqMap));
	}

	@RequestMapping(value = "addMoisBoard", method = RequestMethod.POST)
	public @ResponseBody ReturnData addMoisBoard(@RequestBody Map<String, Object> reqMap) {
		return service.addMoisBoard(new Criterion(reqMap, false));
	}

	@RequestMapping(value = "editMoisBoard", method = RequestMethod.POST)
	public @ResponseBody ReturnData editMoisBoard(@RequestBody Map<String, Object> reqMap) {
		return service.editMoisBoard(new Criterion(reqMap, false));
	}

	@RequestMapping(value = "delMoisBoard")
	public @ResponseBody ReturnData delMoisBoard(@RequestParam Map<String, Object> reqMap) {
		try {
			return service.delMoisBoard(new Criterion(reqMap));
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnData(new ErrorInfo(e));
		}
	}
}

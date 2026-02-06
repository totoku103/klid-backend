package com.klid.webapp.main.controller.sec;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.sec.shareBoard.service.ShareBoardService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/sec/shareBoard")
@Controller
public class ShareBoardController {

	@Resource(name = "shareBoardService")
	private ShareBoardService service;

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
			return service.delBoard(new Criterion(reqMap));
	}
	
	@RequestMapping(value = "getShareBoardSidoCnt")
	public @ResponseBody ReturnData getShareBoardSidoCnt(@RequestParam Map<String, Object> reqMap) {
		return service.getShareBoardSidoCnt(new Criterion(reqMap));
	}

	@RequestMapping(value = "checkAuth")
	public@ResponseBody ReturnData checkId(@RequestParam Map<String, Object> reqMap) {
		return service.checkAuth(new Criterion(reqMap));
	}

}

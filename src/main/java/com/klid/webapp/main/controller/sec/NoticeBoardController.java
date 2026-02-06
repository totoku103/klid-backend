package com.klid.webapp.main.controller.sec;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.sec.noticeBoard.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/main/sec/noticeBoard")
@RestController
@RequiredArgsConstructor
public class NoticeBoardController {

	private final NoticeBoardService service;

	/** 게시판 최근리스트 받아오기 */
	@RequestMapping(value = "getPostBoardList")
	public ReturnData getPostBoardList(@RequestParam Map<String, Object> reqMap) {
			return service.getPostBoardList(new Criterion(reqMap));
	}

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
			return service.delBoard(new Criterion(reqMap));
	}
	
	/** 게시판 그룹타입 리스트 */
	@RequestMapping(value = "getBoardTypeList")
	public ReturnData getBoardTypeList(@RequestParam Map<String, Object> reqMap) {
		return service.getBoardTypeList(new Criterion(reqMap));
	}

	/** 설문 제출 */
	@RequestMapping(value = "addNoticeSurvey", method = RequestMethod.POST)
	public ReturnData addNoticeSurvey(@RequestBody Map<String, Object> reqMap) {
		return service.addNoticeSurvey(new Criterion(reqMap, false));
	}

	/** 설문 응시 여부 체크 */
	@RequestMapping(value = "getSurveyAnsweCnt")
	public ReturnData getSurveyAnsweCnt(@RequestParam Map<String, Object> reqMap) {
		return service.getSurveyAnsweCnt(new Criterion(reqMap));
	}

	/** 설문 차트 */
	@RequestMapping(value = "getSurveyChart")
	public ReturnData getSurveyChart(@RequestParam Map<String, Object> reqMap) {
		return service.getSurveyChart(new Criterion(reqMap));
	}

	/** 설문 주관식 응답 결과 그리드 */
	@RequestMapping(value = "getSurveyGrid")
	public ReturnData getSurveyGrid(@RequestParam Map<String, Object> reqMap) {
		return service.getSurveyGrid(new Criterion(reqMap));
	}

	/** 메인 공지사항 리스트 조회 */
	@PostMapping(value = "getMainNoticeList")
	public ReturnData getMainNoticeList(@RequestParam Map<String, Object> reqMap) {
		return service.getMainNoticeList(new Criterion(reqMap, false));
	}

	@RequestMapping(value = "checkAuth")
	public ReturnData checkId(@RequestParam Map<String, Object> reqMap) {
		return service.checkAuth(new Criterion(reqMap));
	}

	//실시간 공지팝업 알림확인(내용 입력X)
	@RequestMapping(value = "addNoticeConfirm", method = RequestMethod.POST)
	public ReturnData addNoticeConfirm(@RequestBody Map<String, Object> reqMap) {
		return service.addNoticeConfirm(new Criterion(reqMap, false));
	}

	//실시간 공지팝업 확인내용 입력
	@RequestMapping(value = "editNoticeConfirm", method = RequestMethod.POST)
	public ReturnData editNoticeConfirm(@RequestBody Map<String, Object> reqMap) {
		return service.editNoticeConfirm(new Criterion(reqMap, false));
	}

	//실시간 공지팝업 확인내용 리스트
	@RequestMapping(value = "getConfirmList")
	public ReturnData getConfirmList(@RequestParam Map<String, Object> reqMap) {
		return service.getConfirmList(new Criterion(reqMap));
	}

	//실시간 공지 확인 확인 여부
	@RequestMapping(value = "getConfirmCheck")
	public ReturnData getConfirmCheck(@RequestParam Map<String, Object> reqMap) {
		return service.getConfirmCheck(new Criterion(reqMap, false));
	}

	//실시간 공지 확인내용 입력 확인 여부
	@RequestMapping(value = "getConfirmReplyCheck")
	public ReturnData getConfirmReplyCheck(@RequestParam Map<String, Object> reqMap) {
		return service.getConfirmReplyCheck(new Criterion(reqMap, false));
	}

	//실시간 공지 확인 삭제
	@RequestMapping(value = "delNoticeConfirm", method = RequestMethod.POST)
	public ReturnData delNoticeConfirm(@RequestBody Map<String, Object> reqMap) {
		return service.delNoticeConfirm(new Criterion(reqMap, false));
	}
}

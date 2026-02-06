# JSP/JS - Spring Controller 통신 API 문서

**최종 수정일**: 2026-01-20

## 개요

KLID 웹 애플리케이션에서 JSP/JS 파일과 Spring Controller 간의 통신 구조 분석 문서.
이 문서는 기존 모놀리식 프로젝트의 레거시 통신 패턴을 정리한 참조 자료입니다.

## 프로젝트 통계

| 구분 | 경로 | 파일/엔드포인트 수 |
|------|------|-------------------|
| JSP | `/src/main/webapp/WEB-INF/view/` | 100+ 파일 |
| JS | `/src/main/webapp/js/` | 93+ 파일 |
| Controller | `/src/main/java/com/klid/webapp/` | 87개 |
| View Controller | 26개 클래스 | ~130+ 엔드포인트 |
| Data Controller | 61개 클래스 | ~280+ API 엔드포인트 |

## 요청 분류

| 분류 | 설명 | Controller 특징 |
|------|------|----------------|
| **웹뷰 요청** | JSP 페이지 렌더링 | `@Controller`, `void`/`String` 반환 |
| **데이터 요청** | JSON 데이터 반환 | `@ResponseBody`/`@RestController`, `ReturnData`/`ResponseEntity` 반환 |

---

# Part 1: View Controllers (웹뷰 요청)

`@Controller` 어노테이션 사용, `@ResponseBody` 없음, 반환 타입: `void`, `String`, `ModelAndView`

## 기본 View

| Controller | URI | 설명 |
|-----------|-----|------|
| ViewController | `/` | 루트 페이지 |
| ViewController | `/login.do` | 로그인 페이지 |
| ViewController | `/engineer.do` | 엔지니어 페이지 |
| ViewController | `/main/main.do` | 메인 페이지 |
| ViewController | `/error.do` | 에러 페이지 |

## WebDash
| Controller | URI | 설명 |
|-----------|-----|------|
| WebDashViewController | `/webdash/{path}` | 대시보드 뷰 (동적 경로) |

## 환경 설정

| Controller | URI | 설명 |
|-----------|-----|------|
| EnvViewController | `/main/env/instMgmt` | 기관 관리 |
| EnvViewController | `/main/env/instIPMgmt` | 기관 IP 관리 |
| EnvViewController | `/main/env/nationIPMgmt` | 국가 IP 관리 |
| EnvViewController | `/main/env/userConf` | 사용자 설정 |
| EnvViewController | `/main/env/user-management` | 사용자 관리 |
| EnvViewController | `/main/env/user-management/history` | 사용자 관리 이력 |

## 게시판

| Controller | URI | 설명 |
|-----------|-----|------|
| SecViewController | `/main/sec/shareBoardList` | 공유 게시판 |
| SecViewController | `/main/sec/noticeBoardList` | 공지사항 게시판 |
| SecViewController | `/main/sec/resourceBoardList` | 자료실 게시판 |
| SecViewController | `/main/sec/takeOverBoardList` | 인수인계 게시판 |
| SecViewController | `/main/sec/qnaBoardList` | Q&A 게시판 |

## 보고서

| Controller | URI | 설명 |
|-----------|-----|------|
| RptViewController | `/main/rpt/reportCollection` | 보고서 모음 |
| RptViewController | `/main/rpt/reportDailyState` | 일일 현황 |
| RptViewController | `/main/rpt/reportWeeklyState` | 주간 현황 |
| RptViewController | `/main/rpt/reportInciLocal` | 지역별 사고 |
| RptViewController | `/main/rpt/reportSecurityResult` | 보안 결과 |
| RptViewController | `/main/rpt/reportInciType` | 사고 유형 |
| RptViewController | `/main/rpt/reportInciAttNatn` | 공격 국가 |
| RptViewController | `/main/rpt/reportDailyInciState` | 일일 사고 현황 |
| RptViewController | `/main/rpt/reportDaily` | 일일 보고서 |
| RptViewController | `/main/rpt/reportInciPrty` | 사고 우선순위 |
| RptViewController | `/main/rpt/reportInciPrcsStat` | 사고 처리 상태 |
| RptViewController | `/main/rpt/reportInciDetail` | 사고 상세 |

## 이력 관리

| Controller | URI | 설명 |
|-----------|-----|------|
| HistViewController | `/main/hist/userInoutHistMgmt` | 사용자 입출력 이력 |
| HistViewController | `/main/hist/userActHist` | 사용자 활동 이력 |
| HistViewController | `/main/hist/smsEmailHistMgmt` | SMS/이메일 이력 |
| UserConnectLogViewController | `/main/hist/userConnectLogMgmt` | 접속 로그 |
| UserActionLogViewController | `/main/logs/user-action` | 액션 로그 |

## 사고/시스템/홈/VMS/CTRS/CTSS/MOIS/엔지니어

| Controller | URI | 설명 |
|-----------|-----|------|
| AccidentViewController | `/main/acc/accidentApplyList` | 사고 접수 목록 |
| CodeMgmtViewController | `/main/sys/codeMgmtList` | 코드 관리 |
| CodeMgmtViewController | `/main/sys/weekMgmt` | 주간 관리 |
| CodeMgmtViewController | `/main/sys/boardMgmt` | 게시판 관리 |
| CodeMgmtViewController | `/main/sys/custUserMgmt` | 고객 사용자 관리 |
| RiskMgmtViewController | `/main/sys/riskMgmt` | 위험 관리 |
| HomeViewController | `/main/home/healthCheckUrlList` | 헬스체크 URL |
| HomeViewController | `/main/home/forgeryUrlList` | 위변조 URL |
| VmsViewController | `/main/vms` | VMS 메인 |
| CtrsViewController | `/main/ctrs/accidentProcState` | 사고 처리 상태 |
| MoisViewController | `/main/mois/dashConfig` | MOIS 대시보드 설정 |

## 팝업 (주요 항목)

### 사고 관리 팝업
`/main/popup/acc/pAccidentAdd`, `/main/popup/acc/pAccidentDetail`, `/main/popup/acc/pAccidentEdit` 등 8개

### 시스템 관리 팝업
`/main/popup/sys/pCodeLv1Add`, `/main/popup/sys/pBoardMgmtEdit` 등 17개

### 환경 설정 팝업
`/main/popup/env/pInstAdd`, `/main/popup/env/pUserConfAdd` 등 18개

### 기타 팝업
홈, 게시판, 보고서, 엔지니어 팝업 약 10개

---

# Part 2: Data Controllers (데이터 API)

`@RestController` 또는 `@ResponseBody` 사용, 반환 타입: `ReturnData`, `ResponseEntity`

## 인증/로그인 API

| Controller | URI | HTTP | 설명 |
|-----------|-----|------|------|
| LoginController | `/api/login/test` | POST | 테스트 |
| LoginController | `/api/login/logout` | POST | 로그아웃 |
| LoginVmsPrimaryController | `/api/login/vms/authenticate/primary` | POST | VMS 1차 인증 |
| LoginVmsSecondController | `/api/login/vms/authenticate/second/otp` | POST | VMS OTP 인증 |
| LoginVmsSecondController | `/api/login/vms/authenticate/second/email/send` | POST | VMS 이메일 전송 |
| LoginVmsSecondController | `/api/login/vms/authenticate/second/email/validate` | POST | VMS 이메일 검증 |
| LoginCtssPrimaryController | `/api/login/ctss/authenticate/primary` | POST | CTSS 1차 인증 |
| LoginCtssSecondController | `/api/login/ctss/authenticate/second/otp` | POST | CTSS OTP 인증 |
| LoginCtrsPrimaryController | `/api/login/ctrs/authenticate/primary` | POST | CTRS 1차 인증 |
| LoginCtrsSecondController | `/api/login/ctrs/authenticate/second/otp` | POST | CTRS OTP 인증 |
| LoginCtrsSecondController | `/api/login/ctrs/authenticate/second/email/send` | POST | CTRS 이메일 인증 |

## 공통 API

| Controller | URI | 설명 |
|-----------|-----|------|
| GrpController | `/api/common/grp/getGrpList` | 그룹 목록 |
| CodeController | `/api/common/code/getAllCode` | 전체 코드 |
| MenuController | `/api/common/menu/getMenuList` | 메뉴 목록 |
| FileController | `/api/file/download.do` | 파일 다운로드 |
| FileController | `/api/file/upload.do` | 파일 업로드 |
| SmsController | `/api/main/sms/send` | SMS 전송 |

## 대시보드 API (WebDash)

| Controller | URI | HTTP | 설명 |
|-----------|-----|------|------|
| AdminControlController | `/api/webdash/adminControl/incidentStatus` | GET | 사고 현황 |
| AdminControlController | `/api/webdash/adminControl/inciCnt` | GET | 사고 건수 |
| WebDashCenterController | `/api/webdash/center/list` | POST | 센터 목록 |
| WebDashMoisController | `/api/webdash/mois/list` | POST | MOIS 목록 |
| WebDashSidoController | `/api/webdash/sido/list` | POST | 시도 목록 |

## 게시판 API (5개 게시판 × CRUD)

각 게시판(Share, Notice, Resource, TakeOver, Qna)에 동일한 패턴:
- `getList`, `getDetail`, `add`, `edit`, `delete`
- URI: `/api/main/sec/{boardName}/getList` 등

## 보고서 API

각 보고서 Controller에 `getList` 또는 `getReportExcelList` 엔드포인트:
- URI: `/api/main/rpt/{reportType}/getList` 등

## 환경 설정 API

| 도메인 | 주요 API | 엔드포인트 수 |
|--------|---------|-------------|
| InstMgmt (기관) | CRUD + 상위기관 + 코드 중복 | 6 |
| InstIPMgmt (기관 IP) | CRUD | 4 |
| NationIPMgmt (국가 IP) | CRUD + 엑셀 가져오기 | 5 |
| UserConf (사용자) | CRUD + 상세 + 권한 + 비밀번호 | 10 |
| UserManagement | 요청/OTP/GPKI/비밀번호/잠금 초기화 | 5 |
| UserManagementHistory | 그리드/비교/취소/승인/엑셀 | 6 |

## 사고 관리 API

AccidentApplyController: 28개 엔드포인트 (CRUD + 이력 + 통계 + 가져오기/내보내기 + 암호화)

## 기타 (로그, 시스템, MOIS/VMS/CTSS)

- 로그 관련: 접속로그, 액션로그, CSV 다운로드, 일일/기간별/기관별
- 시스템: 코드관리(8), 고객사용자관리(9), 위험관리(5)
- MOIS/VMS/CTSS: 대시보드 설정, 개인정보처리방침, 인증 리다이렉트

---

# Part 3: JS 통신 패턴

## 패턴 요약

| 패턴 | 용도 | 예시 |
|------|------|------|
| `Server.get(url, params, callback)` | GET 데이터 조회 | 코드/상세 조회 |
| `Server.post(url, params, callback)` | POST 저장/수정/삭제 | 폼 데이터 저장 |
| `$.ajax({...})` | 복잡한 AJAX (헤더, 에러 처리) | JSON 요청 |
| `$.get(url, params, callback)` | 간단한 GET (팝업 HTML) | 팝업 HTML 로드 |
| `fetch(url, options)` | 최신 비동기 (Promise) | 로그인 인증 |
| `jqxGrid DataAdapter` | 그리드 데이터 소스 | 목록 그리드 |
| `HmUtil.createPopup({...})` | 모달 팝업 생성 | 사고 상세 팝업 |
| `HmUtil.exportExcel({...})` | 엑셀 다운로드 | 기관 목록 내보내기 |
| `location.href` | 페이지 전환 | 로그인, 메인 이동 |

## 핵심 참조 파일

| 파일 | 설명 |
|------|------|
| `/js/hm/hm.util.js` | Server.get/post, HmUtil.createPopup, exportExcel 정의 |
| `ReturnData.java` | API 응답 포맷: `{result, message, data, totalCount}` |

## Server.get / Server.post (커스텀 래퍼)

정의: `/src/main/webapp/js/hm/hm.util.js`

```javascript
// 조회
Server.get("/main/acc/accidentApply/getAccidentDetail.do", { accdNo: "ACC2024001" }, function(data) {
    // 상세 정보 처리
});

// 저장
Server.post("/main/acc/accidentApply/addAccidentApply.do", {
    accdNm: "사고명", accdType: "TYPE01"
}, function(data) {
    if (data.result === "success") alert("저장되었습니다.");
});
```

## HmUtil.createPopup (팝업)

```javascript
HmUtil.createPopup({
    url: "/main/popup/acc/pAccidentDetail.do",
    title: "사고 상세",
    width: 900, height: 700,
    params: { accdNo: selectedAccdNo },
    callback: function(result) {
        if (result && result.updated) grid.refresh();
    }
});
```

## jqxGrid DataAdapter

```javascript
var source = {
    datatype: "json",
    datafields: [
        { name: "accdNo", type: "string" },
        { name: "accdNm", type: "string" }
    ],
    url: "/main/acc/accidentApply/getAccidentList.do",
    type: "POST",
    root: "data",
    beforeprocessing: function(data) { source.totalrecords = data.totalCount; }
};
var dataAdapter = new $.jqx.dataAdapter(source);
```

## 응답 데이터 구조 (ReturnData)

```json
{
    "result": "success",    // success, fail, error
    "message": "처리 완료",
    "data": { },
    "totalCount": 100
}
```

## URI 패턴 규칙

- 모든 URI는 `.do` 확장자 사용 (레거시)
- View: `/main/{module}/{page}` 형태
- Data: `/main/{module}/{resource}/{action}` 형태
- 팝업: `/main/popup/{module}/{popupName}` 형태

# KLID Backend 마이그레이션 체크리스트

> 원본: `/Users/totoku103/BurunetProjects/klid-java-web` (모놀리식)
> 마이그레이션: `/Users/totoku103/IdeaProjects/klid-backend` (Spring Boot REST API)
>
> 비교 기준: 원본의 `com.klid.webapp` 기능이 신규 `com.klid.api`로 전환되었는지 확인
> 작성일: 2026-02-06

## 요약

| 도메인 | 전체 항목 | 완료 | 부분 | 미완료 | 진행률 |
|--------|----------|------|------|--------|--------|
| 게시판 (Board) | 6 | 6 | 0 | 0 | 100% |
| 보고서 (Report) | 12 | 12 | 0 | 0 | 100% |
| 로그 (Logs) | 10 | 10 | 0 | 0 | 100% |
| 시스템 (System) | 5 | 5 | 0 | 0 | 100% |
| 웹대시보드 (WebDash) | 4 | 4 | 0 | 0 | 100% |
| 모니터링 (Monitoring) | 2 | 2 | 0 | 0 | 100% |
| 관리자 (Admin/Engineer) | 2 | 2 | 0 | 0 | 100% |
| 인증 (Auth) | 3 | 3 | 0 | 0 | 100% |
| 공통 (Common) | 7 | 5 | 2 | 0 | 71% |
| 외부연동 (ThirdParty) | 5 | 3 | 2 | 0 | 60% |
| 이력 (History) | 4 | 0 | 3 | 1 | 0% |
| 환경설정 (Environment) | 5 | 0 | 5 | 0 | 0% |
| Config/Security | 6 | 4 | 2 | 0 | 67% |
| 스케줄러/리스너 | 3 | 1 | 0 | 2 | 33% |
| **합계** | **74** | **57** | **14** | **3** | **77%** |

---

## 1. 게시판 (Board) - 100%

### 1.1 사고 접수/처리 (Accident)
- [x] Controller: `AccidentApplyController` → 8개 전문 컨트롤러로 분리
  - `AccidentListController`, `AccidentReportController`, `AccidentProcessController`
  - `AccidentDocumentController`, `AccidentIpController`, `AccidentImportController`
  - `AccidentExternalApiController`, `AccidentStatisticsController`
- [x] Service: `AccidentApplyService` → `AccidentListService` + 레거시 서비스 재사용
- [x] Mapper: `AccidentListMapper` (신규) + 레거시 매퍼 재사용
- 위치: `com.klid.api.board.accident`

### 1.2 공지사항 (Notice)
- [x] Controller: `NoticeBoardController` → `NoticeController`
- [x] Service: `NoticeBoardServiceImpl` → `NoticeService`
- [x] Mapper: `NoticeBoardMapper` → `NoticeMapper`
- 위치: `com.klid.api.board.notice`

### 1.3 Q&A 게시판
- [x] Controller: `QnaBoardController` → `QnAController`
- [x] Service: `QnaBoardServiceImpl` → `QnAService`
- [x] Mapper: `QnaBoardMapper` → `QnAMapper`
- 위치: `com.klid.api.board.qna`

### 1.4 자료실 (Resource)
- [x] Controller: `ResourceBoardController` → `ResourceController`
- [x] Service: `ResourceBoardServiceImpl` → `ResourceBoardService`
- [x] Mapper: `ResourceBoardMapper` → `ResourceBoardMapper`
- 위치: `com.klid.api.board.resource`

### 1.5 정보공유 (Share)
- [x] Controller: `ShareBoardController` → `ShareController`
- [x] Service: `ShareBoardServiceImpl` → `ShareBoardService`
- [x] Mapper: `ShareBoardMapper` → `ShareBoardMapper`
- 위치: `com.klid.api.board.share`

### 1.6 인수인계 (Takeover)
- [x] Controller: `TakeOverBoardController` → `TakeoverController`
- [x] Service: `TakeOverBoardServiceImpl` → `TakeoverBoardService`
- [x] Mapper: `TakeOverBoardMapper` → `TakeoverBoardMapper`
- 위치: `com.klid.api.board.takeover`

---

## 2. 보고서 (Report) - 100%

### 2.1 보고서 수집 현황
- [x] Controller: `ReportCollectionController` → `ReportCollectionController`
- [x] Service: `ReportCollectionServiceImpl` → `ReportCollectionService`
- [x] Mapper: `ReportCollectionMapper` → `ReportCollectionMapper`
- 위치: `com.klid.api.report.collection`

### 2.2 일일 보고
- [x] Controller: `ReportDailyController` → `ReportDailyController`
- [x] Service: `ReportDailyServiceImpl` → `ReportDailyService`
- [x] Mapper: `ReportDailyMapper` → `ReportDailyMapper`
- 위치: `com.klid.api.report.daily`

### 2.3 일일 현황
- [x] Controller: `ReportDailyStateController` → `ReportDailyStateController`
- [x] Service: `ReportDailyStateServiceImpl` → `ReportDailyStateService`
- [x] Mapper: `ReportDailyStateMapper` → `ReportDailyStateMapper`
- 위치: `com.klid.api.report.daily`

### 2.4 일일 사고 현황
- [x] Controller: `ReportDailyInciStateController` → `ReportDailyInciStateController`
- [x] Service: `ReportDailyInciStateServiceImpl` → `ReportDailyInciStateService`
- [x] Mapper: `ReportDailyInciStateMapper` → `ReportDailyInciStateMapper`
- 위치: `com.klid.api.report.daily`

### 2.5 주간 현황
- [x] Controller: `ReportWeeklyStateController` → `ReportWeeklyStateController`
- [x] Service: `ReportWeeklyStateServiceImpl` → `ReportWeeklyStateService`
- [x] Mapper: `ReportWeeklyStateMapper` → `ReportWeeklyStateMapper`
- 위치: `com.klid.api.report.weekly`

### 2.6 사고 상세
- [x] Controller: `ReportInciDetailController` → `ReportInciDetailController`
- [x] Service: `ReportInciDetailServiceImpl` → `ReportInciDetailService`
- [x] Mapper: `ReportInciDetailMapper` → `ReportInciDetailMapper`
- 위치: `com.klid.api.report.incident`

### 2.7 지역별 사고
- [x] Controller: `ReportInciLocalController` → `ReportInciLocalController`
- [x] Service: `ReportInciLocalServiceImpl` → `ReportInciLocalService`
- [x] Mapper: `ReportInciLocalMapper` → `ReportInciLocalMapper`
- 위치: `com.klid.api.report.incident`

### 2.8 유형별 사고
- [x] Controller: `ReportInciTypeController` → `ReportInciTypeController`
- [x] Service: `ReportInciTypeServiceImpl` → `ReportInciTypeService`
- [x] Mapper: `ReportInciTypeMapper` → `ReportInciTypeMapper`
- 위치: `com.klid.api.report.incident`

### 2.9 우선순위별 사고
- [x] Controller: `ReportInciPrtyController` → `ReportInciPrtyController`
- [x] Service: `ReportInciPrtyServiceImpl` → `ReportInciPrtyService`
- [x] Mapper: `ReportInciPrtyMapper` → `ReportInciPrtyMapper`
- 위치: `com.klid.api.report.incident`

### 2.10 사고 처리 통계
- [x] Controller: `ReportInciPrcsStatController` → `ReportInciPrcsStatController`
- [x] Service: `ReportInciPrcsStatServiceImpl` → `ReportInciPrcsStatService`
- [x] Mapper: `ReportInciPrcsStatMapper` → `ReportInciPrcsStatMapper`
- 위치: `com.klid.api.report.incident`

### 2.11 공격국가별 사고
- [x] Controller: `ReportInciAttNatnController` → `ReportInciAttNatnController`
- [x] Service: `ReportInciAttNatnServiceImpl` → `ReportInciAttNatnService`
- [x] Mapper: `ReportInciAttNatnMapper` → `ReportInciAttNatnMapper`
- 위치: `com.klid.api.report.incident`

### 2.12 보안 결과
- [x] Controller: `ReportSecurityResultController` → `ReportSecurityResultController`
- [x] Service: `ReportSecurityResultServiceImpl` → `ReportSecurityResultService`
- [x] Mapper: `ReportSecurityResultMapper` → `ReportSecurityResultMapper`
- 위치: `com.klid.api.report.security`

---

## 3. 로그 (Logs) - 100%

### 3.1 사용자 행위 로그
- [x] Controller: `UserActionLogController` → `UserActionLogController`
- [x] Service: `UserActionLogService` → `UserActionLogService`
- [x] Mapper: `UserActionLogMapper` → `UserActionLogMapper`
- 위치: `com.klid.api.logs.action`

### 3.2 일별 행위 로그
- [x] Controller: `UserActionLogDailyController` → `UserActionLogDailyController`
- [x] Service: `UserActionLogDailyService` → `UserActionLogDailyService`
- [x] Mapper: `UserActionLogDailyMapper` → `UserActionLogDailyMapper`
- 위치: `com.klid.api.logs.action`

### 3.3 기관별 행위 로그
- [x] Controller: `UserActionLogInstitutionController` → `UserActionLogInstitutionController`
- [x] Service: `UserActionLogInstitutionService` → `UserActionLogInstitutionService`
- [x] Mapper: `UserActionLogInstitutionMapper` → `UserActionLogInstitutionMapper`
- 위치: `com.klid.api.logs.action`

### 3.4 기간별 행위 로그
- [x] Controller: `UserActionLogPeriodController` → `UserActionLogPeriodController`
- [x] Service: `UserActionLogPeriodService` → `UserActionLogPeriodService`
- [x] Mapper: `UserActionLogPeriodMapper` → `UserActionLogPeriodMapper`
- 위치: `com.klid.api.logs.action`

### 3.5 사용자 접속 로그
- [x] Controller: `UserConnectLogController` → `UserConnectLogController`
- [x] Service: `UserConnectLogService` → `UserConnectLogService`
- [x] Mapper: `UserConnectLogMapper` → `UserConnectLogMapper`
- 위치: `com.klid.api.logs.connect`

### 3.6 일별 접속 로그
- [x] Controller: `UserConnectLogDailyController` → `UserConnectLogDailyController`
- [x] Service: `UserConnectLogDailyService` → `UserConnectLogDailyService`
- [x] Mapper: `UserConnectLogDailyMapper` → `UserConnectLogDailyMapper`
- 위치: `com.klid.api.logs.connect`

### 3.7 기관별 접속 로그
- [x] Controller: `UserConnectLogInstitutionController` → `UserConnectLogInstitutionController`
- [x] Service: `UserConnectLogInstitutionService` → `UserConnectLogInstitutionService`
- [x] Mapper: `UserConnectLogInstitutionMapper` → `UserConnectLogInstitutionMapper`
- 위치: `com.klid.api.logs.connect`

### 3.8 기간별 접속 로그
- [x] Controller: `UserConnectLogPeriodController` → `UserConnectLogPeriodController`
- [x] Service: `UserConnectLogPeriodService` → `UserConnectLogPeriodService`
- [x] Mapper: `UserConnectLogPeriodMapper` → `UserConnectLogPeriodMapper`
- 위치: `com.klid.api.logs.connect`

### 3.9 기관 코드
- [x] Controller: `InstitutionCodeController` → `InstitutionCodeController`
- [x] Service: `InstitutionCodeService` → `InstitutionCodeService`
- [x] Mapper: `InstitutionCodeMapper` → `InstitutionCodeMapper`
- 위치: `com.klid.api.logs.common`

### 3.10 로그 CSV 다운로드
- [x] Controller: `LogsCsvDownloadController` → `LogsCsvDownloadController`
- [x] Service: (개선됨) → `InstitutionAggregateService`
- 위치: `com.klid.api.logs.common`

---

## 4. 시스템 (System) - 100%

### 4.1 코드 관리
- [x] Controller: `CodeMgmtController` → `CodeController`
- [x] Service: `CodeServiceImpl` → `CodeService`
- [x] Mapper: `CodeMapper` → `CodeMapper`
- 위치: `com.klid.api.system.code`

### 4.2 고객 사용자 관리
- [x] Controller: `CustUserMgmtController` → `CustomerUserController`
- [x] Service: `CustUserMgmtServiceImpl` → `CustomerUserService`
- [x] Mapper: `CustUserMgmtMapper` → `CustomerUserMapper`
- 위치: `com.klid.api.system.customer`

### 4.3 위험 관리
- [x] Controller: `RiskMgmtController` → `RiskController`
- [x] Service: `RiskMgmtServiceImpl` → `RiskService`
- [x] Mapper: `RiskMgmtMapper` → `RiskMapper`
- 위치: `com.klid.api.system.risk`

### 4.4 SMS
- [x] Controller: `SmsController` → `SmsController`
- [x] Service: `SmsServiceImpl` → `SmsService`
- [x] Mapper: `SmsMapper` → `SmsMapper`
- 참고: `Nuri2SmsSaveController`/`Nuri2SmsSaveService`는 단일 컨트롤러로 통합
- 위치: `com.klid.api.system.sms`

### 4.5 대시보드 설정 (MOIS)
- [x] Controller: `DashConfigController` → `DashboardConfigController`
- [x] Service: `DashConfigServiceImpl` → `DashboardConfigService`
- [x] Mapper: `DashConfigMapper` → `DashboardConfigMapper`
- 위치: `com.klid.api.system.config`

---

## 5. 웹대시보드 (WebDash) - 100%

### 5.1 센터 대시보드
- [x] Controller: `WebDashCenterController` → `WebDashCenterController`
- [x] Service: `WebDashCenterServiceImpl` → `WebDashCenterService`
- [x] Mapper: `WebDashCenterMapper` → `WebDashCenterMapper`
- 위치: `com.klid.api.webdash.center`

### 5.2 시도 대시보드
- [x] Controller: `WebDashSidoController` → `WebDashSidoController`
- [x] Service: `WebDashSidoServiceImpl` → `WebDashSidoService`
- [x] Mapper: `WebDashSidoMapper` → `WebDashSidoMapper`
- 위치: `com.klid.api.webdash.sido`

### 5.3 행안부 대시보드
- [x] Controller: `WebDashMoisController` → `WebDashMoisController`
- [x] Service: `WebDashMoisServiceImpl` → `WebDashMoisService`
- [x] Mapper: `WebDashMoisMapper` → `WebDashMoisMapper`
- 위치: `com.klid.api.webdash.mois`

### 5.4 관리자 제어
- [x] Controller: `AdminControlController` → `AdminControlController`
- [x] Service: `AdminControlServiceImpl` → `AdminControlService`
- [x] Mapper: `AdminControlMapper` → `AdminControlMapper`
- 위치: `com.klid.api.webdash.admin`

---

## 6. 모니터링 (Monitoring) - 100%

### 6.1 위변조 URL
- [x] Controller: `ForgeryUrlController` → `ForgeryUrlController`
- [x] Service: `ForgeryUrlServiceImpl` → `ForgeryUrlService`
- [x] Mapper: `ForgeryUrlMapper` → `ForgeryUrlMapper`
- 위치: `com.klid.api.monitoring.forgery`

### 6.2 헬스체크 URL
- [x] Controller: `HealthCheckUrlController` → `HealthCheckUrlController`
- [x] Service: `HealthCheckUrlServiceImpl` → `HealthCheckUrlService`
- [x] Mapper: `HealthCheckUrlMapper` → `HealthCheckUrlMapper`
- 위치: `com.klid.api.monitoring.health`

---

## 7. 관리자 (Admin/Engineer) - 100%

### 7.1 메뉴 관리
- [x] Controller: `MenuMgmtController` → `MenuMgmtController`
- [x] Service: `MenuMgmtServiceImpl` → `MenuMgmtService`
- [x] Mapper: `MenuMgmtMapper` → `MenuMgmtMapper`
- 위치: `com.klid.api.admin.menu`

### 7.2 팝업 관리
- [x] Controller: `PopupController` → `PopupController`
- [x] Service: `PopupServiceImpl` → `PopupService`
- [x] Mapper: `PopupMapper` → `PopupMapper`
- 위치: `com.klid.api.admin.popup`

---

## 8. 인증 (Auth) - 100%

### 8.1 CTRS 인증
- [x] Controller: `LoginCtrsPrimaryController` + `LoginCtrsSecondController` → `CtrsAuthController`
- [x] 엔드포인트: `/primary`, `/secondary/otp`, `/secondary/email`
- 위치: `com.klid.api.auth.ctrs`

### 8.2 CTSS 인증
- [x] Controller: `LoginCtssPrimaryController` + `LoginCtssSecondController` → `CtssAuthController`
- [x] 엔드포인트: `/primary`, `/secondary/otp`, `/secondary/email`
- 위치: `com.klid.api.auth.ctss`

### 8.3 VMS 인증
- [x] Controller: `LoginVmsPrimaryController` + `LoginVmsSecondController` → `VmsAuthController`
- [x] 엔드포인트: `/primary`, `/secondary/otp`, `/secondary/email`
- 위치: `com.klid.api.auth.vms`

---

## 9. 공통 (Common) - 71%

### 9.1 메뉴
- [x] Controller: `MenuController` → `MenuController`
- 위치: `com.klid.api.menu`

### 9.2 파일 (Upload/Download/Delete)
- [x] Controller: `FileController` → `FileController`
- [x] Service: 3개 서비스 → `FileService` (단일 통합)
- 위치: `com.klid.api.common.file`

### 9.3 공통 코드
- [x] Controller: `CodeController` → `CommonCodeController`
- [x] Service: `CodeServiceImpl` → `CommonCodeService`
- 위치: `com.klid.api.common.code`

### 9.4 그룹
- [x] Controller: `GrpController` → `GroupController`
- [x] Service: `GrpServiceImpl` → `GroupService`
- 위치: `com.klid.api.common.group`

### 9.5 세션
- [x] Controller: (신규) `SessionController`
- [x] Service: (신규) `SessionService`
- 위치: `com.klid.api.session`

### 9.6 GPKI
- [~] Controller 없음, 인증 API에 기능 통합됨
- [~] Service/Mapper: 레거시만 존재 (`webapp/common/service`)

### 9.7 OTP
- [~] Controller 없음, 인증 API에 기능 통합됨 (`/secondary/otp`)
- [~] Service/Mapper: 레거시만 존재 (`webapp/common/service`)

---

## 10. 외부연동 (ThirdParty) - 60%

### 10.1 CTSS REST API
- [x] Controller: `CtssRestController` → `CtssController`
- [x] Service: → `CtssService`
- 위치: `com.klid.api.system.thirdparty`

### 10.2 VMS REST API
- [x] Controller: `VmsRestController` → `VmsController`
- [x] Service: → `VmsService`
- 위치: `com.klid.api.system.thirdparty`

### 10.3 리다이렉트
- [x] Controller: `CtrsRedirectController` → `ThirdPartyRedirectController`
- [x] Service: → `ThirdPartyRedirectService`
- 위치: `com.klid.api.common.redirect`

### 10.4 인증 서비스 (Primary/Secondary)
- [~] PrimaryCtrsService, SecondCtrsService: 레거시만 존재
- [~] PrimaryCtssService, SecondCtssService: 레거시만 존재
- [~] PrimaryVmsService, SecondVmsService: 레거시만 존재
- 참고: Controller는 api/auth에 있으나 Service는 webapp에 잔존

### 10.5 ThirdParty 설정
- [~] ThirdPartyProperty: 레거시 위치에만 존재
- [~] ThirdPartyRestTemplate: 미존재
- [~] ThirdPartyPropertyCrypto: 레거시 위치에만 존재

---

## 11. 이력 (History) - 0%

### 11.1 사용자 활동 이력
- [~] Controller: 레거시만 존재 (`webapp/main/hist`)
- [~] Service: 레거시만 존재 (`UserActHistServiceImpl`)
- [x] Mapper/DTO: `com.klid.api.hist.useract`에 존재
- 상태: DTO/Mapper만 이동, Controller/Service 미구현

### 11.2 사용자 입출입 이력
- [~] Controller: 레거시만 존재
- [~] Service: 레거시만 존재 (`UserInoutHistMgmtServiceImpl`)
- [x] Mapper/DTO: `com.klid.api.hist.userinout`에 존재
- 상태: DTO/Mapper만 이동, Controller/Service 미구현

### 11.3 SMS/이메일 이력
- [~] Controller: 레거시만 존재
- [~] Service: 레거시만 존재 (`SmsEmailHistMgmtServiceImpl`)
- [x] Mapper/DTO: `com.klid.api.hist.smsemail`에 존재
- 상태: DTO/Mapper만 이동, Controller/Service 미구현

### 11.4 SMS 발송 이력
- [ ] Controller: 미존재
- [ ] Service: 미존재
- [ ] Mapper: 미존재
- 상태: 전혀 마이그레이션되지 않음

---

## 12. 환경설정 (Environment) - 0%

### 12.1 기관 관리
- [~] Controller/Service: 미존재
- [x] Mapper: `InstMgmtMapper` (`com.klid.api.env.inst`)에 존재
- 상태: Mapper만 이동

### 12.2 기관 IP 관리
- [~] Controller/Service: 미존재
- [x] Mapper: `InstIPMgmtMapper` (`com.klid.api.env.instip`)에 존재
- 상태: Mapper만 이동

### 12.3 국가 IP 관리
- [~] Controller/Service: 미존재
- [x] Mapper: `NationIPMgmtMapper` (`com.klid.api.env.nationip`)에 존재
- 상태: Mapper만 이동

### 12.4 사용자 관리
- [~] Controller/Service: 미존재
- [x] Mapper: `UserManagementMapper`, `UserManagementHistoryMapper` (`com.klid.api.env.usermgmt`)에 존재
- 상태: Mapper만 이동

### 12.5 사용자 설정
- [~] Controller/Service: 미존재
- [x] Mapper: `UserConfMapper` (`com.klid.api.env.userconf`)에 존재
- 상태: Mapper만 이동

---

## 13. Config/Security - 67%

### 13.1 데이터소스 설정
- [x] `DbConfig` → `DataSourceConfig`
- 위치: `com.klid.config`

### 13.2 Spring Security
- [x] `SecurityConfig` → `SecurityConfig` (Spring Security 6.x)
- [x] `TwoFactorAuthenticationFilter` (신규 추가)
- [x] `CustomAuthenticationProvider` (신규 추가)
- 위치: `com.klid.config`, `com.klid.webapp.common.security`

### 13.3 필터 설정
- [x] `FilterConfig` → `FilterConfig`
- [x] `CrossScriptingFilter` (유지)
- [x] `SecurityFilter` (유지)

### 13.4 WebMvc 설정
- [x] (신규) `WebMvcConfig`

### 13.5 RestTemplate 설정
- [~] `RestTemplateConfig`: 레거시 위치에만 존재

### 13.6 예외 처리
- [~] `GlobalControllerExceptionHandler`: 확인 필요
- [x] 신규 예외 클래스: `NotFoundUserException`, `PrefixMessageRuntimeException` 등

---

## 14. 스케줄러/리스너 - 33%

### 14.1 기관 코드 스케줄러
- [x] `InstitutionCodeScheduler` → `InstitutionCodeScheduler`
- 위치: `com.klid.scheduler`

### 14.2 시작 러너
- [ ] `StartupRunner`: 미존재

### 14.3 세션 속성 로거
- [ ] `SessionAttributeLogger`: 미존재

---

## 15. 신규 추가 기능 (원본에 없음)

### 15.1 WebSocket 실시간 알림
- [x] `RealtimeNotifyNotificationHandler` (신규)
- [x] `RealtimeNotifyNotificationServiceImpl` (신규)
- [x] `RealtimeNotifyWebSocketConfig` (신규)

### 15.2 대시보드 API
- [x] `DashboardController` (신규)
- [x] `DashboardService` (신규)
- 위치: `com.klid.api.dashboard`

### 15.3 SQL 로깅
- [x] `P6SpyConfig`, `P6SpySqlFormatter` (dev/local 프로파일)

---

## 마이그레이션 우선순위

### HIGH - 즉시 필요
1. **환경설정 (Environment)** - 기관/사용자 관리 Controller/Service 구현
2. **이력 (History)** - Controller/Service 구현 (Mapper는 준비됨)
3. **SMS 발송 이력** - 전체 구현 필요

### MEDIUM - 개선 필요
4. **인증 서비스 위치 정리** - `webapp/common/service` → `api/auth/service`로 이동
5. **ThirdParty 설정 정리** - Properties를 `config/` 패키지로 이동
6. **RestTemplate 설정** - 신규 Config로 마이그레이션

### LOW - 정리 작업
7. **레거시 webapp 패키지 정리** - 마이그레이션 완료 후 제거
8. **StartupRunner/SessionAttributeLogger** - 필요 여부 검토 후 결정
9. **GlobalExceptionHandler** - REST API 전용 예외 핸들러 재구현

---

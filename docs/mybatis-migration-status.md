# MyBatis XML 매퍼 마이그레이션 현황

**최종 업데이트: 2026-02-06**

---

## 개요

이 문서는 레거시 모놀리식 프로젝트(`klid-java-web`)에서 신규 백엔드 프로젝트(`klid-backend`)로의 MyBatis XML 매퍼 마이그레이션 진행 상황을 추적합니다.

### 마이그레이션 요약

| 항목 | 수량 |
|------|------|
| **원본 매퍼 총계** | 52개 |
| 원본 Oracle 매퍼 | 51개 |
| 원본 Maria 매퍼 | 1개 |
| **신규 구조로 마이그레이션** | 33개 |
| **백엔드 전용 신규 매퍼** | 4개 |
| **아직 마이그레이션 안 됨** | 19개 |
| **마이그레이션 진행률** | **63%** |

---

## 디렉토리 구조

```
klid-backend/
└── src/main/resources/
    ├── oracle/                    # 레거시 매퍼 (원본 그대로 복사)
    │   └── com/klid/webapp/...   # 원본 구조 유지 (과도기)
    │
    └── mapper/                    # 신규 구조 (마이그레이션 대상)
        ├── admin/                 # 관리자 기능
        ├── board/                 # 게시판
        ├── common/                # 공통
        ├── dashboard/             # 대시보드
        ├── institution/           # 기관
        ├── monitoring/            # 모니터링
        ├── system/                # 시스템
        ├── webdash/               # 웹 대시보드
        └── com/klid/api/logs/     # API 로그
```

### 마이그레이션 상태 설명

- **O (마이그레이션 완료)**: `mapper/` 디렉토리에 신규 구조로 이동됨
- **X (미마이그레이션)**: `oracle/` 디렉토리에만 존재 (아직 마이그레이션 안 함)
- **신규**: 원본 프로젝트에 없던 백엔드 전용 신규 매퍼

---

## 도메인별 마이그레이션 현황

### 1. 공통 (Common)

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 1 | CodeMapper.xml | O | CodeMapper.xml | mapper/system/code/ | 공통 코드 |
| 2 | FileDeleteMapper.xml | X | - | - | 파일 삭제 |
| 3 | FileDownloadMapper.xml | X | - | - | 파일 다운로드 |
| 4 | FileUploadMapper.xml | X | - | - | 파일 업로드 |
| 5 | GrpMapper.xml | O | GroupMapper.xml | mapper/common/group/ | 그룹 관리 |
| 6 | MenuMapper.xml | O | MenuMgmtMapper.xml | mapper/admin/menu/ | 메뉴 관리 |
| 7 | LoginMapper.xml | X | - | - | 로그인 |
| 8 | PolicyConfMapper.xml | X | - | - | 정책 설정 |

**소계**: 8개 중 3개 마이그레이션 (37.5%)

---

### 2. 사고 (Accident)

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 9 | AccidentApplyMapper.xml | O | AccidentListMapper.xml | mapper/board/accident/ | 사고 신청/목록 |

**소계**: 1개 중 1개 마이그레이션 (100%)

---

### 3. 환경설정 (Environment)

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 10 | InstIPMgmtMapper.xml | X | - | - | 기관 IP 관리 |
| 11 | InstMgmtMapper.xml | X | - | - | 기관 관리 |
| 12 | NationIPMgmtMapper.xml | X | - | - | 국가 IP 관리 |
| 13 | UserConfMapper.xml | X | - | - | 사용자 설정 |
| 14 | UserManagementMapper.xml | X | - | - | 사용자 관리 |
| 15 | UserManagementHistoryMapper.xml | X | - | - | 사용자 관리 이력 |

**소계**: 6개 중 0개 마이그레이션 (0%)

---

### 4. 이력 (History)

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 16 | SmsEmailHistMgmtMapper.xml | X | - | - | SMS/이메일 이력 |
| 17 | UserActHistMapper.xml | X | - | - | 사용자 활동 이력 |
| 18 | UserInoutHistMgmtMapper.xml | X | - | - | 사용자 출입 이력 |

**소계**: 3개 중 0개 마이그레이션 (0%)

---

### 5. 모니터링 (Monitoring/Home)

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 19 | ForgeryUrlMapper.xml | O | ForgeryUrlMapper.xml | mapper/monitoring/forgery/ | 위조 URL |
| 20 | healthCheckUrlMapper.xml | O | HealthCheckUrlMapper.xml | mapper/monitoring/health/ | 헬스 체크 URL |

**소계**: 2개 중 2개 마이그레이션 (100%)

---

### 6. 로그 (Logs)

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 21 | InstitutionCodeMapper.xml | O | InstitutionCodeMapper.xml | mapper/com/klid/api/logs/common/ | 기관 코드 |
| 22 | UserActionLogMapper.xml | O | UserActionLogMapper.xml | mapper/com/klid/api/logs/action/ | 사용자 활동 로그 |
| 23 | UserActionLogDailyMapper.xml | O | UserActionLogDailyMapper.xml | mapper/com/klid/api/logs/action/ | 일일 활동 로그 |
| 24 | UserActionLogInstitutionMapper.xml | O | UserActionLogInstitutionMapper.xml | mapper/com/klid/api/logs/action/ | 기관별 활동 로그 |
| 25 | UserActionLogPeriodMapper.xml | O | UserActionLogPeriodMapper.xml | mapper/com/klid/api/logs/action/ | 기간별 활동 로그 |
| 26 | UserConnectLogMapper.xml | O | UserConnectLogMapper.xml | mapper/com/klid/api/logs/connect/ | 사용자 접속 로그 |
| 27 | UserConnectLogDailyMapper.xml | O | UserConnectLogDailyMapper.xml | mapper/com/klid/api/logs/connect/ | 일일 접속 로그 |
| 28 | UserConnectLogInstitutionMapper.xml | O | UserConnectLogInstitutionMapper.xml | mapper/com/klid/api/logs/connect/ | 기관별 접속 로그 |
| 29 | UserConnectLogPeriodMapper.xml | O | UserConnectLogPeriodMapper.xml | mapper/com/klid/api/logs/connect/ | 기간별 접속 로그 |

**소계**: 9개 중 9개 마이그레이션 (100%)

---

### 7. 대시보드 설정 (Dashboard Configuration)

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 30 | DashConfigMapper.xml | O | DashboardConfigMapper.xml | mapper/system/config/ | 대시보드 설정 |

**소계**: 1개 중 1개 마이그레이션 (100%)

---

### 8. 보고서 (Report) - **전체 미마이그레이션**

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 31 | ReportCollectionMapper.xml | X | - | - | 보고서 수집 |
| 32 | ReportDailyMapper.xml | X | - | - | 일일 보고서 |
| 33 | ReportDailyInciStateMapper.xml | X | - | - | 일일 사건 상태 |
| 34 | ReportDailyStateMapper.xml | X | - | - | 일일 상태 |
| 35 | ReportInciAttNatnMapper.xml | X | - | - | 사건 속성 국가 |
| 36 | ReportInciDetailMapper.xml | X | - | - | 사건 상세 정보 |
| 37 | ReportInciLocalMapper.xml | X | - | - | 사건 지역 |
| 38 | ReportInciPrcsStatMapper.xml | X | - | - | 사건 처리 상태 |
| 39 | ReportInciPrtyMapper.xml | X | - | - | 사건 속성 |
| 40 | ReportInciTypeMapper.xml | X | - | - | 사건 타입 |
| 41 | ReportSecurityResultMapper.xml | X | - | - | 보안 결과 |
| 42 | ReportWeeklyStateMapper.xml | X | - | - | 주간 상태 |

**소계**: 12개 중 0개 마이그레이션 (0%) - **우선 순위 검토 필요**

---

### 9. 게시판 (Board)

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 43 | NoticeBoardMapper.xml | O | NoticeMapper.xml | mapper/board/ | 공지사항 |
| 44 | QnaBoardMapper.xml | O | QnAMapper.xml | mapper/board/ | 질문/답변 |
| 45 | ResourceMapper.xml | O | ResourceBoardMapper.xml | mapper/board/resource/ | 자료실 |
| 46 | ShareBoardMapper.xml | O | ShareBoardMapper.xml | mapper/board/share/ | 공유 게시판 |
| 47 | TakeOverBoardMapper.xml | O | TakeoverBoardMapper.xml | mapper/board/takeover/ | 인수 인계 |

**소계**: 5개 중 5개 마이그레이션 (100%)

---

### 10. SMS

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 48 | SmsMapper.xml | O | SmsMapper.xml | mapper/system/sms/ | SMS 관리 |

**소계**: 1개 중 1개 마이그레이션 (100%)

---

### 11. 시스템 (System)

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 49 | CustUserMgmtMapper.xml | O | CustomerUserMapper.xml | mapper/system/customer/ | 고객사 사용자 |
| 50 | RiskMgmtMapper.xml | O | RiskMapper.xml | mapper/system/risk/ | 위험 관리 |

**소계**: 2개 중 2개 마이그레이션 (100%)

---

### 12. 서드파티 (Third Party)

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 51 | TokenInfoMapper.xml | X | - | - | 토큰 정보 |

**소계**: 1개 중 0개 마이그레이션 (0%)

---

### 13. 사용자 인증 (User Authentication)

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 52 | GpkiMapper.xml | X | - | - | GPKI 인증 |
| 53 | OtpMapper.xml | X | - | - | OTP 인증 |

**소계**: 2개 중 0개 마이그레이션 (0%)

---

### 14. 웹 대시보드 (Web Dashboard)

| # | 원본 파일명 | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|:-:|---|---|---|
| 54 | AdminControlMapper.xml | O | AdminControlMapper.xml | mapper/webdash/admin/ | 관리자 제어 |
| 55 | WebDashCenterMapper.xml | O | WebDashCenterMapper.xml | mapper/webdash/center/ | 센터 대시보드 |
| 56 | WebDashMoisMapper.xml | O | WebDashMoisMapper.xml | mapper/webdash/mois/ | MOIS 대시보드 |
| 57 | WebDashSidoMapper.xml | O | WebDashSidoMapper.xml | mapper/webdash/sido/ | 시도 대시보드 |

**소계**: 4개 중 4개 마이그레이션 (100%)

---

### 15. Maria DB (마이그레이션 필요 여부 검토 중)

| # | 원본 파일명 | DB | 마이그레이션 | 신규 파일명 | 신규 경로 | 비고 |
|---|---|---|:-:|---|---|---|
| 58 | LoginMapper.xml | Maria | X | - | - | Maria DB 전용 |

**소계**: 1개 중 0개 마이그레이션 (0%)

---

## 백엔드 전용 신규 매퍼 (원본 없음)

| # | 파일명 | 경로 | 설명 | 상태 |
|---|---|---|---|---|
| 1 | CommonCodeMapper.xml | mapper/common/ | 공통 코드 관리 | 신규 추가 |
| 2 | DashboardMapper.xml | mapper/dashboard/ | 대시보드 기능 | 신규 추가 |
| 3 | MonitoringMapper.xml | mapper/monitoring/ | 모니터링 기능 | 신규 추가 |
| 4 | PopupMapper.xml | mapper/admin/popup/ | 팝업 관리 | 신규 추가 |

---

## 미마이그레이션 도메인별 요약

### 우선 순위별 미마이그레이션 현황

#### 1순위: 보고서 (Report) - **12개**

시스템 핵심 기능이나 마이그레이션 복잡도 높음

| 매퍼명 | 원본 경로 | 설명 |
|---|---|---|
| ReportCollectionMapper.xml | oracle/main/rpt/reportCollection/ | 보고서 수집 |
| ReportDailyMapper.xml | oracle/main/rpt/reportDaily/ | 일일 보고서 |
| ReportDailyInciStateMapper.xml | oracle/main/rpt/reportDailyInciState/ | 일일 사건 상태 보고서 |
| ReportDailyStateMapper.xml | oracle/main/rpt/reportDailyState/ | 일일 상태 보고서 |
| ReportInciAttNatnMapper.xml | oracle/main/rpt/reportInciAttNatn/ | 사건 속성 국가별 보고서 |
| ReportInciDetailMapper.xml | oracle/main/rpt/reportInciDetail/ | 사건 상세 정보 보고서 |
| ReportInciLocalMapper.xml | oracle/main/rpt/reportInciLocal/ | 사건 지역별 보고서 |
| ReportInciPrcsStatMapper.xml | oracle/main/rpt/reportInciPrcsStat/ | 사건 처리 상태 보고서 |
| ReportInciPrtyMapper.xml | oracle/main/rpt/reportInciPrty/ | 사건 속성별 보고서 |
| ReportInciTypeMapper.xml | oracle/main/rpt/reportInciType/ | 사건 타입별 보고서 |
| ReportSecurityResultMapper.xml | oracle/main/rpt/reportSecurityResult/ | 보안 결과 보고서 |
| ReportWeeklyStateMapper.xml | oracle/main/rpt/reportWeeklyState/ | 주간 상태 보고서 |

**특징**: 모두 보고 기능 관련 / 대부분 집계 및 통계 쿼리 포함

#### 2순위: 환경설정 (Environment) - **6개**

관리자 기능 / 마이그레이션 복잡도 중간

| 매퍼명 | 원본 경로 | 설명 |
|---|---|---|
| InstIPMgmtMapper.xml | oracle/main/env/instIPMgmt/ | 기관 IP 관리 |
| InstMgmtMapper.xml | oracle/main/env/instMgmt/ | 기관 정보 관리 |
| NationIPMgmtMapper.xml | oracle/main/env/nationIPMgmt/ | 국가 IP 범위 관리 |
| UserConfMapper.xml | oracle/main/env/userConf/ | 사용자 설정 정보 |
| UserManagementMapper.xml | oracle/main/env/userManagement/ | 사용자 관리 |
| UserManagementHistoryMapper.xml | oracle/main/env/userManagementHistory/ | 사용자 관리 이력 추적 |

**특징**: 대부분 시스템 설정 및 관리 기능

#### 3순위: 이력 (History) - **3개**

감사 추적 기능 / 마이그레이션 복잡도 낮음

| 매퍼명 | 원본 경로 | 설명 |
|---|---|---|
| SmsEmailHistMgmtMapper.xml | oracle/main/hist/smsEmailHistMgmt/ | SMS/이메일 발송 이력 |
| UserActHistMapper.xml | oracle/main/hist/userActHist/ | 사용자 활동 이력 |
| UserInoutHistMgmtMapper.xml | oracle/main/hist/userInoutHist/ | 사용자 출입 이력 |

**특징**: 순수 이력 저장 기능 / 조회 중심

#### 4순위: 공통/인증 - **5개**

핵심 기능 / 마이그레이션 복잡도 높음

| 매퍼명 | 원본 경로 | 설명 |
|---|---|---|
| LoginMapper.xml | oracle/common/persistence/ | 로그인 기능 (Oracle) |
| LoginMapper.xml | maria/common/persistence/ | 로그인 기능 (Maria) |
| PolicyConfMapper.xml | oracle/common/persistence/ | 정책 설정 |
| FileDeleteMapper.xml | oracle/common/file/persistence/ | 파일 삭제 |
| FileDownloadMapper.xml | oracle/common/file/persistence/ | 파일 다운로드 |
| FileUploadMapper.xml | oracle/common/file/persistence/ | 파일 업로드 |

**특징**: 로그인은 보안 관련 / 파일은 다른 서버 통신 필요 가능성

#### 5순위: 사용자 인증 - **2개**

보안 기능 / 마이그레이션 복잡도 높음

| 매퍼명 | 원본 경로 | 설명 |
|---|---|---|
| GpkiMapper.xml | oracle/main/user/gpki/ | GPKI 공인인증 |
| OtpMapper.xml | oracle/main/user/otp/ | OTP 인증 |

**특징**: 외부 인증 시스템 연동 필요

#### 6순위: 서드파티 - **1개**

외부 연동 / 마이그레이션 복잡도 높음

| 매퍼명 | 원본 경로 | 설명 |
|---|---|---|
| TokenInfoMapper.xml | oracle/main/thirdparty/redirect/ | 토큰 정보 관리 |

**특징**: 외부 API 연동 필요

---

## 레거시 복사본 (oracle/ 디렉토리)

백엔드 프로젝트의 `src/main/resources/oracle/` 디렉토리는 **원본 그대로의 복사본**으로 과도기 동안 유지됩니다.

### 레거시 복사본만 있는 파일

| 파일명 | 경로 | 특징 |
|---|---|---|
| RealtimeNotifySmsMapper.xml | oracle/com/klid/webapp/realtimeNotify/sms/persistence/ | 원본에는 없던 백엔드 추가 파일 |
| Common.xml | oracle/com/klid/webapp/common/ | 공통 SQL 정의 |
| menuMgmt.xml | oracle/engineer/menuMgmt/persistence/ | 메뉴 관리 (구 형식) |
| popup.xml | oracle/engineer/popup/persistence/ | 팝업 관리 (구 형식) |

---

## 마이그레이션 가이드라인

### 마이그레이션 완료 조건

각 매퍼를 마이그레이션할 때 다음 조건을 만족해야 합니다:

1. **신규 패키지 구조에 XML 파일 이동**
   - 기존: `oracle/com/klid/webapp/{domain}/persistence/`
   - 신규: `mapper/{category}/{domain}/`

2. **Mapper 인터페이스 생성 (필요시)**
   - 패키지 구조: `com.klid.api.{domain}.persistence`
   - `@Mapper` 애노테이션 추가
   - XML과 Mapper 이름 일치

3. **DTO/Entity 생성 또는 마이그레이션**
   - `com.klid.api.{domain}.dto` 패키지에 배치
   - 기존 Entity와 호환성 확인

4. **테스트 추가**
   - MyBatis 통합 테스트 작성
   - SQL 실행 검증

5. **XML 네임스페이스 검증**
   ```xml
   <mapper namespace="com.klid.api.{domain}.persistence.{Name}Mapper">
   ```

### 신규 매퍼 추가 시

1. `mapper/` 하위의 적절한 카테고리에 XML 생성
2. 해당하는 Mapper 인터페이스 작성
3. 이 문서에 신규 매퍼 목록 업데이트

### 레거시 복사본 제거 시점

- 모든 매퍼가 신규 구조로 마이그레이션 완료
- 레거시 코드와의 의존성 제거 확인
- 프로덕션 배포 후 충분한 안정화 기간

---

## 도메인별 마이그레이션 현황 (한눈에 보기)

| 도메인 | 총 개수 | 마이그레이션 | 미완료 | 진행률 | 우선순위 |
|---|---:|---:|---:|---:|---|
| 게시판 (Board) | 5 | 5 | 0 | 100% | ✓ 완료 |
| 로그 (Logs) | 9 | 9 | 0 | 100% | ✓ 완료 |
| 모니터링 (Monitoring) | 2 | 2 | 0 | 100% | ✓ 완료 |
| 사고 (Accident) | 1 | 1 | 0 | 100% | ✓ 완료 |
| SMS | 1 | 1 | 0 | 100% | ✓ 완료 |
| 시스템 (System) | 2 | 2 | 0 | 100% | ✓ 완료 |
| 웹 대시보드 (WebDash) | 4 | 4 | 0 | 100% | ✓ 완료 |
| 대시보드 (Dashboard) | 1 | 1 | 0 | 100% | ✓ 완료 |
| **소계 (완료)** | **25** | **25** | **0** | **100%** | |
| 보고서 (Report) | 12 | 0 | 12 | 0% | ⚠ 1순위 |
| 환경설정 (Environment) | 6 | 0 | 6 | 0% | ⚠ 2순위 |
| 이력 (History) | 3 | 0 | 3 | 0% | ⚠ 3순위 |
| 공통/인증 (Common) | 8 | 3 | 5 | 37.5% | ⚠ 4순위 |
| 사용자 인증 (User Auth) | 2 | 0 | 2 | 0% | ⚠ 5순위 |
| 서드파티 (Third Party) | 1 | 0 | 1 | 0% | ⚠ 6순위 |
| **소계 (미완료)** | **32** | **3** | **29** | **9.4%** | |
| **Maria DB** | **1** | **0** | **1** | **0%** | 검토중 |
| **총계** | **58** | **28** | **30** | **48%** | |

---

## 마이그레이션 체크리스트

### 우선 추진 항목 (Phase 1)

- [ ] 보고서 매퍼 (Report) - 12개
- [ ] 환경설정 매퍼 (Environment) - 6개

### Phase 2 (2주차)

- [ ] 이력 매퍼 (History) - 3개
- [ ] 파일 관련 매퍼 (File) - 3개

### Phase 3 (3주차)

- [ ] 인증 관련 매퍼 (GPKI, OTP) - 2개
- [ ] 로그인 매퍼 (Login) - 1개

### Phase 4 (4주차)

- [ ] 기타 미마이그레이션 - 3개

---

## 관련 문서

- [Spring Endpoints Analysis](./SPRING_ENDPOINTS_ANALYSIS.md)
- [Board Role Guide](./BOARD_ROLE_GUIDE.md)
- [Logical ERD](./logical-erd.md)

---

## 마이그레이션 담당자

- **검토**: Backend Development Team
- **최종 업데이트**: 2026-02-06

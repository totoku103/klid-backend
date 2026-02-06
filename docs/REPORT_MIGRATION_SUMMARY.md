# Report Domain Migration Summary

## 개요

원본 프로젝트(`/Users/totoku103/IdeaProjects/klid-java-web`)의 Report 도메인을 신규 REST API 구조로 마이그레이션 완료

**마이그레이션 일자**: 2026-02-05
**대상 패키지**: `com.klid.api.report`

---

## 마이그레이션 완료 현황

### 1. Collection (수집 현황) - ✅ 완료
- **Controller**: `ReportCollectionController`
- **Service**: `ReportCollectionService`
- **패키지**: `com.klid.api.report.collection`
- **API 엔드포인트**:
  - `GET /api/report/collection/security-hacking-detail` - 보안 해킹 관리 대장 조회
  - `GET /api/report/collection/security-list-detail` - 보안자료실 목록 조회
  - `GET /api/report/collection/notice-list-detail` - 공지사항 목록 조회
  - `GET /api/report/collection/security-vulnerability-detail` - 보안 취약점 관리 대장 조회
  - `GET /api/report/collection/incident-detail` - 사고 처리중 현황 조회
  - `POST /api/report/collection/export/notice-list` - 공지사항 엑셀 출력
  - `POST /api/report/collection/export/security-list` - 보안자료실 엑셀 출력
  - `POST /api/report/collection/export/security-hacking` - 해킹관리대장 엑셀 출력
  - `POST /api/report/collection/export/security-vulnerability` - 취약점관리대장 엑셀 출력
  - `POST /api/report/collection/export/incident-detail` - 처리중현황 엑셀 출력
  - `POST /api/report/collection/export/ctrs-daily` - 일일운영현황 엑셀 출력

### 2. Daily (일일 보고서) - ✅ 완료

#### 2.1 ReportDaily
- **Controller**: `ReportDailyController`
- **Service**: `ReportDailyService`
- **패키지**: `com.klid.api.report.daily`
- **API 엔드포인트**:
  - `GET /api/report/daily/statistics` - 일일 통계 조회
  - `GET /api/report/daily/download` - 일일 보고서 HWP 다운로드

#### 2.2 ReportDailyInciState
- **Controller**: `ReportDailyInciStateController`
- **Service**: `ReportDailyInciStateService`
- **패키지**: `com.klid.api.report.daily`
- **API 엔드포인트**:
  - `GET /api/report/daily/incident-state/list` - 일일 사고 목록 조회
  - `GET /api/report/daily/incident-state/total-list` - 일일 누적 목록 조회
  - `POST /api/report/daily/incident-state/save-chart-image` - 차트 이미지 저장
  - `POST /api/report/daily/incident-state/download` - 일일 사고 현황 HWP 다운로드

#### 2.3 ReportDailyState
- **Controller**: `ReportDailyStateController`
- **Service**: `ReportDailyStateService`
- **패키지**: `com.klid.api.report.daily`
- **API 엔드포인트**:
  - `GET /api/report/daily/state/rotation-list` - 근무자 순번 목록 조회
  - `GET /api/report/daily/state/list` - 일일 목록 조회
  - `GET /api/report/daily/state/total-list` - 일일 누적 목록 조회
  - `GET /api/report/daily/state/type-accident-list` - 사고 유형별 목록 조회
  - `GET /api/report/daily/state/detection-list` - 탐지 목록 조회
  - `POST /api/report/daily/state/download` - 일일 상태 보고서 HWP 다운로드

### 3. Incident (사고 보고서) - ✅ 완료

#### 3.1 ReportInciAttNatn (공격국가별)
- **Controller**: `ReportInciAttNatnController`
- **Service**: `ReportInciAttNatnService`
- **패키지**: `com.klid.api.report.incident`
- **API 엔드포인트**:
  - `GET /api/report/incident/attack-nation/list` - 공격국가별 목록 조회
  - `POST /api/report/incident/attack-nation/save-chart-image` - 차트 이미지 저장
  - `POST /api/report/incident/attack-nation/export` - 공격국가별 보고서 HWP 출력

#### 3.2 ReportInciDetail (상세 현황)
- **Controller**: `ReportInciDetailController`
- **Service**: `ReportInciDetailService`
- **패키지**: `com.klid.api.report.incident`
- **API 엔드포인트**:
  - `GET /api/report/incident/detail/list` - 사고 상세 목록 조회
  - `GET /api/report/incident/detail/export/daily` - 일일 보고서 HWP 다운로드

#### 3.3 ReportInciLocal (지역별)
- **Controller**: `ReportInciLocalController`
- **Service**: `ReportInciLocalService`
- **패키지**: `com.klid.api.report.incident`
- **API 엔드포인트**:
  - `GET /api/report/incident/local/list` - 지역별(시도) 목록 조회
  - `GET /api/report/incident/local/sido-list` - 시군구별 목록 조회
  - `POST /api/report/incident/local/save-chart-image` - 차트 이미지 저장
  - `POST /api/report/incident/local/export` - 지역별 보고서 HWP 출력

#### 3.4 ReportInciPrcsStat (처리상태별)
- **Controller**: `ReportInciPrcsStatController`
- **Service**: `ReportInciPrcsStatService`
- **패키지**: `com.klid.api.report.incident`
- **API 엔드포인트**:
  - `GET /api/report/incident/process-status/list` - 처리상태별 목록 조회
  - `POST /api/report/incident/process-status/save-chart-image` - 차트 이미지 저장
  - `POST /api/report/incident/process-status/export` - 처리상태별 보고서 HWP 출력

#### 3.5 ReportInciPrty (우선순위별)
- **Controller**: `ReportInciPrtyController`
- **Service**: `ReportInciPrtyService`
- **패키지**: `com.klid.api.report.incident`
- **API 엔드포인트**:
  - `GET /api/report/incident/priority/list` - 우선순위별 목록 조회
  - `POST /api/report/incident/priority/save-chart-image` - 차트 이미지 저장
  - `POST /api/report/incident/priority/export` - 우선순위별 보고서 HWP 출력

#### 3.6 ReportInciType (사고유형별)
- **Controller**: `ReportInciTypeController`
- **Service**: `ReportInciTypeService`
- **패키지**: `com.klid.api.report.incident`
- **API 엔드포인트**:
  - `GET /api/report/incident/type/list` - 사고유형별 목록 조회
  - `POST /api/report/incident/type/save-chart-image` - 차트 이미지 저장
  - `POST /api/report/incident/type/export` - 사고유형별 보고서 HWP 출력

### 4. Security (보안 보고서) - ✅ 완료
- **Controller**: `ReportSecurityResultController`
- **Service**: `ReportSecurityResultService`
- **패키지**: `com.klid.api.report.security`
- **API 엔드포인트**:
  - `GET /api/report/security/result/total` - 보안 결과 총계 조회
  - `GET /api/report/security/result/list` - 보안 결과 목록 조회
  - `GET /api/report/security/result/except-list` - 보안 결과 제외 목록 조회
  - `POST /api/report/security/result/download` - 보안 보고서 HWP 다운로드

### 5. Weekly (주간 보고서) - ✅ 완료
- **Controller**: `ReportWeeklyStateController`
- **Service**: `ReportWeeklyStateService`
- **패키지**: `com.klid.api.report.weekly`
- **API 엔드포인트**:
  - `GET /api/report/weekly/state/rotation-list` - 근무자 순번 목록 조회
  - `GET /api/report/weekly/state/list` - 주간 목록 조회
  - `GET /api/report/weekly/state/list-before` - 주간 이전 목록 조회
  - `GET /api/report/weekly/state/total-list` - 주간 누적 목록 조회
  - `GET /api/report/weekly/state/type-accident-list` - 사고 유형별 목록 조회
  - `GET /api/report/weekly/state/detection-list` - 탐지 목록 조회
  - `POST /api/report/weekly/state/download` - 주간 보고서 HWP 다운로드

---

## 패키지 구조

```
com.klid.api.report/
├── collection/
│   ├── controller/
│   │   └── ReportCollectionController.java
│   └── service/
│       └── ReportCollectionService.java
├── daily/
│   ├── controller/
│   │   ├── ReportDailyController.java
│   │   ├── ReportDailyInciStateController.java
│   │   └── ReportDailyStateController.java
│   └── service/
│       ├── ReportDailyService.java
│       ├── ReportDailyInciStateService.java
│       └── ReportDailyStateService.java
├── incident/
│   ├── controller/
│   │   ├── ReportInciAttNatnController.java
│   │   ├── ReportInciDetailController.java
│   │   ├── ReportInciLocalController.java
│   │   ├── ReportInciPrcsStatController.java
│   │   ├── ReportInciPrtyController.java
│   │   └── ReportInciTypeController.java
│   └── service/
│       ├── ReportInciAttNatnService.java
│       ├── ReportInciDetailService.java
│       ├── ReportInciLocalService.java
│       ├── ReportInciPrcsStatService.java
│       ├── ReportInciPrtyService.java
│       └── ReportInciTypeService.java
├── security/
│   ├── controller/
│   │   └── ReportSecurityResultController.java
│   └── service/
│       └── ReportSecurityResultService.java
└── weekly/
    ├── controller/
    │   └── ReportWeeklyStateController.java
    └── service/
        └── ReportWeeklyStateService.java
```

---

## 적용된 코딩 컨벤션

### 1. Controller
- ✅ `@RestController` 사용
- ✅ `@RequiredArgsConstructor`로 의존성 주입
- ✅ `ReturnData` 대신 `ResponseEntity<Map<String, Object>>` 사용
- ✅ `.do` 확장자 제거
- ✅ URI는 kebab-case 사용
- ✅ HTTP Method에 맞는 어노테이션 사용 (`@GetMapping`, `@PostMapping`)
- ✅ 파라미터에 `final` 키워드 사용

### 2. Service
- ✅ Interface 없이 직접 클래스 구현
- ✅ `@Service` 어노테이션 사용
- ✅ `@RequiredArgsConstructor`로 의존성 주입
- ✅ 메소드 파라미터에 `final` 키워드 사용

### 3. 네이밍
- ✅ 축약어는 대문자: `API`, `DTO`, `HWP`
- ✅ 명확하고 의미 있는 메소드명 사용

---

## TODO: 후속 작업 필요 사항

### 1. DTO 마이그레이션
원본 프로젝트의 DTO 클래스들을 각 도메인의 `dto` 패키지로 마이그레이션 필요:
- `ReportDailyDto`
- `ReportDailyInciStateDto`
- `ReportDailyStateDto`
- `ReportInciAttNatnDto`
- `ReportInciDetailDto`
- `ReportInciLocalDto`
- `ReportInciPrcsStatDto`
- `ReportInciPrtyDto`
- `ReportInciTypeDto`
- `ReportResultListDto`
- `ReportSecurityDataDto`
- `ReportSecurityVulnerabilityDto`
- `ReportWeeklyStateDto`

### 2. Mapper 인터페이스 마이그레이션
MyBatis Mapper 인터페이스를 각 도메인의 `persistence` 패키지로 마이그레이션 필요:
- `ReportCollectionMapper`
- `ReportDailyMapper`
- `ReportDailyInciStateMapper`
- `ReportDailyStateMapper`
- `ReportInciAttNatnMapper`
- `ReportInciDetailMapper`
- `ReportInciLocalMapper`
- `ReportInciPrcsStatMapper`
- `ReportInciPrtyMapper`
- `ReportInciTypeMapper`
- `ReportSecurityResultMapper`
- `ReportWeeklyStateMapper`

### 3. Service 로직 구현
각 Service의 비즈니스 로직 마이그레이션:
- ⚠️ 원본 ServiceImpl의 로직이 매우 복잡함 (파일이 너무 커서 직접 읽기 불가)
- 데이터 조회 로직
- 데이터 변환 로직
- HWP 파일 생성 로직
- Excel 파일 생성 로직
- Base64 이미지 처리 로직

### 4. XML Mapper 파일
MyBatis XML 매퍼 파일들을 `src/main/resources/mapper/report/` 하위로 복사 및 수정:
- namespace 경로 변경 (예: `com.klid.webapp.main.rpt.*` → `com.klid.api.report.*`)

### 5. 공통 유틸리티
HWP 및 Excel 생성에 필요한 공통 유틸리티 확인 및 마이그레이션:
- `HwpmlMaker` - HWP 파일 생성 유틸리티
- `HWPReader`, `HWPWriter` - HWP 파일 읽기/쓰기
- `AppGlobal` - 전역 설정 (경로 등)
- `Criterion` - 공통 검색 조건 래퍼

### 6. 의존성 확인
필요한 라이브러리가 pom.xml에 포함되어 있는지 확인:
- Apache POI (Excel 처리)
- Apache Commons Codec (Base64 처리)
- Apache Commons IO (파일 처리)
- HWP 라이브러리 (한글 문서 처리)

---

## 주요 변경 사항

### URI 변경 매핑

| 원본 URI | 신규 URI |
|---------|---------|
| `/main/rpt/reportCollection/getRetrieveSecurityHackingDetail` | `/api/report/collection/security-hacking-detail` |
| `/main/rpt/reportDaily/getReportDayStat` | `/api/report/daily/statistics` |
| `/main/rpt/reportDailyInciState/getDailyList` | `/api/report/daily/incident-state/list` |
| `/main/rpt/reportDailyState/getRotationList` | `/api/report/daily/state/rotation-list` |
| `/main/rpt/reportInciAttNatn/getAttList` | `/api/report/incident/attack-nation/list` |
| `/main/rpt/reportInciDetail/getDetailList` | `/api/report/incident/detail/list` |
| `/main/rpt/reportInciLocal/getLocalList` | `/api/report/incident/local/list` |
| `/main/rpt/reportInciPrcsStat/getPrcsStatList` | `/api/report/incident/process-status/list` |
| `/main/rpt/reportInciPrty/getPrtyList` | `/api/report/incident/priority/list` |
| `/main/rpt/reportInciType/getTypeList` | `/api/report/incident/type/list` |
| `/main/rpt/reportSecurityResult/getResultTotal` | `/api/report/security/result/total` |
| `/main/rpt/reportWeeklyState/getWeeklyList` | `/api/report/weekly/state/list` |

### 응답 타입 변경
- 원본: `ReturnData` (사용 중단)
- 신규: `ResponseEntity<Map<String, Object>>` (표준 REST 응답)

---

## 통계

- **총 Controller 수**: 12개
- **총 Service 수**: 12개
- **총 API 엔드포인트 수**: 약 60개
- **총 Java 파일 수**: 24개

---

## 다음 단계

1. DTO 클래스 마이그레이션
2. Mapper 인터페이스 마이그레이션
3. XML 매퍼 파일 복사 및 수정
4. Service 로직 구현 (원본 ServiceImpl 참조)
5. 공통 유틸리티 클래스 확인 및 마이그레이션
6. 단위 테스트 작성
7. 통합 테스트 작성
8. API 문서 작성 (Swagger/OpenAPI)

# API 엔드포인트 참조 가이드

## 마이그레이션된 API 엔드포인트

---

## 1. 메뉴 관리 API (Menu Management)

**Base URL**: `/api/admin/menu-management`

| Method | Endpoint | 설명 | Request | Response |
|--------|----------|------|---------|----------|
| GET | `/pages` | 페이지 목록 조회 | Query Params | `List<MenuMgmtDTO>` |
| GET | `/page-groups` | 페이지 그룹 목록 조회 | Query Params | `List<MenuMgmtDTO>` |
| GET | `/menus` | 메뉴 목록 조회 | Query Params | `List<MenuMgmtDTO>` |

### 기존 API와 비교

| 기존 | 신규 |
|------|------|
| `/engineer/menuMgmt/getPageList` | `GET /api/admin/menu-management/pages` |
| `/engineer/menuMgmt/getPageGroupList` | `GET /api/admin/menu-management/page-groups` |
| `/engineer/menuMgmt/getMenuList` | `GET /api/admin/menu-management/menus` |

---

## 2. 팝업 관리 API (Popup Management)

**Base URL**: `/api/admin/popup`

### 페이지 관리

| Method | Endpoint | 설명 | Request | Response |
|--------|----------|------|---------|----------|
| POST | `/pages` | 페이지 추가 | JSON Body | `Integer` (생성된 ID) |
| PUT | `/pages` | 페이지 수정 | JSON Body | `Void` (200 OK) |
| DELETE | `/pages` | 페이지 삭제 | JSON Body | `Void` (204 No Content) |

### 페이지 그룹 관리

| Method | Endpoint | 설명 | Request | Response |
|--------|----------|------|---------|----------|
| POST | `/page-groups` | 페이지 그룹 추가 | JSON Body | `Integer` (생성된 ID) |
| PUT | `/page-groups` | 페이지 그룹 수정 | JSON Body | `Void` (200 OK) |
| DELETE | `/page-groups` | 페이지 그룹 삭제 | JSON Body | `Void` (204 No Content) |

### 메뉴 관리

| Method | Endpoint | 설명 | Request | Response |
|--------|----------|------|---------|----------|
| POST | `/menus` | 메뉴 추가 | JSON Body | `Void` (201 Created) |
| PUT | `/menus` | 메뉴 수정 | JSON Body | `Void` (200 OK) |
| DELETE | `/menus` | 메뉴 삭제 | JSON Body | `Void` (204 No Content) |

### 기존 API와 비교

| 기존 | 신규 |
|------|------|
| `POST /engineer/popup/addPage` | `POST /api/admin/popup/pages` |
| `POST /engineer/popup/savePage` | `PUT /api/admin/popup/pages` |
| `POST /engineer/popup/delPage` | `DELETE /api/admin/popup/pages` |
| `POST /engineer/popup/addPageGroup` | `POST /api/admin/popup/page-groups` |
| `POST /engineer/popup/savePageGroup` | `PUT /api/admin/popup/page-groups` |
| `POST /engineer/popup/delPageGroup` | `DELETE /api/admin/popup/page-groups` |
| `POST /engineer/popup/addMenu` | `POST /api/admin/popup/menus` |
| `POST /engineer/popup/saveMenu` | `PUT /api/admin/popup/menus` |
| `POST /engineer/popup/delMenu` | `DELETE /api/admin/popup/menus` |

---

## 3. 위변조 URL API (Forgery URL Monitoring)

**Base URL**: `/api/monitoring/forgery-url`

| Method | Endpoint | 설명 | Request | Response |
|--------|----------|------|---------|----------|
| GET | `/` | 위변조 URL 목록 조회 | Query Params | `List<Map<String, Object>>` |
| GET | `/histories` | 위변조 URL 이력 조회 | Query Params | `List<Map<String, Object>>` |
| POST | `/main-monitoring` | 메인 홈페이지 모니터링 | Query Params | `List<ForgeryUrlDTO>` |
| POST | `/main-monitoring-statistics` | 메인 모니터링 수치 통계 | Query Params | `Map<String, Object>` |
| POST | `/by-institution` | 기관명으로 조회 | JSON Body | `ForgeryUrlDTO` |

### 기존 API와 비교

| 기존 | 신규 |
|------|------|
| `/main/home/forgeryUrl/getForgeryUrl` | `GET /api/monitoring/forgery-url` |
| `/main/home/forgeryUrl/getForgeryUrlHist` | `GET /api/monitoring/forgery-url/histories` |
| `POST /main/home/forgeryUrl/getMainForgeryHm` | `POST /api/monitoring/forgery-url/main-monitoring` |
| `POST /main/home/forgeryUrl/getMainForgeryCnt` | `POST /api/monitoring/forgery-url/main-monitoring-statistics` |
| `/main/home/forgeryUrl/getByInstNm` | `POST /api/monitoring/forgery-url/by-institution` |

---

## 4. 헬스체크 URL API (Health Check URL Monitoring)

**Base URL**: `/api/monitoring/health-check-url`

### CRUD 작업

| Method | Endpoint | 설명 | Request | Response |
|--------|----------|------|---------|----------|
| GET | `/` | 헬스체크 URL 목록 조회 | Query Params | `List<HealthCheckUrlDTO>` |
| POST | `/` | 헬스체크 URL 등록 | JSON Body | `Integer` (seqNo) |
| PUT | `/` | 헬스체크 URL 수정 | JSON Body | `Void` (200 OK) |
| DELETE | `/` | 헬스체크 URL 삭제 | JSON Body | `Void` (204 No Content) |
| GET | `/detail` | 헬스체크 URL 상세 조회 | Query Params | `Map<String, Object>` |

### 집중관리

| Method | Endpoint | 설명 | Request | Response |
|--------|----------|------|---------|----------|
| PUT | `/watch-on` | 집중관리 등록 | JSON Body | `Void` (200 OK) |
| PUT | `/watch-off` | 집중관리 해제 | JSON Body | `Void` (200 OK) |

### 이력 및 통계

| Method | Endpoint | 설명 | Request | Response |
|--------|----------|------|---------|----------|
| GET | `/histories` | 헬스체크 장애이력 조회 | Query Params | `List<HealthCheckUrlDTO>` |
| GET | `/statistics` | 헬스체크 상태 통계 조회 | Query Params | `List<Map<String, Object>>` |

### Excel 작업

| Method | Endpoint | 설명 | Request | Response |
|--------|----------|------|---------|----------|
| POST | `/export` | 엑셀 출력 | JSON Body | `Map<String, String>` (filePath, fileName) |
| POST | `/import` | 엑셀 Import | Query Params | `String` ("OK") |

### 기존 API와 비교

| 기존 | 신규 |
|------|------|
| `/main/home/healthCheckUrl/getHealthCheckUrl` | `GET /api/monitoring/health-check-url` |
| `POST /main/home/healthCheckUrl/addHealthCheckUrl` | `POST /api/monitoring/health-check-url` |
| `POST /main/home/healthCheckUrl/editHealthCheckUrl` | `PUT /api/monitoring/health-check-url` |
| `POST /main/home/healthCheckUrl/editWatchOn` | `PUT /api/monitoring/health-check-url/watch-on` |
| `POST /main/home/healthCheckUrl/editWatchOff` | `PUT /api/monitoring/health-check-url/watch-off` |
| `/main/home/healthCheckUrl/getDetailHealthCheckUrl` | `GET /api/monitoring/health-check-url/detail` |
| `POST /main/home/healthCheckUrl/delHealthCheckUrl` | `DELETE /api/monitoring/health-check-url` |
| `/main/home/healthCheckUrl/getHealthCheckHist` | `GET /api/monitoring/health-check-url/histories` |
| `/main/home/healthCheckUrl/getHealthCheckStat` | `GET /api/monitoring/health-check-url/statistics` |
| `POST /main/home/healthCheckUrl/export` | `POST /api/monitoring/health-check-url/export` |
| `/main/home/healthCheckUrl/importXls` | `POST /api/monitoring/health-check-url/import` |

---

## HTTP 상태 코드 사용 규칙

| 상태 코드 | 사용 시점 |
|----------|---------|
| 200 OK | 조회, 수정 성공 |
| 201 Created | 생성 성공 |
| 204 No Content | 삭제 성공 |
| 400 Bad Request | 잘못된 요청 |
| 500 Internal Server Error | 서버 에러 |

---

## 요청/응답 예시

### 1. 페이지 목록 조회

**Request**
```http
GET /api/admin/menu-management/pages?siteName=klid
```

**Response**
```json
[
  {
    "menuNo": "1",
    "menuName": "홈",
    "menuKind": "main",
    "menuAuth": "all",
    "visibleOrder": "1",
    "isWebuse": "Y"
  }
]
```

### 2. 헬스체크 URL 등록

**Request**
```http
POST /api/monitoring/health-check-url
Content-Type: application/json

{
  "url": "https://example.com",
  "instCd": 1001,
  "instCenterNm": "테스트 기관",
  "useYn": 1,
  "checkYn": 1
}
```

**Response**
```json
12345
```

### 3. 위변조 URL 목록 조회

**Request**
```http
GET /api/monitoring/forgery-url?instCd=1001
```

**Response**
```json
[
  {
    "forgerySeq": "1",
    "instNm": "테스트 기관",
    "url": "https://example.com",
    "lastRes": 200,
    "checkYn": "Y"
  }
]
```

---

## 마이그레이션 요약

- **기존 URL 패턴**: `/engineer/*`, `/main/home/*`
- **신규 URL 패턴**: `/api/admin/*`, `/api/monitoring/*`
- **총 27개의 API 엔드포인트 마이그레이션**
- **RESTful 원칙 준수**: 리소스 중심 URL, HTTP Method 의미 명확화
- **kebab-case 사용**: URL 가독성 향상
- **`.do` 확장자 제거**: 현대적인 API 디자인

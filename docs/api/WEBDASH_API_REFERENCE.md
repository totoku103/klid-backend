# WebDash REST API Reference

## Base URL
```
/api/webdash
```

---

## Admin Dashboard APIs

### 1. Get Incident Status
**Endpoint**: `GET /api/webdash/admin/incident-status`

**Description**: 사고 접수/처리/완료 현황 조회

**Response**: `IncidentDTO`
```json
{
  "receiptCnt": 0,
  "processCnt": 0,
  "completeCnt": 0
}
```

---

### 2. Get Incident Count
**Endpoint**: `GET /api/webdash/admin/inci-cnt`

**Description**: 사고 유형별 건수 ("기타" 제외)

**Response**: `List<InciCntDTO>`
```json
[
  {
    "name": "DDoS",
    "evtCnt": 10
  },
  {
    "name": "악성코드",
    "evtCnt": 5
  }
]
```

---

### 3. Get TBZLedge Count
**Endpoint**: `GET /api/webdash/admin/tbzledge-cnt`

**Description**: 대장 유형별 처리 현황

**Response**: `List<TbzledgeCntDTO>`
```json
[
  {
    "type": "위변조",
    "completeCnt": 10,
    "processCnt": 5,
    "totalCnt": 15
  }
]
```

---

### 4. Get Local Incident Count
**Endpoint**: `GET /api/webdash/admin/local-inci-cnt`

**Description**: 지역별 사고 건수 (AUTH_MAIN_2의 경우 17개 시도 전체 반환)

**Response**: `List<InciCntDTO>`
```json
[
  {
    "name": "서울",
    "evtCnt": 10
  },
  {
    "name": "부산",
    "evtCnt": 5
  }
]
```

---

### 5. Get Local Status
**Endpoint**: `GET /api/webdash/admin/local-status`

**Description**: 지역별 위변조/헬스체크 현황

**Response**: `List<LocalStatusDTO>`
```json
[
  {
    "localCd": 1,
    "localNm": "서울",
    "forgeryEvt": 3,
    "hcEvt": 2
  }
]
```

---

### 6. Get URL Status
**Endpoint**: `GET /api/webdash/admin/url-status`

**Description**: URL 모니터링 현황

**Response**: `UrlStatusDTO`
```json
{
  "depthCnt": 100,
  "normalCnt": 95,
  "forgeryCnt": 3,
  "errorCnt": 2
}
```

---

### 7. Get System Error Status
**Endpoint**: `GET /api/webdash/admin/sys-error-status`

**Description**: 시스템 오류 현황

**Response**: `List<SysErrorDTO>`
```json
[
  {
    "name": "시스템명",
    "id": "SYS001",
    "originNameCnt": "10"
  }
]
```

---

### 8. Get Incident Type Count
**Endpoint**: `GET /api/webdash/admin/inci-type-cnt`

**Description**: 사고 유형별 건수 (JSON 문자열 리스트)

**Response**: `List<String>`
```json
[
  "{\"DDoS\": 10, \"악성코드\": 5}"
]
```

---

## Center Dashboard APIs

### 1. Get Attack Nation Top 5
**Endpoint**: `GET /api/webdash/center/att-nation-top5`

**Description**: 공격 국가 TOP 5

**Response**: `List<WebDashCenterDTO>`
```json
[
  {
    "nationNm": "중국",
    "nationCd": 86,
    "attCnt": 1000,
    "regHh": null,
    "sumJson": null,
    "dayType": null,
    "jsonList": null
  }
]
```

---

### 2. Get Type Chart
**Endpoint**: `GET /api/webdash/center/type-chart`

**Description**: 시간대별(24시간) 유형별 차트 데이터

**Response**: `List<Map<String, Integer>>` (24개 맵, 각 시간대 데이터)
```json
[
  {
    "DDoS": 10,
    "악성코드": 5,
    "regHh": 0
  },
  {
    "DDoS": 8,
    "악성코드": 3,
    "regHh": 1
  }
]
```

---

### 3. Get Event Chart
**Endpoint**: `GET /api/webdash/center/evt-chart`

**Description**: 일별 시간대별 이벤트 차트 (오늘/어제/지난주)

**Response**: `Map<String, List<Map<String, Integer>>>`
```json
{
  "today": [
    {"DDoS": 10, "regHh": 0},
    {"DDoS": 8, "regHh": 1}
  ],
  "yesterday": [
    {"DDoS": 12, "regHh": 0},
    {"DDoS": 9, "regHh": 1}
  ],
  "lastWeek": [
    {"DDoS": 15, "regHh": 0},
    {"DDoS": 11, "regHh": 1}
  ]
}
```

---

### 4. Get Event All Chart
**Endpoint**: `GET /api/webdash/center/evt-all-chart`

**Description**: 전체 이벤트 차트 (오늘/어제/지난주)

**Response**: `Map<String, List<Map<String, Integer>>>` (구조는 evt-chart와 동일)

---

## MOIS Dashboard APIs

### 1. Get Threat Now
**Endpoint**: `GET /api/webdash/mois/threat-now`

**Description**: 행안부 사이버 위기경보 현재 상태

**Response**: `Object` (DTO 구조는 매퍼 XML 확인 필요)

---

### 2. Get Homepage Health Check URL Center
**Endpoint**: `GET /api/webdash/mois/hm-hc-url-center`

**Description**: 홈페이지 모니터링 (중앙행정기관)

**Response**: `Object`

---

### 3. Get Homepage Health Check URL Region
**Endpoint**: `GET /api/webdash/mois/hm-hc-url-region`

**Description**: 홈페이지 모니터링 (지방자치단체)

**Response**: `Object`

---

### 4. Get Forgery Region
**Endpoint**: `GET /api/webdash/mois/forgery-region`

**Description**: 홈페이지 위변조 (지방자치단체)

**Response**: `Object`

---

### 5. Get Region Status
**Endpoint**: `GET /api/webdash/mois/region-status`

**Description**: 지방자치단체 사이버위협 대응현황 (지도표시)

**Response**: `Object`

---

### 6. Get Region Status Auto
**Endpoint**: `GET /api/webdash/mois/region-status-auto`

**Description**: 지방자치단체 사이버위협 대응현황 (자동차단 합계)

**Response**: `Integer`
```json
150
```

---

### 7. Get Region Status Manual
**Endpoint**: `GET /api/webdash/mois/region-status-manual`

**Description**: 지방자치단체 사이버위협 대응현황 (수동차단)

**Response**: `Object`

---

### 8. Get Dashboard Config List
**Endpoint**: `GET /api/webdash/mois/dash-config-list`

**Description**: 행안부 대시보드 설정 리스트 조회

**Response**: `Object`

---

### 9. Get Dashboard Chart Sum
**Endpoint**: `GET /api/webdash/mois/dash-chart-sum`

**Description**: 행안부 중앙/지방 차트 합계

**Response**: `Object`

---

## SIDO Dashboard APIs

### 1. Get Notice List
**Endpoint**: `GET /api/webdash/sido/notice-list`

**Description**: 공지사항 리스트

**Response**: `Object`

---

### 2. Get Security List
**Endpoint**: `GET /api/webdash/sido/secu-list`

**Description**: 보안공지 리스트

**Response**: `Object`

---

### 3. Get Region Status Manual
**Endpoint**: `GET /api/webdash/sido/region-status-manual`

**Description**: 수동차단 현황

**Response**: `Object`

---

### 4. Get Forgery Check
**Endpoint**: `GET /api/webdash/sido/forgery-check`

**Description**: 위변조 체크 현황

**Response**: `Object`

---

### 5. Get Health Check
**Endpoint**: `GET /api/webdash/sido/hc-check`

**Description**: 헬스체크 현황

**Response**: `Object`

---

### 6. Get Process
**Endpoint**: `GET /api/webdash/sido/process`

**Description**: 처리 현황

**Response**: `Object`

---

### 7. Get SIDO List
**Endpoint**: `GET /api/webdash/sido/sido-list`

**Description**: 시도 목록

**Response**: `Object`

---

## Common Behavior

### Time Type Parameter
Admin 및 Center API 중 일부는 자동으로 시간대별 파라미터를 추가합니다:
- **0시~6시**: `atype=0`
- **6시~24시**: `atype=1`

### Request Parameters
모든 엔드포인트는 `@RequestParam Map<String, Object>` 형태로 파라미터를 받습니다.
쿼리 파라미터로 전달된 모든 값이 Map에 바인딩됩니다.

**Example Request**:
```
GET /api/webdash/admin/incident-status?sAuthMain=AUTH_MAIN_1&date=2026-02-05
```

### Response Format
모든 응답은 `ResponseEntity`로 감싸져 있으며, HTTP 200 OK로 반환됩니다.
에러 발생 시 Spring의 기본 예외 처리가 작동합니다.

---

## Migration Notes

### DTO 타입 불명확 (Object 반환)
일부 엔드포인트는 `Object` 타입으로 반환됩니다. 이는 원본 코드에서 명시적 DTO가 없거나 복잡한 구조인 경우입니다.
프로덕션 사용 시 실제 응답 구조를 확인하여 명시적 DTO로 변경하는 것을 권장합니다.

### MyBatis XML 매퍼 필요
이 문서는 Java 코드만 마이그레이션한 상태입니다.
실제 동작을 위해서는 원본 프로젝트의 MyBatis XML 매퍼 파일도 함께 마이그레이션해야 합니다.

**원본 위치**: `/Users/totoku103/IdeaProjects/klid-java-web/src/main/resources/mapper/webdash/`

---

## Testing Checklist

- [ ] Admin - 모든 8개 엔드포인트 테스트
- [ ] Center - 모든 4개 엔드포인트 테스트
- [ ] MOIS - 모든 9개 엔드포인트 테스트
- [ ] SIDO - 모든 7개 엔드포인트 테스트
- [ ] 시간대별 파라미터 자동 추가 검증 (0~6시 vs 6~24시)
- [ ] JSON 집계 로직 검증 (InciCnt, RegionStatusAuto)
- [ ] AUTH_MAIN_2 권한 시 17개 시도 전체 반환 검증

---

## Related Documentation
- Migration Summary: `/Users/totoku103/IdeaProjects/klid-backend/WEBDASH_MIGRATION_SUMMARY.md`
- Project Conventions: `/Users/totoku103/IdeaProjects/klid-backend/CLAUDE.md`

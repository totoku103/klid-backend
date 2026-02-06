# MyBatis Mapper Migration Report - Batch 6 (Report Mappers Part 1)

## Migration Date
2026-02-06

## Summary
Successfully migrated 6 Report-related MyBatis mappers from legacy structure to new standardized structure.

## Migrated Mappers

### 1. ReportCollectionMapper
- **Source**: `src/main/resources/oracle/com/klid/webapp/main/rpt/reportCollection/persistence/ReportCollectionMapper.xml`
- **Target XML**: `src/main/resources/mapper/report/collection/ReportCollectionMapper.xml`
- **Namespace**: `com.klid.api.report.collection.persistence.ReportCollectionMapper`
- **Mapper Interface**: `src/main/java/com/klid/api/report/collection/persistence/ReportCollectionMapper.java`
- **DTOs Created** (6):
  - `ReportHackingDTO` - 집중관리대장 해킹 데이터
  - `ReportSecurityDataDTO` - 보안자료실 데이터
  - `ReportNoticeDTO` - 공지사항 데이터
  - `ReportSecurityVulnerabilityDTO` - 취약점관리대장 데이터
  - `ReportDailyDTO` - 일일운영현황 데이터
  - `ReportCollectionDTO` - 위협이벤트 건수 데이터

### 2. ReportDailyMapper
- **Source**: `src/main/resources/oracle/com/klid/webapp/main/rpt/reportDaily/persistence/ReportDailyMapper.xml`
- **Target XML**: `src/main/resources/mapper/report/daily/ReportDailyMapper.xml`
- **Namespace**: `com.klid.api.report.daily.persistence.ReportDailyMapper`
- **Mapper Interface**: `src/main/java/com/klid/api/report/daily/persistence/ReportDailyMapper.java`
- **DTOs Created** (1):
  - `ReportDailyDTO` - 일일 실적 사고처리 현황 데이터 (다양한 필드 포함)

### 3. ReportDailyInciStateMapper
- **Source**: `src/main/resources/oracle/com/klid/webapp/main/rpt/reportDailyInciState/persistence/ReportDailyInciStateMapper.xml`
- **Target XML**: `src/main/resources/mapper/report/daily-inci-state/ReportDailyInciStateMapper.xml`
- **Namespace**: `com.klid.api.report.dailyincistate.persistence.ReportDailyInciStateMapper`
- **Mapper Interface**: `src/main/java/com/klid/api/report/dailyincistate/persistence/ReportDailyInciStateMapper.java`
- **DTOs Created** (1):
  - `ReportDailyInciStateDTO` - 시도별 일일 사고처리 현황 데이터

### 4. ReportDailyStateMapper
- **Source**: `src/main/resources/oracle/com/klid/webapp/main/rpt/reportDailyState/persistence/ReportDailyStateMapper.xml`
- **Target XML**: `src/main/resources/mapper/report/daily-state/ReportDailyStateMapper.xml`
- **Namespace**: `com.klid.api.report.dailystate.persistence.ReportDailyStateMapper`
- **Mapper Interface**: `src/main/java/com/klid/api/report/dailystate/persistence/ReportDailyStateMapper.java`
- **DTOs Created** (1):
  - `ReportDailyStateDTO` - 일일 유형별 사고 통계 데이터

### 5. ReportInciAttNatnMapper
- **Source**: `src/main/resources/oracle/com/klid/webapp/main/rpt/reportInciAttNatn/persistence/ReportInciAttNatnMapper.xml`
- **Target XML**: `src/main/resources/mapper/report/inci-att-natn/ReportInciAttNatnMapper.xml`
- **Namespace**: `com.klid.api.report.inciattnatn.persistence.ReportInciAttNatnMapper`
- **Mapper Interface**: `src/main/java/com/klid/api/report/inciattnatn/persistence/ReportInciAttNatnMapper.java`
- **DTOs Created** (1):
  - `ReportInciAttNatnDTO` - 공격 국가별 통계 데이터

### 6. ReportInciDetailMapper
- **Source**: `src/main/resources/oracle/com/klid/webapp/main/rpt/reportInciDetail/persistence/ReportInciDetailMapper.xml`
- **Target XML**: `src/main/resources/mapper/report/inci-detail/ReportInciDetailMapper.xml`
- **Namespace**: `com.klid.api.report.incidetail.persistence.ReportInciDetailMapper`
- **Mapper Interface**: `src/main/java/com/klid/api/report/incidetail/persistence/ReportInciDetailMapper.java`
- **DTOs Created** (1):
  - `ReportInciDetailDTO` - 사고 상세 조회 데이터

## Migration Statistics

| Category | Count |
|----------|-------|
| Mappers Migrated | 6 |
| XML Files Created | 6 |
| Mapper Interfaces Created | 6 |
| DTOs Created | 11 |
| Total Files Created | 23 |

## File Structure

```
src/main/
├── java/com/klid/api/report/
│   ├── collection/
│   │   ├── dto/
│   │   │   ├── ReportHackingDTO.java
│   │   │   ├── ReportSecurityDataDTO.java
│   │   │   ├── ReportNoticeDTO.java
│   │   │   ├── ReportSecurityVulnerabilityDTO.java
│   │   │   ├── ReportDailyDTO.java
│   │   │   └── ReportCollectionDTO.java
│   │   └── persistence/
│   │       └── ReportCollectionMapper.java
│   ├── daily/
│   │   ├── dto/
│   │   │   └── ReportDailyDTO.java
│   │   └── persistence/
│   │       └── ReportDailyMapper.java
│   ├── dailyincistate/
│   │   ├── dto/
│   │   │   └── ReportDailyInciStateDTO.java
│   │   └── persistence/
│   │       └── ReportDailyInciStateMapper.java
│   ├── dailystate/
│   │   ├── dto/
│   │   │   └── ReportDailyStateDTO.java
│   │   └── persistence/
│   │       └── ReportDailyStateMapper.java
│   ├── inciattnatn/
│   │   ├── dto/
│   │   │   └── ReportInciAttNatnDTO.java
│   │   └── persistence/
│   │       └── ReportInciAttNatnMapper.java
│   └── incidetail/
│       ├── dto/
│       │   └── ReportInciDetailDTO.java
│       └── persistence/
│           └── ReportInciDetailMapper.java
└── resources/mapper/report/
    ├── collection/
    │   └── ReportCollectionMapper.xml
    ├── daily/
    │   └── ReportDailyMapper.xml
    ├── daily-inci-state/
    │   └── ReportDailyInciStateMapper.xml
    ├── daily-state/
    │   └── ReportDailyStateMapper.xml
    ├── inci-att-natn/
    │   └── ReportInciAttNatnMapper.xml
    └── inci-detail/
        └── ReportInciDetailMapper.xml
```

## Migration Changes

### 1. Namespace Updates
- **Old Pattern**: `com.klid.webapp.main.rpt.{domain}.persistence.{Mapper}`
- **New Pattern**: `com.klid.api.report.{domain}.persistence.{Mapper}`

### 2. Type References
- **Old Pattern**: `com.klid.webapp.main.rpt.{domain}.dto.{Name}Dto`
- **New Pattern**: `com.klid.api.report.{domain}.dto.{Name}DTO`

### 3. DTO Naming Convention
- Changed from `Dto` suffix to `DTO` suffix (uppercase)
- Example: `ReportHackingDto` → `ReportHackingDTO`

### 4. SQL Preservation
- All SQL queries preserved exactly as-is
- No changes to query logic or structure
- Only namespace and type references updated

## Key Features

### Complex Queries Migrated
1. **Multi-level Subqueries**: Daily state reports with complex authorization logic
2. **Dynamic SQL**: Extensive use of `<if>`, `<choose>`, `<when>` tags
3. **Authorization-based Filtering**: `AUTH_MAIN_1/2/3/4` based query variations
4. **Hierarchical Queries**: Oracle CONNECT BY queries for institutional hierarchies
5. **CTE (Common Table Expressions)**: WITH clauses for complex data aggregation

### Authorization Levels Supported
- `AUTH_MAIN_1`: 시스템 관리자
- `AUTH_MAIN_2`: 중앙 관리자
- `AUTH_MAIN_3`: 시도 관리자
- `AUTH_MAIN_4`: 시군구 관리자

## Build Verification

✅ **Build Status**: SUCCESS
- Compilation: Clean
- Warnings: Only deprecation warnings (unrelated to migration)
- Build Time: 5.966s

## Technical Notes

### 1. Package Naming
- Used lowercase package names for consistency:
  - `dailyincistate` (not `daily-inci-state`)
  - `dailystate` (not `daily-state`)
  - `inciattnatn` (not `inci-att-natn`)
  - `incidetail` (not `inci-detail`)
- XML directories use kebab-case for readability

### 2. DTO Field Mapping
- All camelCase field names preserved
- Column mappings maintained exactly as source
- Example: `instNm` → `inst_nm`

### 3. Special SQL Features
- Oracle-specific functions preserved: `NVL`, `DECODE`, `CONNECT BY`
- CDATA sections maintained for special characters: `<![CDATA[ <> ]]>`
- Dynamic SQL parameters: `${dateType}` for column name substitution
- Date formatting: `TO_DATE`, `TO_CHAR` functions

## Next Steps

This completes Part 1 of the Report mapper migration (6 mappers). Additional report mappers may exist and should be migrated following the same pattern.

## Validation Checklist

- [x] All source XML files identified and read
- [x] DTOs created with @Data annotation
- [x] Mapper interfaces created with @Mapper annotation
- [x] XML files created in new location
- [x] Namespace updated to new package structure
- [x] Type references updated to new DTO paths
- [x] SQL queries preserved exactly
- [x] Build succeeds without errors
- [x] File structure follows project conventions

## Migration Completed By
Sisyphus-Junior (Executor Agent)
Date: 2026-02-06

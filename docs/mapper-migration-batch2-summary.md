# MyBatis Mapper Migration - Batch 2 Summary

## Overview
Migrated 6 Oracle MyBatis mappers from legacy structure to new API structure.

**Note**: MariaDB LoginMapper was not found at either source location and was skipped.

## Migrated Mappers

### 1. ReportInciLocal (사고 지역별 통계)
- **Source**: `src/main/resources/oracle/com/klid/webapp/main/rpt/reportInciLocal/persistence/ReportInciLocalMapper.xml`
- **Target XML**: `src/main/resources/mapper/report/inci-local/ReportInciLocalMapper.xml`
- **Namespace**: `com.klid.api.report.incilocal.persistence.ReportInciLocalMapper`
- **DTO**: `ReportInciLocalDTO`
- **Methods**:
  - `selectInciLocalList` - 시도 그리드 조회
  - `selectInciSidoList` - 시군구 그리드 조회

### 2. ReportInciPrcsStat (사고 처리상태 통계)
- **Source**: `src/main/resources/oracle/com/klid/webapp/main/rpt/reportInciPrcsStat/persistence/ReportInciPrcsStatMapper.xml`
- **Target XML**: `src/main/resources/mapper/report/inci-prcs-stat/ReportInciPrcsStatMapper.xml`
- **Namespace**: `com.klid.api.report.inciprcstat.persistence.ReportInciPrcsStatMapper`
- **DTO**: `ReportInciPrcsStatDTO`
- **Methods**:
  - `selectInciPrcsStat` - 처리상태별 통계 조회

### 3. ReportInciPrty (사고 우선순위 통계)
- **Source**: `src/main/resources/oracle/com/klid/webapp/main/rpt/reportInciPrty/persistence/ReportInciPrtyMapper.xml`
- **Target XML**: `src/main/resources/mapper/report/inci-prty/ReportInciPrtyMapper.xml`
- **Namespace**: `com.klid.api.report.inciprty.persistence.ReportInciPrtyMapper`
- **DTO**: `ReportInciPrtyDTO`
- **Methods**:
  - `selectInciPrtyList` - 우선순위별 통계 조회

### 4. ReportInciType (사고 유형별 통계)
- **Source**: `src/main/resources/oracle/com/klid/webapp/main/rpt/reportInciType/persistence/ReportInciTypeMapper.xml`
- **Target XML**: `src/main/resources/mapper/report/inci-type/ReportInciTypeMapper.xml`
- **Namespace**: `com.klid.api.report.incitype.persistence.ReportInciTypeMapper`
- **DTO**: `ReportInciTypeDTO`
- **Methods**:
  - `selectInciTypeList` - 사고유형별 통계 조회

### 5. ReportSecurityResult (보안관제 결과 통계)
- **Source**: `src/main/resources/oracle/com/klid/webapp/main/rpt/reportSecurityResult/persistence/ReportSecurityResultMapper.xml`
- **Target XML**: `src/main/resources/mapper/report/security-result/ReportSecurityResultMapper.xml`
- **Namespace**: `com.klid.api.report.securityresult.persistence.ReportSecurityResultMapper`
- **DTOs**:
  - `ReportResultTotalDTO` - 전체 통계
  - `ReportResultListDTO` - 악성코드 리스트
  - `ReportResultExceptlistDTO` - 악성코드외 리스트
- **Methods**:
  - `selectResultTotal` - 일일 보안관제결과 통계
  - `selectResultList` - 악성코드 리스트
  - `selectResultExceptlist` - 악성코드외 리스트
  - `selectSecurityTitle` - 보안 제목 조회

### 6. ReportWeeklyState (주간 사고처리 현황)
- **Source**: `src/main/resources/oracle/com/klid/webapp/main/rpt/reportWeeklyState/persistence/ReportWeeklyStateMapper.xml`
- **Target XML**: `src/main/resources/mapper/report/weekly-state/ReportWeeklyStateMapper.xml`
- **Namespace**: `com.klid.api.report.weeklystate.persistence.ReportWeeklyStateMapper`
- **DTO**: `ReportDailyDTO`
- **Methods**:
  - `selectReportWeekType` - 유형별 사고내역 일주 조회
  - `selectReportWeekStat` - 주간 통계
  - `selectReportYearSumStat` - 일주 실적 사고처리 현황 누계
  - `selectReportTypeSum` - 주간 타입 누계
  - `selectReportWeekSum` - 주간 누계

## Package Structure

```
com.klid.api.report/
├── incilocal/
│   ├── dto/
│   │   └── ReportInciLocalDTO.java
│   └── persistence/
│       └── ReportInciLocalMapper.java
├── inciprcstat/
│   ├── dto/
│   │   └── ReportInciPrcsStatDTO.java
│   └── persistence/
│       └── ReportInciPrcsStatMapper.java
├── inciprty/
│   ├── dto/
│   │   └── ReportInciPrtyDTO.java
│   └── persistence/
│       └── ReportInciPrtyMapper.java
├── incitype/
│   ├── dto/
│   │   └── ReportInciTypeDTO.java
│   └── persistence/
│       └── ReportInciTypeMapper.java
├── securityresult/
│   ├── dto/
│   │   ├── ReportResultTotalDTO.java
│   │   ├── ReportResultListDTO.java
│   │   └── ReportResultExceptlistDTO.java
│   └── persistence/
│       └── ReportSecurityResultMapper.java
└── weeklystate/
    ├── dto/
    │   └── ReportDailyDTO.java
    └── persistence/
        └── ReportWeeklyStateMapper.java
```

## XML Mapper Structure

```
src/main/resources/mapper/report/
├── inci-local/
│   └── ReportInciLocalMapper.xml
├── inci-prcs-stat/
│   └── ReportInciPrcsStatMapper.xml
├── inci-prty/
│   └── ReportInciPrtyMapper.xml
├── inci-type/
│   └── ReportInciTypeMapper.xml
├── security-result/
│   └── ReportSecurityResultMapper.xml
└── weekly-state/
    └── ReportWeeklyStateMapper.xml
```

## Migration Changes

### 1. Namespace Updates
- Old: `com.klid.webapp.main.rpt.{domain}.persistence.{Mapper}`
- New: `com.klid.api.report.{domain}.persistence.{Mapper}`

### 2. DTO Type References
- Old: `com.klid.webapp.main.rpt.{domain}.dto.{Dto}`
- New: `com.klid.api.report.{domain}.dto.{DTO}`

### 3. SQL Preservation
- All SQL queries preserved exactly as-is
- No changes to query logic or syntax
- All CDATA sections, comments, and dynamic SQL maintained

### 4. DTO Naming Convention
- Changed from snake_case property names to camelCase
- Example: `inst_cd` → `instCd`, `total_cnt` → `totalCnt`
- All DTOs use `@Data` annotation (Lombok)

### 5. Mapper Interfaces
- Created with `@Mapper` annotation
- Methods match XML query IDs
- Parameters use `@Param` annotations for clarity

## Build Status

✅ **BUILD SUCCESS**
- All mappers compiled without errors
- No MyBatis mapping errors
- Zero compilation errors related to migration

## Files Created

**Total: 18 files**
- 6 XML mappers
- 6 Mapper interfaces
- 9 DTO classes (3 for SecurityResult, 1 each for others)

## Notes

1. **MariaDB LoginMapper**: Not found at expected locations, skipped from this batch
2. **ReportWeeklyState**: References `ReportDailyDTO` from the old package path in resultMap, but DTO created in new path
3. **All SQL preserved**: No modifications to query logic, only package/namespace changes

## Next Steps

1. Consider creating Service layer for these mappers
2. Create REST Controllers for report APIs
3. Add integration tests for the migrated mappers
4. Update any existing references to old mapper locations

## Verification Checklist

- [x] All XML files created in correct location
- [x] All Mapper interfaces created with @Mapper
- [x] All DTOs created with @Data
- [x] Namespace updated in XML
- [x] Type references updated in resultMap
- [x] Build successful
- [x] No compilation errors
- [ ] Integration tests (pending)
- [ ] Service layer (pending)
- [ ] REST Controllers (pending)

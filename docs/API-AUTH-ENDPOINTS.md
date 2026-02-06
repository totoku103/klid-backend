# Auth API Endpoints Reference

## Quick Reference

### CTRS System
- POST `/api/auth/ctrs/primary` - 1차 인증
- POST `/api/auth/ctrs/secondary/otp` - 2차 OTP 인증
- POST `/api/auth/ctrs/secondary/email` - 2차 이메일 인증

### CTSS System
- POST `/api/auth/ctss/primary` - 1차 인증
- POST `/api/auth/ctss/secondary/otp` - 2차 OTP 인증

### VMS System
- POST `/api/auth/vms/primary` - 1차 인증
- POST `/api/auth/vms/secondary/otp` - 2차 OTP 인증
- POST `/api/auth/vms/secondary/email/send` - 이메일 발송
- POST `/api/auth/vms/secondary/email/validate` - 이메일 검증

## 상세 스펙

모든 API는 JSON 형식의 요청/응답을 사용합니다.

### Error Response Format
```json
{
  "timestamp": "2026-02-05T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "사용자 ID를 입력해주세요.",
  "path": "/api/auth/ctrs/primary"
}
```

### Authentication Flow
1. Primary Authentication (1차 인증)
   - ID/Password 검증
   - OTP 키 확인/생성
   - GPKI 정보 조회 (해당 시스템)

2. Secondary Authentication (2차 인증)
   - OTP 코드 검증 또는
   - 이메일 코드 검증

3. Session Management
   - 통합 세션 관리 (IntegrationSessionManager)
   - 1차/2차 인증 상태 추적

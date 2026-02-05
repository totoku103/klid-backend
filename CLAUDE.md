# KLID Backend Project

## 프로젝트 개요

이 프로젝트는 `/Users/totoku103/BurunetProjects/klid-java-web` 의 모놀리식 프로젝트를 **React Frontend + Spring Boot Backend**로 분리하는 작업 중 **Backend 부분**입니다.

## 기술 스택

- **Framework**: Spring Boot 4.0.1
- **Java Version**: 25
- **Build Tool**: Maven
- **ORM**: MyBatis 4.0.1
- **Database**: Oracle (ojdbc11), MySQL
- **Security**: Spring Security
- **WebSocket**: Spring WebSocket (실시간 알림용)
- **기타**: Lombok, Apache POI, JFreeChart, Bouncy Castle

## 패키지 구조

```
com.klid
├── api/           # 신규 REST API (프론트엔드 분리용)
│   ├── board/     # 게시판 관련 API
│   │   └── accident/  # 사고 관련 기능
│   ├── menu/      # 메뉴 API
│   └── session/   # 세션 관리 API
├── common/        # 공통 유틸리티, 암호화, HTTP 클라이언트 등
│   ├── http/      # HTTP 클라이언트
│   └── hwplib/    # HWP 문서 처리 라이브러리
└── KlidApplication.java  # 메인 애플리케이션
```

## 개발 규칙

### 코딩 컨벤션

#### Controller 규칙
- `@RestController` 사용
- **`ReturnData` 사용 금지** → `ResponseEntity<T>` 사용
- HTTP Method에 맞는 어노테이션 사용: `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- 메소드명과 URI는 기능에 맞게 명확히 정의

#### URI 규칙
- **kebab-case** 사용
- **`.do` 확장자 사용 금지** (기존 시스템에서 마이그레이션 시 제거)
- 예: `/api/user-info`, `/api/accident-reports`

```java
// Bad (기존 방식)
@GetMapping("/user/info.do")

// Good (신규 방식)
@GetMapping("/api/user-info")
```

#### 네이밍 규칙
- **축약어는 대문자**: `OTP`, `DTO`, `API`, `URL`
- 예: `OTPService`, `UserDTO`, `APIResponse`

#### 타입 및 변수
- 제네릭 타입 파라미터 항상 명시 (raw type 금지)
- **변수에 `final` 사용**
- `var`는 타입이 명확한 경우에만 허용

```java
// Bad
List users = userService.findAll();
String name = "test";

// Good
final List<UserDTO> users = userService.findAll();
final String name = "test";
```

#### 의존성 주입
- **`@RequiredArgsConstructor` 사용** (생성자 주입)
- 필드 주입(`@Autowired`) 금지

```java
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
}
```

#### Service 레이어
- **Interface 사용하지 않음** (직접 클래스 구현)
- 기능 분리 시 **SOLID 원칙** 준수

```java
// Bad
public interface UserService { ... }
public class UserServiceImpl implements UserService { ... }

// Good
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
}
```

#### 설계 원칙
- 기능을 분리할 수 있다면 분리 (단일 책임 원칙)
- SOLID 원칙 준수

### 신규 API 작성 위치
- **모든 신규 API는 `com.klid.api` 패키지 하위에 작성**
- 기존 기능을 REST API로 전환할 때 `com.klid.api.{도메인}` 형태로 구성

### API 패키지 구조 컨벤션
```
com.klid.api.{도메인}/
├── controller/    # REST Controller
├── service/       # 비즈니스 로직
├── dto/           # Data Transfer Objects
├── persistence/   # MyBatis Mapper 인터페이스
└── client/        # 외부 API 클라이언트 (필요시)
```

## 빌드 및 실행

```bash
# 빌드
mvn clean package

# 실행
mvn spring-boot:run

# 테스트
mvn test
```

## 관련 프로젝트

- **원본 프로젝트**: `/Users/totoku103/BurunetProjects/klid-java-web`
- **Frontend (분리 예정)**: React 기반으로 별도 프로젝트 생성 예정

package com.klid.test;

import com.klid.webapp.common.dto.UserDto;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 테스트에서 SessionManager/IntegrationSessionManager가 동작하도록
 * RequestContextHolder에 MockHttpServletRequest를 설정하는 유틸리티
 */
public final class TestSessionHelper {

    private TestSessionHelper() {
    }

    /**
     * 기본 테스트 사용자로 세션을 설정합니다.
     * SessionManager.getUser(), SessionManager.getSession() 등이 NPE 없이 동작합니다.
     */
    public static void setupMockSession() {
        final UserDto user = createDefaultTestUser();
        setupMockSession(user);
    }

    /**
     * 지정된 UserDto로 세션을 설정합니다.
     */
    public static void setupMockSession(final UserDto user) {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final MockHttpSession session = new MockHttpSession();

        // UserDto 세션 설정
        session.setAttribute("User", user);

        // 인증 상태 세션 설정 (2단계 인증 완료 상태)
        session.setAttribute("IS_AUTHENTICATE_PRIMARY", true);
        session.setAttribute("IS_AUTHENTICATE_SECOND", true);

        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    /**
     * RequestContextHolder를 정리합니다. @AfterEach에서 호출하세요.
     */
    public static void clearMockSession() {
        RequestContextHolder.resetRequestAttributes();
    }

    /**
     * 기본 테스트 사용자를 생성합니다.
     */
    public static UserDto createDefaultTestUser() {
        final UserDto user = new UserDto();
        user.setUserId("testuser");
        user.setUserName("테스트사용자");
        user.setInstCd(1100000);
        user.setPntInstCd(0);
        user.setInstNm("테스트기관");
        user.setLocalCd(-1);
        user.setAuthMain("AUTH_MAIN_2");
        user.setAuthSub("AUTH_SUB_1");
        user.setRoleCtrs("10");
        user.setCenterUserYn("Y");
        user.setIpAddr("127.0.0.1");
        user.setUseYn("Y");

        // 게시판 권한 (읽기/쓰기/수정/삭제 모두 허용)
        user.setRoleTbz01("Y");
        user.setRoleTbz02("Y");
        user.setRoleTbz03("Y");
        user.setRoleTbz04("Y");
        user.setRoleTbz05("Y");
        user.setRoleTbz06("Y");
        user.setRoleNot01("Y");
        user.setRoleNot02("Y");
        user.setRoleNot03("Y");
        user.setRoleNot04("Y");
        user.setRoleRes01("Y");
        user.setRoleRes02("Y");
        user.setRoleRes03("Y");
        user.setRoleRes04("Y");
        user.setRoleSha01("Y");
        user.setRoleSha02("Y");
        user.setRoleSha03("Y");
        user.setRoleSha04("Y");
        user.setRoleQna01("Y");
        user.setRoleQna02("Y");
        user.setRoleQna03("Y");
        user.setRoleQna04("Y");

        return user;
    }
}

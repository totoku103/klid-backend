package com.klid.webapp.api.user.information.dto;

import com.klid.api.session.dto.SessionUserSimpleInformationDTO;
import com.klid.webapp.common.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionUserSimpleInformationDTOTest {

    @Nested
    @DisplayName("from()")
    class From {

        @Test
        @DisplayName("UserDto가 null이면 null을 반환한다")
        void returnsNullWhenUserDtoIsNull() {
            SessionUserSimpleInformationDTO result = SessionUserSimpleInformationDTO.from(null);
            assertNull(result);
        }

        @Test
        @DisplayName("UserDto에서 기본 정보를 올바르게 변환한다")
        void convertsBasicInformationCorrectly() {
            UserDto userDto = createTestUserDto();

            SessionUserSimpleInformationDTO result = SessionUserSimpleInformationDTO.from(userDto);

            assertNotNull(result);
            assertEquals("testUser", result.userId());
            assertEquals("홍길동", result.userName());
            assertEquals(100, result.instCd());
            assertEquals("테스트기관", result.instNm());
        }

        @Test
        @DisplayName("UserDto에서 권한 정보를 올바르게 변환한다")
        void convertsRoleInformationCorrectly() {
            UserDto userDto = createTestUserDto();

            SessionUserSimpleInformationDTO result = SessionUserSimpleInformationDTO.from(userDto);

            assertNotNull(result);
            assertNotNull(result.authRole());
            assertEquals("A", result.authRole().main());
            assertEquals("01", result.authRole().sub());
        }

        @Test
        @DisplayName("UserDto에서 게시판 권한 정보를 올바르게 변환한다")
        void convertsBoardRoleCorrectly() {
            UserDto userDto = createTestUserDto();

            SessionUserSimpleInformationDTO result = SessionUserSimpleInformationDTO.from(userDto);

            assertNotNull(result.boardRole());
            assertNotNull(result.boardRole().tbz());
            assertEquals("Y", result.boardRole().tbz().role01());
            assertNotNull(result.boardRole().notice());
            assertEquals("N", result.boardRole().notice().role01());
        }
    }

    private UserDto createTestUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUserId("testUser");
        userDto.setUserName("홍길동");
        userDto.setInstCd(100);
        userDto.setInstNm("테스트기관");
        userDto.setPntInstCd(10);
        userDto.setPntInstNm("상위기관");
        userDto.setGrade("과장");
        userDto.setEmailAddr("test@example.com");
        userDto.setMoblPhnNo("01012345678");
        userDto.setOffcTelNo("0212345678");
        userDto.setOffcFaxNo("0212345679");
        userDto.setHomeTelNo("0312345678");
        userDto.setSmsYn("Y");
        userDto.setEmailYn("Y");
        userDto.setCenterUserYn("N");
        userDto.setRegDt("2024-01-01");
        userDto.setRoleCtrs("10");
        userDto.setRoleIics("20");
        userDto.setRoleRms("30");
        userDto.setRoleEws("40");
        userDto.setRoleSd("50");
        userDto.setAuthMain("A");
        userDto.setAuthSub("01");
        userDto.setAuthGrpNo(1);
        userDto.setAuthGrpName("관리자그룹");
        userDto.setRoleTbz01("Y");
        userDto.setRoleNot01("N");
        return userDto;
    }
}

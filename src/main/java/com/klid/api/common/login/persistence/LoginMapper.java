package com.klid.api.common.login.persistence;

import com.klid.api.common.login.dto.UserDTO;
import com.klid.api.common.login.dto.UserLogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 로그인 Mapper
 */
@Mapper
public interface LoginMapper {

    /**
     * 로그인 정보 조회
     *
     * @param userId 사용자 ID
     * @param password 비밀번호
     * @return 사용자 정보
     */
    UserDTO selectLogin(@Param("userId") String userId, @Param("password") String password);

    /**
     * 사용자 정보 조회
     *
     * @param userId 사용자 ID
     * @return 사용자 정보
     */
    UserDTO selectUserInfo(@Param("userId") String userId);

    /**
     * 사용자명과 사무실 전화번호로 사용자 정보 조회
     *
     * @param userName 사용자명
     * @param officeNumber 사무실 전화번호
     * @return 사용자 정보
     */
    UserDTO selectUserInfoByUserNameAndOfficeNumber(
            @Param("userName") String userName,
            @Param("officeNumber") String officeNumber
    );

    /**
     * 사용자 접근 로그 삽입
     *
     * @param userLog 사용자 로그 정보
     * @return 삽입된 행 수
     */
    int insertUserLog(UserLogDTO userLog);

    /**
     * OTP 키 수정
     *
     * @param userId 사용자 ID
     * @param otpSetCode OTP 설정 코드
     * @return 수정된 행 수
     */
    int editOtpKey(@Param("userId") String userId, @Param("otpSetCode") String otpSetCode);
}

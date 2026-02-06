package com.klid.api.user.otp.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OtpMapper {
    String selectOtpSecretKeyByUserIdAndIpAddr(@Param("userId") String userId,
                                                @Param("ipAddr") String ipAddr);

    String selectOtpSecretKeyByUserNameAndPhoneNumberAndOfficeNumber(
            @Param("userName") String userName,
            @Param("phoneNumber") String phoneNumber,
            @Param("officeNumber") String officeNumber);
}

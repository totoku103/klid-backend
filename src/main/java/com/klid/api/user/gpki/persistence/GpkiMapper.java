package com.klid.api.user.gpki.persistence;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GpkiMapper {
    String selectGpkiSerialNoByUserIdAndIpAddr(@Param("userId") String userId);

    void updateGpkiSerialNoByUserIdAndIpAddr(@Param("gpkiSerialNo") String gpkiSerialNo,
                                              @Param("userId") String userId,
                                              @Param("ipAddr") String ipAddr);

    void updateGpkiSerialNoByUserNameAndOfficeNumberAndPhoneNumber(
            @Param("gpkiSerialNo") String gpkiSerialNo,
            @Param("userName") String userName,
            @Param("officeNumber") String officeNumber,
            @Param("phoneNumber") String phoneNumber);
}

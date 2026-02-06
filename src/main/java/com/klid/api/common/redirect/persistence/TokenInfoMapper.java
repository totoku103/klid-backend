package com.klid.api.common.redirect.persistence;

import com.klid.api.common.redirect.dto.SimpleTokenInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface TokenInfoMapper {
    void insertToken(@Param("userId") String userId,
                     @Param("clientIp") String clientIp,
                     @Param("systemType") String systemType,
                     @Param("token") String token,
                     @Param("expiredAt") Date expiredAt,
                     @Param("status") String status);

    SimpleTokenInfoDTO selectActiveAndNotUsedTokenByToken(@Param("token") String token);

    void updateOnlyExpiredByToken(@Param("token") String token);

    void updateExpiredAndUsedByToken(@Param("token") String token);
}

package com.klid.api.hist.useract.persistence;

import com.klid.api.hist.useract.dto.UserActHistDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserActHistMapper {
    List<UserActHistDTO> selectUserActHist(@Param("srchUserName") String srchUserName,
                                           @Param("srchUserId") String srchUserId,
                                           @Param("srchActType") String srchActType,
                                           @Param("period") String period,
                                           @Param("date1") String date1,
                                           @Param("date2") String date2);

    int addUserActHist(UserActHistDTO userActHistDTO);

    void insertFileDownloadHistory(@Param("userActHistSeq") Integer userActHistSeq,
                                    @Param("reason") String reason,
                                    @Param("extraAttr") String extraAttr,
                                    @Param("fileName") String fileName);
}

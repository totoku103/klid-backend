package com.klid.api.hist.userinout.persistence;

import com.klid.api.hist.userinout.dto.UserInoutHistMgmtDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInoutHistMgmtMapper {
    List<UserInoutHistMgmtDTO> selectLogUserList();

    List<UserInoutHistMgmtDTO> selectUserInoutHist(@Param("sLogCd") String sLogCd,
                                                    @Param("sUsrId") String sUsrId,
                                                    @Param("date1") String date1,
                                                    @Param("date2") String date2,
                                                    @Param("time1") String time1,
                                                    @Param("time2") String time2);
}

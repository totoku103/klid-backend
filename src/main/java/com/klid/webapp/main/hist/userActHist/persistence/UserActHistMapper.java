package com.klid.webapp.main.hist.userActHist.persistence;

import com.klid.webapp.main.hist.userActHist.dto.UserActHistDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserActHistMapper {

    void addUserActHist(Map<String, Object> paramMap);

    List<UserActHistDto> selectUserActHist(Map<String, Object> paramMap);

    int insertFileDownloadHistory(@Param("userActHistSeq") int userActHistSeq, @Param("reason") String reason, @Param("extraAttr") String extraAttr, @Param("fileName") String fileName);

}

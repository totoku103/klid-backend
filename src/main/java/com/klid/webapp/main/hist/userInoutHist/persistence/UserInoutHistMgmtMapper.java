package com.klid.webapp.main.hist.userInoutHist.persistence;

import com.klid.webapp.main.hist.userInoutHist.dto.UserInoutHistMgmtDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("userInoutHistMgmtMapper")
public interface UserInoutHistMgmtMapper {

    List<UserInoutHistMgmtDto> selectLogUserList(Map<String, Object> paramMap);

    List<UserInoutHistMgmtDto> selectUserInoutHist(Map<String, Object> paramMap);

}

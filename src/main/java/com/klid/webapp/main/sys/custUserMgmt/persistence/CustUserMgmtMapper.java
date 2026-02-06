package com.klid.webapp.main.sys.custUserMgmt.persistence;

import com.klid.webapp.main.sys.custUserMgmt.dto.CustUserMgmtDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustUserMgmtMapper {

    /** SMS 사용자 리스트 받아오기 */
    List<CustUserMgmtDto> getSmsUserList(Map<String, Object> paramMap);

    /** SMS 외부 사용자 리스트 받아오기 */
    List<CustUserMgmtDto> getSmsOfUserList(Map<String, Object> paramMap);

    /** 사용자 폰번호 받아오기 */
    CustUserMgmtDto selectUserPhone(Map<String, Object> paramMap);

    List<Map<String, Object>> selectSmsGroup(Map<String, Object> map);

    void insertSmsGroup(Map<String, Object> paramMap);

    int updateSmsGroup(Map<String, Object> paramMap);

    int deleteSmsGroup(Map<String, Object> paramMap);

    int updateSmsTopGroup(Map<String, Object> paramMap);
}

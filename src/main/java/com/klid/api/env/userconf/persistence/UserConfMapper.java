package com.klid.api.env.userconf.persistence;

import com.klid.api.env.userconf.dto.CodeDTO;
import com.klid.api.env.userconf.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserConfMapper {
    List<UserDTO> selectUserAddrList(Map<String, Object> params);
    List<UserDTO> selectUserConfList(Map<String, Object> params);
    int addUser(UserDTO dto);
    int selectUserIdDuplicateCnt(String userId);
    UserDTO selectDetailUser(String userId);
    int editUser(UserDTO dto);
    int editSelfUser(UserDTO dto);
    int updateUserPassword(UserDTO dto);
    List<UserDTO> selectPushUsers(Map<String, Object> params);
    List<CodeDTO> selectAuthList(Map<String, Object> params);
    int userPassReset(UserDTO dto);
    int userLockReset(Map<String, Object> params);
    int passwordCheck(Map<String, Object> params);
    int updateLoginFailCnt(String userId);
    int updateLoginFailCntReset(String userId);
    int updateLoginLock(String userId);
    int delUser(String userId);
    int updateAllUserPassReset(UserDTO dto);
}

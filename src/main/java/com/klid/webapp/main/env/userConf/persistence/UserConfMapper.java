package com.klid.webapp.main.env.userConf.persistence;

import com.klid.webapp.common.code.dto.CodeDto;
import com.klid.webapp.common.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserConfMapper {
	//사용자 연락처 목록
	public List<UserDto> selectUserAddrList(Map<String, Object> paramMap);

	//사용자 목록
	public List<UserDto> selectUserConfList(Map<String, Object> paramMap);

	void addUser(Map<String, Object> paramMap);

	int selectUserIdDuplicateCnt(Map<String, Object> paramMap);

	UserDto selectDetailUser(Map<String, Object> paramMap);

	void editUser(Map<String, Object> paramMap);

	void editSelfUser(Map<String, Object> paramMap);
	
	void updateUserPassword(Map<String, Object> paramMap);

	List<CodeDto> selectAuthList(Map<String, Object> paramMap);

	void userPassReset(Map<String, Object> paramMap);

	void userLockReset(Map<String, Object> paramMap);

	int passwordCheck(Map<String, Object> paramMap);

	void updateLoginFailCnt(Map<String, Object> paramMap);

	void updateLoginFailCntReset(Map<String, Object> paramMap);

	void updateLoginLock(Map<String, Object> paramMap);

	void delUser(Map<String, Object> paramMap);

	List<UserDto> selectPushUsers(Map<String, Object> paramMap);

	void updateAllUserPassReset(Map<String, Object> paramMap);
}

package com.klid.api.env.userconf.service;

import com.klid.api.env.userconf.dto.CodeDTO;
import com.klid.api.env.userconf.dto.UserDTO;
import com.klid.api.env.userconf.persistence.UserConfMapper;
import com.klid.api.hist.useract.dto.UserActHistDTO;
import com.klid.api.hist.useract.persistence.UserActHistMapper;
import com.klid.common.SEED_KISA256;
import com.klid.webapp.common.CustomException;
import com.klid.webapp.common.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserConfigService {

    private final UserConfMapper userConfMapper;
    private final UserActHistMapper userActHistMapper;

    private static final String GUID = "1D26DA5D-C6DA-4FD1-8D0B-6B82CC84058A";
    private static final String REF_TABLE = "COMM_USER";
    private static final String PASSWORD_ERR_MSG = "비밀번호는 영문자(대+소문자)/숫자/특수문자 포함 8자 이상입니다.";

    public List<UserDTO> getContactList(final Map<String, Object> params) {
        return userConfMapper.selectUserAddrList(params);
    }

    public List<UserDTO> getList(final Map<String, Object> params) {
        final List<UserDTO> list = userConfMapper.selectUserConfList(params);
        for (final UserDTO dto : list) {
            if (dto.getMoblPhnNo() != null) {
                dto.setMoblPhnNo(SEED_KISA256.Decrypt(dto.getMoblPhnNo()));
            }
            if (dto.getEmailAddr() != null) {
                dto.setEmailAddr(SEED_KISA256.Decrypt(dto.getEmailAddr()));
            }
        }
        return list;
    }

    @Transactional
    public void addUser(final UserDTO dto) {
        dto.setPassword(SEED_KISA256.Encrypt(dto.getPassword()));
        dto.setEmailAddr(SEED_KISA256.Encrypt(dto.getEmailAddr()));
        dto.setMoblPhnNo(SEED_KISA256.Encrypt(dto.getMoblPhnNo()));
        userConfMapper.addUser(dto);
        saveUserActHist("C");
    }

    @Transactional
    public void editUser(final String userId, final UserDTO dto) {
        dto.setUserId(userId);
        dto.setEmailAddr(SEED_KISA256.Encrypt(dto.getEmailAddr()));
        dto.setMoblPhnNo(SEED_KISA256.Encrypt(dto.getMoblPhnNo()));
        userConfMapper.editUser(dto);
        saveUserActHist("U");
    }

    @Transactional
    public void editSelfUser(final UserDTO dto) {
        dto.setUserId(SessionManager.getUser().getUserId());
        dto.setEmailAddr(SEED_KISA256.Encrypt(dto.getEmailAddr()));
        dto.setMoblPhnNo(SEED_KISA256.Encrypt(dto.getMoblPhnNo()));
        userConfMapper.editSelfUser(dto);
    }

    @Transactional
    public void editPassword(final String userId, final String password) {
        validatePassword(password);
        final UserDTO dto = new UserDTO();
        dto.setUserId(userId);
        dto.setPassword(SEED_KISA256.Encrypt(password));
        userConfMapper.updateUserPassword(dto);
    }

    @Transactional
    public void passwordCheck(final String userId, final String prePassword, final String newPassword) {
        final Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("password", SEED_KISA256.Encrypt(prePassword));
        final int checkPrePassword = userConfMapper.passwordCheck(params);
        if (checkPrePassword == 0) {
            throw new CustomException("이전 비밀번호가 일치하지 않습니다.");
        }

        final UserDTO dto = new UserDTO();
        dto.setUserId(userId);
        dto.setPassword(SEED_KISA256.Encrypt(newPassword));
        userConfMapper.updateUserPassword(dto);
    }

    @Transactional
    public void passwordReset(final String userId) {
        final UserDTO dto = new UserDTO();
        dto.setUserId(userId);
        dto.setPassword(SEED_KISA256.Encrypt(userId));
        userConfMapper.userPassReset(dto);
    }

    @Transactional
    public void lockReset(final String userId) {
        final Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        userConfMapper.userLockReset(params);
    }

    @Transactional
    public int allPasswordReset() {
        final Map<String, Object> params = new HashMap<>();
        params.put("userId", null);
        final List<UserDTO> list = userConfMapper.selectUserConfList(params);

        int updateCnt = 0;
        for (final UserDTO user : list) {
            final UserDTO dto = new UserDTO();
            dto.setUserId(user.getUserId());
            dto.setPassword(SEED_KISA256.Encrypt(user.getUserId()));
            userConfMapper.updateAllUserPassReset(dto);
            updateCnt++;
        }
        return updateCnt;
    }

    @Transactional
    public void expirePasswordChange(final String password) {
        validatePassword(password);
        final UserDTO dto = new UserDTO();
        if (SessionManager.getUser() != null) {
            dto.setUserId(SessionManager.getUser().getUserId());
        }
        dto.setPassword(SEED_KISA256.Encrypt(password));
        userConfMapper.updateUserPassword(dto);
    }

    public int checkDuplicate(final String userId) {
        return userConfMapper.selectUserIdDuplicateCnt(userId);
    }

    public Map<String, Object> getDetail(final String userId) {
        final UserDTO userDTO = userConfMapper.selectDetailUser(userId);
        if (userDTO.getMoblPhnNo() != null) {
            userDTO.setMoblPhnNo(SEED_KISA256.Decrypt(userDTO.getMoblPhnNo()));
        }
        if (userDTO.getEmailAddr() != null) {
            userDTO.setEmailAddr(SEED_KISA256.Decrypt(userDTO.getEmailAddr()));
        }

        final Map<String, Object> result = new HashMap<>();
        result.put("contents", userDTO);
        return result;
    }

    public List<CodeDTO> getAuthList(final Map<String, Object> params) {
        return userConfMapper.selectAuthList(params);
    }

    public Map<String, Object> checkMyId(final String userId) {
        final Map<String, Object> result = new HashMap<>();
        String checkAuthYn = "N";

        if (SessionManager.getUser().getUserId().equals(userId)) {
            checkAuthYn = "Y";
            final UserDTO userDTO = userConfMapper.selectDetailUser(SessionManager.getUser().getUserId());
            if (userDTO.getMoblPhnNo() != null) {
                userDTO.setMoblPhnNo(SEED_KISA256.Decrypt(userDTO.getMoblPhnNo()));
            }
            if (userDTO.getEmailAddr() != null) {
                userDTO.setEmailAddr(SEED_KISA256.Decrypt(userDTO.getEmailAddr()));
            }
            result.put("contents", userDTO);
        }
        result.put("checkAuthYn", checkAuthYn);
        return result;
    }

    public int checkUserAuth() {
        int checkCnt = 0;
        final String userMainAuth = SessionManager.getUser().getAuthMain();
        if ("AUTH_MAIN_2".equals(userMainAuth) || "AUTH_MAIN_3".equals(userMainAuth) || "AUTH_MAIN_4".equals(userMainAuth)) {
            checkCnt++;
        }
        return checkCnt;
    }

    @Transactional
    public void deleteUser(final String userId) {
        userConfMapper.delUser(userId);
        saveUserActHist("D");
    }

    public List<UserDTO> getPushUsers(final Map<String, Object> params) {
        return userConfMapper.selectPushUsers(params);
    }

    public UserDTO getUserInfo(final String userId) {
        return userConfMapper.selectDetailUser(userId);
    }

    private void validatePassword(final String pwd) {
        if (pwd.length() < 8) {
            throw new CustomException(PASSWORD_ERR_MSG);
        }
        if (!pwd.matches(".*[0-9].*")) {
            throw new CustomException(PASSWORD_ERR_MSG);
        }
        if (!pwd.matches(".*[A-Z]{1,}.*")) {
            throw new CustomException(PASSWORD_ERR_MSG);
        }
        if (!pwd.matches(".*[!@#$%^&*()_+=|/~`].*")) {
            throw new CustomException(PASSWORD_ERR_MSG);
        }
    }

    private void saveUserActHist(final String actType) {
        final UserActHistDTO histDTO = new UserActHistDTO();
        histDTO.setGuid(GUID);
        histDTO.setActType(actType);
        histDTO.setRegUserId(SessionManager.getUser().getUserId());
        histDTO.setRefTable(REF_TABLE);
        histDTO.setRegUserName(SessionManager.getUser().getUserName());
        userActHistMapper.addUserActHist(histDTO);
    }
}

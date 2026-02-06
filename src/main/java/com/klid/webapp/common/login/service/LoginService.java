package com.klid.webapp.common.login.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.common.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;

public interface LoginService {

	public ReturnData isUserAccountLocked(Criterion criterion);

	public String getLogin(Criterion criterion, HttpServletRequest request);

	public void logout(UserDto user, HttpServletRequest request);

	public HashMap<String, String> getOtpGenerate(Criterion criterion, HttpServletRequest request);

	public boolean getOtpCheck(Criterion criterion, HttpServletRequest request);

	public ReturnData editOtpKey(Criterion criterion);
	
}

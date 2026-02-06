package com.klid.webapp.common.grp.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AuthGrpDto implements Serializable {
	private long grpNo;
	private String grpName;
	private String authMain;
	
	public String getAuthMain() {
		return authMain;
	}
	public void setAuthMain(String authMain) {
		this.authMain = authMain;
	}
	public long getGrpNo() {
		return grpNo;
	}
	public void setGrpNo(long grpNo) {
		this.grpNo = grpNo;
	}
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
}

package com.jeiglobal.hk.domain.common;

public class Authority {
	/**
	 * 권한 코드
	 */
	private String authority;
	
	/**
	 * 권한 이름(설명)
	 */
	private String authorityName;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
}

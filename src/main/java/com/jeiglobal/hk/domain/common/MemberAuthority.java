package com.jeiglobal.hk.domain.common;

public class MemberAuthority {
	private String memberId;
	private String authority;
	
	public MemberAuthority() {
		
	}
	
	public MemberAuthority(String memberId, String authority) {
		this.memberId = memberId;
		this.authority = authority;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}

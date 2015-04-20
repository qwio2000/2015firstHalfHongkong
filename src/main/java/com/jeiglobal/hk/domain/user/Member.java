package com.jeiglobal.hk.domain.user;

public class Member {
	private String memberId;
	private String memberPassword;
	private boolean memberEnabled;
	
	public Member() {

	}
	
	public Member(String memberId, String memberPassword, boolean memberEnabled) {
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberEnabled = memberEnabled;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public boolean getMemberEnabled() {
		return memberEnabled;
	}

	public void setMemberEnabled(boolean memberEnabled) {
		this.memberEnabled = memberEnabled;
	}
}

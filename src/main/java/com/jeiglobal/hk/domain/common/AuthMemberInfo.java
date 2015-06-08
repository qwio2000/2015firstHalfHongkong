package com.jeiglobal.hk.domain.common;

public class AuthMemberInfo {
	private String memberId;
	private String memberPassword;
	private boolean memberEnabled;
	private String jisaCD;
	private String depid1;
	private String depid2;
	private String empKey;
	private String empName;
	private String empKeyLvCD;
	private String depMngCD;
	private String encodeCookie;
	
	public AuthMemberInfo() {

	}
	
	public AuthMemberInfo(String memberId, String memberPassword, boolean memberEnabled
			,String empName,String jisaCD,String depid1,String depid2,String empKey,String empKeyLvCD,String depMngCD,String encodeCookie) {
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberEnabled = memberEnabled;
		this.empName = empName;
		this.jisaCD = jisaCD;
		this.depid1 = depid1;
		this.depid2 = depid2;
		this.empKey = empKey;
		this.empKeyLvCD = empKeyLvCD;
		this.depMngCD = depMngCD;
		this.encodeCookie = encodeCookie;
	}
	
	public String getEncodeCookie() {
		return encodeCookie;
	}

	public void setEncodeCookie(String encodeCookie) {
		this.encodeCookie = encodeCookie;
	}

	public String getJisaCD() {
		return jisaCD;
	}

	public void setJisaCD(String jisaCD) {
		this.jisaCD = jisaCD;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpKeyLvCD() {
		return empKeyLvCD;
	}

	public void setEmpKeyLvCD(String empKeyLvCD) {
		this.empKeyLvCD = empKeyLvCD;
	}

	public String getDepMngCD() {
		return depMngCD;
	}

	public void setDepMngCD(String depMngCD) {
		this.depMngCD = depMngCD;
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

	public String getDepid1() {
		return depid1;
	}

	public void setDepid1(String depid1) {
		this.depid1 = depid1;
	}

	public String getDepid2() {
		return depid2;
	}

	public void setDepid2(String depid2) {
		this.depid2 = depid2;
	}

	public String getEmpKey() {
		return empKey;
	}

	public void setEmpKey(String empKey) {
		this.empKey = empKey;
	}

	@Override
	public String toString() {
		return "AuthMemberInfo [memberId=" + memberId + ", memberPassword="
				+ memberPassword + ", memberEnabled=" + memberEnabled
				+ ", jisaCD=" + jisaCD + ", depid1=" + depid1 + ", depid2="
				+ depid2 + ", empKey=" + empKey + ", empName=" + empName
				+ ", empKeyLvCD=" + empKeyLvCD + ", depMngCD=" + depMngCD
				+ ", encodeCookie=" + encodeCookie + "]";
	}
	
	
}

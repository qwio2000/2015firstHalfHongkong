package com.jeiglobal.hk.domain.member;

public class MemberHuheiInfo {
	private String kwamok;
	private String huheiYMD;
	private String huheiKind;
	private String empKey;
	private String empName;
	private String huheiSayu;

	public String getKwamok() {
		return kwamok;
	}
	public void setKwamok(String kwamok) {
		this.kwamok = kwamok;
	}
	public String getHuheiYMD() {
		return huheiYMD;
	}
	public void setHuheiYMD(String huheiYMD) {
		this.huheiYMD = huheiYMD;
	}
	public String getHuheiKind() {
		return huheiKind;
	}
	public void setHuheiKind(String huheiKind) {
		this.huheiKind = huheiKind;
	}
	public String getEmpKey() {
		return empKey;
	}
	public void setEmpKey(String empKey) {
		this.empKey = empKey;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getHuheiSayu() {
		return huheiSayu;
	}
	public void setHuheiSayu(String huheiSayu) {
		this.huheiSayu = huheiSayu;
	}
	@Override
	public String toString() {
		return "MemberHuheiInfo [kwamok=" + kwamok + ", huheiYMD=" + huheiYMD
				+ ", huheiKind=" + huheiKind + ", empKey=" + empKey
				+ ", empName=" + empName + ", huheiSayu=" + huheiSayu + "]";
	}
}

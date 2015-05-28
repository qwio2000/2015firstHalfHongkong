package com.jeiglobal.hk.domain.member;

public class MemberIpgumInfo {
	private String kwamok;
	private String ipgumYMD;
	private String existCD;
	private String empKey;
	private String empName;
	private String ipheibi;
	private String ipheibiFree;
	private String wolheibi;
	private String wolheibiFree;
	private String expireYMD;
	private String totalHeibi;
	public String getKwamok() {
		return kwamok;
	}
	public void setKwamok(String kwamok) {
		this.kwamok = kwamok;
	}
	public String getIpgumYMD() {
		return ipgumYMD;
	}
	public void setIpgumYMD(String ipgumYMD) {
		this.ipgumYMD = ipgumYMD;
	}
	public String getExistCD() {
		return existCD;
	}
	public void setExistCD(String existCD) {
		this.existCD = existCD;
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
	public String getIpheibi() {
		return ipheibi;
	}
	public void setIpheibi(String ipheibi) {
		this.ipheibi = ipheibi;
	}
	public String getIpheibiFree() {
		return ipheibiFree;
	}
	public void setIpheibiFree(String ipheibiFree) {
		this.ipheibiFree = ipheibiFree;
	}
	public String getWolheibi() {
		return wolheibi;
	}
	public void setWolheibi(String wolheibi) {
		this.wolheibi = wolheibi;
	}
	public String getWolheibiFree() {
		return wolheibiFree;
	}
	public void setWolheibiFree(String wolheibiFree) {
		this.wolheibiFree = wolheibiFree;
	}
	public String getExpireYMD() {
		return expireYMD;
	}
	public void setExpireYMD(String expireYMD) {
		this.expireYMD = expireYMD;
	}
	public String getTotalHeibi() {
		return totalHeibi;
	}
	public void setTotalHeibi(String totalHeibi) {
		this.totalHeibi = totalHeibi;
	}
	@Override
	public String toString() {
		return "MemberIpgumInfo [kwamok=" + kwamok + ", ipgumYMD=" + ipgumYMD
				+ ", existCD=" + existCD + ", empKey=" + empKey + ", empName="
				+ empName + ", ipheibi=" + ipheibi + ", ipheibiFree="
				+ ipheibiFree + ", wolheibi=" + wolheibi + ", wolheibiFree="
				+ wolheibiFree + ", expireYMD=" + expireYMD + ", totalHeibi="
				+ totalHeibi + "]";
	}
}

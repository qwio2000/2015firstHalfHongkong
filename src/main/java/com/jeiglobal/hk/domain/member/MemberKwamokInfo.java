package com.jeiglobal.hk.domain.member;

import org.springframework.core.style.ToStringCreator;

public class MemberKwamokInfo {
	private String kwamok;
	private String state;
	private String ipheiYMD;
	private String bokheiYMD;
	private String huheiYMD;
	private String setCnt;
	private String fstYoil;
	private String fstEmpKey;
	private String fstEmpName;
	private String sndYoil;
	private String sndEmpKey;
	private String sndEmpName;
	public String getKwamok() {
		return kwamok;
	}
	public void setKwamok(String kwamok) {
		this.kwamok = kwamok;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIpheiYMD() {
		return ipheiYMD;
	}
	public void setIpheiYMD(String ipheiYMD) {
		this.ipheiYMD = ipheiYMD;
	}
	public String getBokheiYMD() {
		return bokheiYMD;
	}
	public void setBokheiYMD(String bokheiYMD) {
		this.bokheiYMD = bokheiYMD;
	}
	public String getHuheiYMD() {
		return huheiYMD;
	}
	public void setHuheiYMD(String huheiYMD) {
		this.huheiYMD = huheiYMD;
	}
	public String getSetCnt() {
		return setCnt;
	}
	public void setSetCnt(String setCnt) {
		this.setCnt = setCnt;
	}
	public String getFstYoil() {
		return fstYoil;
	}
	public void setFstYoil(String fstYoil) {
		this.fstYoil = fstYoil;
	}
	public String getFstEmpKey() {
		return fstEmpKey;
	}
	public void setFstEmpKey(String fstEmpKey) {
		this.fstEmpKey = fstEmpKey;
	}
	public String getFstEmpName() {
		return fstEmpName;
	}
	public void setFstEmpName(String fstEmpName) {
		this.fstEmpName = fstEmpName;
	}
	public String getSndYoil() {
		return sndYoil;
	}
	public void setSndYoil(String sndYoil) {
		this.sndYoil = sndYoil;
	}
	public String getSndEmpKey() {
		return sndEmpKey;
	}
	public void setSndEmpKey(String sndEmpKey) {
		this.sndEmpKey = sndEmpKey;
	}
	public String getSndEmpName() {
		return sndEmpName;
	}
	public void setSndEmpName(String sndEmpName) {
		this.sndEmpName = sndEmpName;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new ToStringCreator(this)
		.append("kwamok", kwamok)
		.append("state", state)
		.append("ipheiYMD", ipheiYMD)
		.append("bokheiYMD", bokheiYMD)
		.append("huheiYMD", huheiYMD)
		.append("setCnt", setCnt)
		.append("fstYoil", fstYoil)
		.append("fstEmpKey", fstEmpKey)
		.append("fstEmpName", fstEmpName)
		.append("sndYoil", sndYoil)
		.append("sndEmpKey", sndEmpKey)
		.append("sndEmpName", sndEmpName)
		.toString();
	}
}

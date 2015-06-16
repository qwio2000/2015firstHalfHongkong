package com.jeiglobal.hk.domain.member;

public class MemberInfoCheck {
	private String mkey;
	private String subj;
	private String stateCD;
	private String ipheiYMD;
	private String bokheiYMD;
	private String setCnt;
	private String mFstName;
	private String fstYoil;
	private String fstYoilNM;
	private String sndYoil;
	private String sndYoilNM;
	public String getMkey() {
		return mkey;
	}
	public void setMkey(String mkey) {
		this.mkey = mkey;
	}
	public String getSubj() {
		return subj;
	}
	public void setSubj(String subj) {
		this.subj = subj;
	}
	public String getStateCD() {
		return stateCD;
	}
	public void setStateCD(String stateCD) {
		this.stateCD = stateCD;
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
	public String getSetCnt() {
		return setCnt;
	}
	public void setSetCnt(String setCnt) {
		this.setCnt = setCnt;
	}
	public String getmFstName() {
		return mFstName;
	}
	public void setmFstName(String mFstName) {
		this.mFstName = mFstName;
	}
	public String getFstYoil() {
		return fstYoil;
	}
	public void setFstYoil(String fstYoil) {
		this.fstYoil = fstYoil;
	}
	public String getFstYoilNM() {
		return fstYoilNM;
	}
	public void setFstYoilNM(String fstYoilNM) {
		this.fstYoilNM = fstYoilNM;
	}
	public String getSndYoil() {
		return sndYoil;
	}
	public void setSndYoil(String sndYoil) {
		this.sndYoil = sndYoil;
	}
	public String getSndYoilNM() {
		return sndYoilNM;
	}
	public void setSndYoilNM(String sndYoilNM) {
		this.sndYoilNM = sndYoilNM;
	}
	@Override
	public String toString() {
		return "MemberInfoCheck [mkey=" + mkey + ", subj=" + subj
				+ ", stateCD=" + stateCD + ", ipheiYMD=" + ipheiYMD
				+ ", bokheiYMD=" + bokheiYMD + ", setCnt=" + setCnt
				+ ", mFstName=" + mFstName + ", fstYoil=" + fstYoil
				+ ", fstYoilNM=" + fstYoilNM + ", sndYoil=" + sndYoil
				+ ", sndYoilNM=" + sndYoilNM + "]";
	}

}

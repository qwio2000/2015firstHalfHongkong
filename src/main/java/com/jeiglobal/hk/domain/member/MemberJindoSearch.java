package com.jeiglobal.hk.domain.member;

public class MemberJindoSearch {
	private String mFstName;
	private String mLstName;
	private String setCnt;
	private String fstYoil;
	private String fstYoilNm;
	private String sndYoil;
	private String sndYoilNm;
	public String getmFstName() {
		return mFstName;
	}
	public void setmFstName(String mFstName) {
		this.mFstName = mFstName;
	}
	public String getmLstName() {
		return mLstName;
	}
	public void setmLstName(String mLstName) {
		this.mLstName = mLstName;
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
	public String getFstYoilNm() {
		return fstYoilNm;
	}
	public void setFstYoilNm(String fstYoilNm) {
		this.fstYoilNm = fstYoilNm;
	}
	public String getSndYoil() {
		return sndYoil;
	}
	public void setSndYoil(String sndYoil) {
		this.sndYoil = sndYoil;
	}
	public String getSndYoilNm() {
		return sndYoilNm;
	}
	public void setSndYoilNm(String sndYoilNm) {
		this.sndYoilNm = sndYoilNm;
	}
	@Override
	public String toString() {
		return "MemberJindoSearch [mFstName=" + mFstName + ", mLstName="
				+ mLstName + ", setCnt=" + setCnt + ", fstYoil=" + fstYoil
				+ ", fstYoilNm=" + fstYoilNm + ", sndYoil=" + sndYoil
				+ ", sndYoilNm=" + sndYoilNm + "]";
	}
}

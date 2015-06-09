package com.jeiglobal.hk.domain.manageInfo;
/**
 * 학적미발생 회원 정보
 * @author JSY
 *
 */
public class EmptyHakjukInfo {
	private String mkey;
	private String subj;
	private String mFstName;
	private String mLstName;
	private String sKey;
	private String sName;
	private String yoil;
	private String finalJindo;
	private String finalYMW;
	private String yoilNM;
	private String depidNM;
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
	public String getsKey() {
		return sKey;
	}
	public void setsKey(String sKey) {
		this.sKey = sKey;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getYoil() {
		return yoil;
	}
	public void setYoil(String yoil) {
		this.yoil = yoil;
	}
	public String getFinalJindo() {
		return finalJindo;
	}
	public void setFinalJindo(String finalJindo) {
		this.finalJindo = finalJindo;
	}
	public String getFinalYMW() {
		return finalYMW;
	}
	public void setFinalYMW(String finalYMW) {
		this.finalYMW = finalYMW;
	}
	public String getYoilNM() {
		return yoilNM;
	}
	public void setYoilNM(String yoilNM) {
		this.yoilNM = yoilNM;
	}
	public String getDepidNM() {
		return depidNM;
	}
	public void setDepidNM(String depidNM) {
		this.depidNM = depidNM;
	}
	@Override
	public String toString() {
		return "EmptyHakjukInfo [mkey=" + mkey + ", subj=" + subj
				+ ", mFstName=" + mFstName + ", mLstName=" + mLstName
				+ ", sKey=" + sKey + ", sName=" + sName + ", yoil=" + yoil
				+ ", finalJindo=" + finalJindo + ", finalYMW=" + finalYMW
				+ ", yoilNM=" + yoilNM + ", depidNM=" + depidNM + "]";
	}
	
}

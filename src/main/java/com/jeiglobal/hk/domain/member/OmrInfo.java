package com.jeiglobal.hk.domain.member;

public class OmrInfo {
	private String mKey;
	private String kwamok;
	private String yy;
	private String mm;
	private String wk;
	private String sort;
	private String yoil;
	private String yymmwk;
	private String jset;
	private String ind;
	private String pan;
	public String getmKey() {
		return mKey;
	}
	public void setmKey(String mKey) {
		this.mKey = mKey;
	}
	public String getKwamok() {
		return kwamok;
	}
	public void setKwamok(String kwamok) {
		this.kwamok = kwamok;
	}
	public String getYy() {
		return yy;
	}
	public void setYy(String yy) {
		this.yy = yy;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getWk() {
		return wk;
	}
	public void setWk(String wk) {
		this.wk = wk;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getYoil() {
		return yoil;
	}
	public void setYoil(String yoil) {
		this.yoil = yoil;
	}
	public String getYymmwk() {
		return yymmwk;
	}
	public void setYymmwk(String yymmwk) {
		this.yymmwk = yymmwk;
	}
	public String getJset() {
		return jset;
	}
	public void setJset(String jset) {
		this.jset = jset;
	}
	public String getInd() {
		return ind;
	}
	public void setInd(String ind) {
		this.ind = ind;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	@Override
	public String toString() {
		return "OmrInfo [mKey=" + mKey + ", kwamok=" + kwamok + ", yy=" + yy
				+ ", mm=" + mm + ", wk=" + wk + ", sort=" + sort + ", yoil="
				+ yoil + ", yymmwk=" + yymmwk + ", jset=" + jset + ", ind="
				+ ind + ", pan=" + pan + "]";
	}
}

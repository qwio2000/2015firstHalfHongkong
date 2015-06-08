package com.jeiglobal.hk.domain.member;
/**
 * 회원진도정보
 * @author JSY
 *
 */
public class MemberJindoInfo {
	private String kwamok;
	private String yy;
	private String mm;
	private String wk;
	private String sort;
	private String yoil;
	private String jset;
	private String ed;
	private String yetext;
	private String yoilnm;
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
	public String getJset() {
		return jset;
	}
	public void setJset(String jset) {
		this.jset = jset;
	}
	public String getEd() {
		return ed;
	}
	public void setEd(String ed) {
		this.ed = ed;
	}
	public String getYetext() {
		return yetext;
	}
	public void setYetext(String yetext) {
		this.yetext = yetext;
	}
	public String getYoilnm() {
		return yoilnm;
	}
	public void setYoilnm(String yoilnm) {
		this.yoilnm = yoilnm;
	}
	@Override
	public String toString() {
		return "MemberJindoInfo [kwamok=" + kwamok + ", yy=" + yy + ", mm="
				+ mm + ", wk=" + wk + ", sort=" + sort + ", yoil=" + yoil
				+ ", jset=" + jset + ", ed=" + ed + ", yetext=" + yetext
				+ ", yoilnm=" + yoilnm + "]";
	}
}

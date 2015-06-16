package com.jeiglobal.hk.domain.member;

public class JindoAdjustCheck {
	private String cngOptNM;
	private String cngCnt;
	private String dayCnt;
	public String getCngOptNM() {
		return cngOptNM;
	}
	public void setCngOptNM(String cngOptNM) {
		this.cngOptNM = cngOptNM;
	}
	public String getCngCnt() {
		return cngCnt;
	}
	public void setCngCnt(String cngCnt) {
		this.cngCnt = cngCnt;
	}
	public String getDayCnt() {
		return dayCnt;
	}
	public void setDayCnt(String dayCnt) {
		this.dayCnt = dayCnt;
	}
	@Override
	public String toString() {
		return "JindoAdjustCheck [cngOptNM=" + cngOptNM + ", cngCnt=" + cngCnt
				+ ", dayCnt=" + dayCnt + "]";
	}
	
}

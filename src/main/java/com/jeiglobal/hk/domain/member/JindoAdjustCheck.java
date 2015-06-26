package com.jeiglobal.hk.domain.member;

/**
 * 진도조정 체크정보
 * @author JSY
 *
 */
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

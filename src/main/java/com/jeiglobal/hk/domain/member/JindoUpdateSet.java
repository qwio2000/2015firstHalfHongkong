package com.jeiglobal.hk.domain.member;

/**
 * 진도 조정시 필요한 set정보
 * @author JSY
 *
 */
public class JindoUpdateSet {
	private String dung;
	private String casKey;
	private String casNset;
	public String getDung() {
		return dung;
	}
	public void setDung(String dung) {
		this.dung = dung;
	}
	public String getCasKey() {
		return casKey;
	}
	public void setCasKey(String casKey) {
		this.casKey = casKey;
	}
	public String getCasNset() {
		return casNset;
	}
	public void setCasNset(String casNset) {
		this.casNset = casNset;
	}
	@Override
	public String toString() {
		return "JindoUpdateSet [dung=" + dung + ", casKey=" + casKey
				+ ", casNset=" + casNset + "]";
	}
	
}

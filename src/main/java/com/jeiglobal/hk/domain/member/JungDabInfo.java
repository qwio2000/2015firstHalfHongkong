package com.jeiglobal.hk.domain.member;
/**
 * 정답 정보
 * @author JSY
 *
 */
public class JungDabInfo {
	private String jungKey;
	private String jungDab;
	private String jungHang;
	public String getJungKey() {
		return jungKey;
	}
	public void setJungKey(String jungKey) {
		this.jungKey = jungKey;
	}
	public String getJungDab() {
		return jungDab;
	}
	public void setJungDab(String jungDab) {
		this.jungDab = jungDab;
	}
	public String getJungHang() {
		return jungHang;
	}
	public void setJungHang(String jungHang) {
		this.jungHang = jungHang;
	}
	@Override
	public String toString() {
		return "JungDabInfo [jungKey=" + jungKey + ", jungDab=" + jungDab
				+ ", jungHang=" + jungHang + "]";
	}
}

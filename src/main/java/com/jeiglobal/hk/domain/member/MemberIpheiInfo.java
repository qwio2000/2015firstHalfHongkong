package com.jeiglobal.hk.domain.member;
/**
 * 회원입회정보
 * @author JSY
 *
 */
public class MemberIpheiInfo {
	private String kwamok;
	private String ipheiYMD;
	private String ipheiGubun;
	private String ipheiKind;
	private String jGradeCD;
	private String setCnt;
	private String fstYMD;
	private String fstEmpKey;
	private String fstEmpName;
	private String sndYMD;
	private String sndEmpKey;
	private String sndEmpName;

	public String getKwamok() {
		return kwamok;
	}
	public void setKwamok(String kwamok) {
		this.kwamok = kwamok;
	}
	public String getIpheiYMD() {
		return ipheiYMD;
	}
	public void setIpheiYMD(String ipheiYMD) {
		this.ipheiYMD = ipheiYMD;
	}
	public String getIpheiGubun() {
		return ipheiGubun;
	}
	public void setIpheiGubun(String ipheiGubun) {
		this.ipheiGubun = ipheiGubun;
	}
	public String getIpheiKind() {
		return ipheiKind;
	}
	public void setIpheiKind(String ipheiKind) {
		this.ipheiKind = ipheiKind;
	}
	public String getjGradeCD() {
		return jGradeCD;
	}
	public void setjGradeCD(String jGradeCD) {
		this.jGradeCD = jGradeCD;
	}
	public String getSetCnt() {
		return setCnt;
	}
	public void setSetCnt(String setCnt) {
		this.setCnt = setCnt;
	}
	public String getFstYMD() {
		return fstYMD;
	}
	public void setFstYMD(String fstYMD) {
		this.fstYMD = fstYMD;
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
	public String getSndYMD() {
		return sndYMD;
	}
	public void setSndYMD(String sndYMD) {
		this.sndYMD = sndYMD;
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
		return "MemberIpheiInfo [kwamok=" + kwamok + ", ipheiYMD=" + ipheiYMD
				+ ", ipheiGubun=" + ipheiGubun + ", ipheiKind=" + ipheiKind
				+ ", jGradeCD=" + jGradeCD + ", setCnt=" + setCnt + ", fstYMD="
				+ fstYMD + ", fstEmpKey=" + fstEmpKey + ", fstEmpName="
				+ fstEmpName + ", sndYMD=" + sndYMD + ", sndEmpKey="
				+ sndEmpKey + ", sndEmpName=" + sndEmpName + "]";
	}
	
}

package com.jeiglobal.hk.domain.member;
/**
 * 회원 데이터 붙일때 이용
 * @author JSY
 *
 */
public class ConcatData {
	private String tel1;
	private String tel2;
	private String tel3;
	private String gPhone1;
	private String gPhone2;
	private String gPhone3;
	private String ePhone1;
	private String ePhone2;
	private String ePhone3;
	private String mEmail1;
	private String mEmail2;
	private String gEmail1;
	private String gEmail2;
	@Override
	public String toString() {
		return "ConcatData [tel1=" + tel1 + ", tel2=" + tel2 + ", tel3=" + tel3
				+ ", gPhone1=" + gPhone1 + ", gPhone2=" + gPhone2
				+ ", gPhone3=" + gPhone3 + ", ePhone1=" + ePhone1
				+ ", ePhone2=" + ePhone2 + ", ePhone3=" + ePhone3
				+ ", mEmail1=" + mEmail1 + ", mEmail2=" + mEmail2
				+ ", gEmail1=" + gEmail1 + ", gEmail2=" + gEmail2 + "]";
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel3() {
		return tel3;
	}
	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	public String getgPhone1() {
		return gPhone1;
	}
	public void setgPhone1(String gPhone1) {
		this.gPhone1 = gPhone1;
	}
	public String getgPhone2() {
		return gPhone2;
	}
	public void setgPhone2(String gPhone2) {
		this.gPhone2 = gPhone2;
	}
	public String getgPhone3() {
		return gPhone3;
	}
	public void setgPhone3(String gPhone3) {
		this.gPhone3 = gPhone3;
	}
	public String getePhone1() {
		return ePhone1;
	}
	public void setePhone1(String ePhone1) {
		this.ePhone1 = ePhone1;
	}
	public String getePhone2() {
		return ePhone2;
	}
	public void setePhone2(String ePhone2) {
		this.ePhone2 = ePhone2;
	}
	public String getePhone3() {
		return ePhone3;
	}
	public void setePhone3(String ePhone3) {
		this.ePhone3 = ePhone3;
	}
	public String getmEmail1() {
		return mEmail1;
	}
	public void setmEmail1(String mEmail1) {
		this.mEmail1 = mEmail1;
	}
	public String getmEmail2() {
		return mEmail2;
	}
	public void setmEmail2(String mEmail2) {
		this.mEmail2 = mEmail2;
	}
	public String getgEmail1() {
		return gEmail1;
	}
	public void setgEmail1(String gEmail1) {
		this.gEmail1 = gEmail1;
	}
	public String getgEmail2() {
		return gEmail2;
	}
	public void setgEmail2(String gEmail2) {
		this.gEmail2 = gEmail2;
	}
}

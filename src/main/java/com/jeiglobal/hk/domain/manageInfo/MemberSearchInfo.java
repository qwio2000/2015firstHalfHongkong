package com.jeiglobal.hk.domain.manageInfo;
/**
 * 회원조회정보
 * @author JSY
 *
 */
public class MemberSearchInfo {
	private String mKey;
	private String kwamok;
	private String state;
	private String mFstName;
	private String sex;
	private String birthDay;
	private String lastIpBokheiDay;
	private String huheiDay;
	private String jisa;
	private String tel;
	private String addr;
	private String sKey;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getmFstName() {
		return mFstName;
	}

	public void setmFstName(String mFstName) {
		this.mFstName = mFstName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getLastIpBokheiDay() {
		return lastIpBokheiDay;
	}

	public void setLastIpBokheiDay(String lastIpBokheiDay) {
		this.lastIpBokheiDay = lastIpBokheiDay;
	}

	public String getHuheiDay() {
		return huheiDay;
	}

	public void setHuheiDay(String huheiDay) {
		this.huheiDay = huheiDay;
	}

	public String getJisa() {
		return jisa;
	}

	public void setJisa(String jisa) {
		this.jisa = jisa;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getsKey() {
		return sKey;
	}

	public void setsKey(String sKey) {
		this.sKey = sKey;
	}

	@Override
	public String toString() {
		return "MemberSearchInfo [mKey=" + mKey + ", kwamok=" + kwamok
				+ ", state=" + state + ", mFstName=" + mFstName + ", sex="
				+ sex + ", birthDay=" + birthDay + ", lastIpBokheiDay="
				+ lastIpBokheiDay + ", huheiDay=" + huheiDay + ", jisa=" + jisa
				+ ", tel=" + tel + ", addr=" + addr + ", sKey=" + sKey + "]";
	}

}

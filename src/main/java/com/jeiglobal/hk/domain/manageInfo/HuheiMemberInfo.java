package com.jeiglobal.hk.domain.manageInfo;
/**
 * 퇴회자리스트 정보
 * @author JSY
 *
 */
public class HuheiMemberInfo {
	private String empKey;
	private String empName;
	private String mKey;
	private String mFstName;
	private String subj;
	private String stateName;
	private String birthYMD;
	private String gradeName;
	private String huheiYMD;
	private String tel;
	private String addr;
	private String huheiSayuName;
	private String huheiGubunName;
	private String jin;

	public String getEmpKey() {
		return empKey;
	}

	public void setEmpKey(String empKey) {
		this.empKey = empKey;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getmKey() {
		return mKey;
	}

	public void setmKey(String mKey) {
		this.mKey = mKey;
	}

	public String getmFstName() {
		return mFstName;
	}

	public void setmFstName(String mFstName) {
		this.mFstName = mFstName;
	}

	public String getSubj() {
		return subj;
	}

	public void setSubj(String subj) {
		this.subj = subj;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getBirthYMD() {
		return birthYMD;
	}

	public void setBirthYMD(String birthYMD) {
		this.birthYMD = birthYMD;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getHuheiYMD() {
		return huheiYMD;
	}

	public void setHuheiYMD(String huheiYMD) {
		this.huheiYMD = huheiYMD;
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

	public String getHuheiSayuName() {
		return huheiSayuName;
	}

	public void setHuheiSayuName(String huheiSayuName) {
		this.huheiSayuName = huheiSayuName;
	}

	public String getHuheiGubunName() {
		return huheiGubunName;
	}

	public void setHuheiGubunName(String huheiGubunName) {
		this.huheiGubunName = huheiGubunName;
	}

	public String getJin() {
		return jin;
	}

	public void setJin(String jin) {
		this.jin = jin;
	}

	@Override
	public String toString() {
		return "HuheiMemberInfo [empKey=" + empKey + ", empName=" + empName
				+ ", mKey=" + mKey + ", mFstName=" + mFstName + ", subj="
				+ subj + ", stateName=" + stateName + ", birthYMD=" + birthYMD
				+ ", gradeName=" + gradeName + ", huheiYMD=" + huheiYMD
				+ ", tel=" + tel + ", addr=" + addr + ", huheiSayuName="
				+ huheiSayuName + ", huheiGubunName=" + huheiGubunName
				+ ", jin=" + jin + "]";
	}
}

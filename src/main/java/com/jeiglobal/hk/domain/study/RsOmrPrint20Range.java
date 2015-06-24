package com.jeiglobal.hk.domain.study;

public class RsOmrPrint20Range {
	private String sCode;
	private String odab1;
	private String odab2;
	private String odabNM;
	private String setList;

	public String getsCode() {
		return sCode;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode;
	}

	public String getOdab1() {
		return odab1;
	}

	public void setOdab1(String odab1) {
		this.odab1 = odab1;
	}

	public String getOdab2() {
		return odab2;
	}

	public void setOdab2(String odab2) {
		this.odab2 = odab2;
	}

	public String getOdabNM() {
		return odabNM;
	}

	public void setOdabNM(String odabNM) {
		this.odabNM = odabNM;
	}

	public String getSetList() {
		return setList;
	}

	public void setSetList(String setList) {
		this.setList = setList;
	}

	@Override
	public String toString() {
		return "RsOmrPrint20Odab4 [sCode=" + sCode + ", odab1=" + odab1
				+ ", odab2=" + odab2 + ", odabNM=" + odabNM + ", setList="
				+ setList + "]";
	}

}

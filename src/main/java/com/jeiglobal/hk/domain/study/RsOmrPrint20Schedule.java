package com.jeiglobal.hk.domain.study;

public class RsOmrPrint20Schedule {
	private String rYY;
	private String rMM;
	private String rWeek;
	private String rSet;
	private String yNM;

	public String getrYY() {
		return rYY;
	}

	public void setrYY(String rYY) {
		this.rYY = rYY;
	}

	public String getrMM() {
		return rMM;
	}

	public void setrMM(String rMM) {
		this.rMM = rMM;
	}

	public String getrWeek() {
		return rWeek;
	}

	public void setrWeek(String rWeek) {
		this.rWeek = rWeek;
	}

	public String getrSet() {
		return rSet;
	}

	public void setrSet(String rSet) {
		this.rSet = rSet;
	}

	public String getyNM() {
		return yNM;
	}

	public void setyNM(String yNM) {
		this.yNM = yNM;
	}

	@Override
	public String toString() {
		return "RsOmrPrint20Schedule [rYY=" + rYY + ", rMM=" + rMM
				+ ", rWeek=" + rWeek + ", rSet=" + rSet + ", yNM=" + yNM + "]";
	}

}

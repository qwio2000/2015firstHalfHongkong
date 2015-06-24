package com.jeiglobal.hk.domain.common;

import java.sql.Timestamp;

public class MemSubInfo {
	private String jisaCD;
	private String subjCD;
	private String subjNM;
	private int stateCD;
	private String sortCD;
	private String codeDes;
	private Timestamp regDate;
	private String regID;
	private Timestamp updDate;
	private String updID;
	
	public String getJisaCD() {
		return jisaCD;
	}
	public void setJisaCD(String jisaCD) {
		this.jisaCD = jisaCD;
	}
	public String getSubjCD() {
		return subjCD;
	}
	public void setSubjCD(String subjCD) {
		this.subjCD = subjCD;
	}
	public int getStateCD() {
		return stateCD;
	}
	public void setStateCD(int stateCD) {
		this.stateCD = stateCD;
	}
	public String getSortCD() {
		return sortCD;
	}
	public void setSortCD(String sortCD) {
		this.sortCD = sortCD;
	}
	public String getCodeDes() {
		return codeDes;
	}
	public void setCodeDes(String codeDes) {
		this.codeDes = codeDes;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getRegID() {
		return regID;
	}
	public void setRegID(String regID) {
		this.regID = regID;
	}
	public Timestamp getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}
	public String getUpdID() {
		return updID;
	}
	public void setUpdID(String updID) {
		this.updID = updID;
	}
	public String getSubjNM() {
		return subjNM;
	}
	public void setSubjNM(String subjNM) {
		this.subjNM = subjNM;
	}
	
}

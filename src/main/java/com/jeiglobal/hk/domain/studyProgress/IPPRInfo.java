package com.jeiglobal.hk.domain.studyProgress;

public class IPPRInfo {
	private String jisa;
	private String omrDate;
	private String hkey;	
	private String kwamok;
	private String omrPath;
	private String lang;
	
	public String getJisa() {
		return jisa;
	}
	public void setJisa(String jisa) {
		this.jisa = jisa;
	}
	public String getOmrDate() {
		return omrDate;
	}
	public void setOmrDate(String omrDate) {
		this.omrDate = omrDate;
	}	
	public String getHkey() {
		return hkey;
	}
	public void setHkey(String hkey) {
		this.hkey = hkey;
	}
	public String getKwamok() {
		return kwamok;
	}
	public void setKwamok(String kwamok) {
		this.kwamok = kwamok;
	}
	public String getOmrPath() {
		return omrPath;
	}
	public void setOmrPath(String omrPath) {
		this.omrPath = omrPath;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	@Override
	public String toString() {
		return "IPPRInfo [jisa=" + jisa + ", omrDate="+ omrDate + ", hkey=" + hkey + ", kwamok=" + kwamok + ", omrPath=" + omrPath+ ", lang=" + lang + "]";
	}
	
	
}

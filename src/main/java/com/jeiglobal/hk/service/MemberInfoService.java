package com.jeiglobal.hk.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeiglobal.hk.domain.common.AuthMemberInfo;
import com.jeiglobal.hk.domain.member.ConcatData;
import com.jeiglobal.hk.domain.member.DtlCD;
import com.jeiglobal.hk.domain.member.JungDabInfo;
import com.jeiglobal.hk.domain.member.MemberDetailInfo;
import com.jeiglobal.hk.domain.member.MemberHuheiInfo;
import com.jeiglobal.hk.domain.member.MemberIpgumInfo;
import com.jeiglobal.hk.domain.member.MemberIpheiInfo;
import com.jeiglobal.hk.domain.member.MemberJindoInfo;
import com.jeiglobal.hk.domain.member.MemberJindoSearch;
import com.jeiglobal.hk.domain.member.MemberJindoSearchInfo;
import com.jeiglobal.hk.domain.member.MemberKwamokInfo;
import com.jeiglobal.hk.domain.member.OmrInfo;
import com.jeiglobal.hk.repository.MemberInfoRepository;

@Service
public class MemberInfoService {
	
	@Autowired
	private MemberInfoRepository memberInfoRepository;

	public MemberDetailInfo getMemberDetailInfo(MemberDetailInfo memberDetailInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		map.put("mdi", memberDetailInfo);
		map.put("lang", getCookieValue(request, "LoginLang"));
		MemberDetailInfo mdi = memberInfoRepository.selectMemberDetailInfo(map);
		return mdi;
	}

	public List<DtlCD> getDtlCode(String jisaCD) {
		// TODO Auto-generated method stub
		return memberInfoRepository.selectDtlCodeList(jisaCD);
	}

	public List<MemberKwamokInfo> getMemberKwamokInfo(MemberDetailInfo memberDetailInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		map.put("mdi", memberDetailInfo);
		map.put("lang", getCookieValue(request, "LoginLang"));
		return memberInfoRepository.selectMemberKwamokInfo(map);
	}

	public int updateMemberDetailInfo(MemberDetailInfo memberDetailInfo, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		map.put("mdi", memberDetailInfo);
		map.put("ami", authMemberInfo);
		return memberInfoRepository.updateMemberDetailInfo(map);
	}

	public MemberDetailInfo concatData(MemberDetailInfo memberDetailInfo, ConcatData cd) {
		// TODO Auto-generated method stub
		memberDetailInfo.setTel((cd.getTel1().equals("")||cd.getTel1()==null)?"":cd.getTel1()+"-"+cd.getTel2()+"-"+cd.getTel3());
		memberDetailInfo.setgPhone((cd.getgPhone1().equals("")||cd.getgPhone1()==null)?"":cd.getgPhone1()+"-"+cd.getgPhone2()+"-"+cd.getgPhone3());
		memberDetailInfo.setePhone((cd.getePhone1().equals("")||cd.getePhone1()==null)?"":cd.getePhone1()+"-"+cd.getePhone2()+"-"+cd.getePhone3());
		memberDetailInfo.setmEmail((cd.getmEmail1().equals("")||cd.getmEmail1()==null)?"":cd.getmEmail1()+"@"+cd.getmEmail2());
		memberDetailInfo.setgEmail((cd.getgEmail1().equals("")||cd.getgEmail1()==null)?"":cd.getgEmail1()+"@"+cd.getgEmail2());
		return memberDetailInfo;
	}

	public String getMemberFirstIpheiDate(MemberDetailInfo memberDetailInfo, String searchKwamok) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mKey", memberDetailInfo.getmKey());
		return memberInfoRepository.selectFirstIpheiDate(map);
	}

	public List<MemberIpheiInfo> getMemberIpheiInfo(
			MemberDetailInfo memberDetailInfo, String searchKwamok, AuthMemberInfo authMemberInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mKey", memberDetailInfo.getmKey());
		map.put("kwamok", memberDetailInfo.getKwamok());
		map.put("searchKwamok", searchKwamok);
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("lang", getCookieValue(request, "LoginLang"));
		return memberInfoRepository.selectMemberIpheiInfoList(map);
	}

	public List<MemberHuheiInfo> getMemberHuheiInfo(
			MemberDetailInfo memberDetailInfo, String searchKwamok, AuthMemberInfo authMemberInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mKey", memberDetailInfo.getmKey());
		map.put("kwamok", memberDetailInfo.getKwamok());
		map.put("searchKwamok", searchKwamok);
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("lang", getCookieValue(request, "LoginLang"));
		return memberInfoRepository.selectMemberHuheiInfoList(map);
	}

	public List<MemberIpgumInfo> getMemberIpgumInfo(
			MemberDetailInfo memberDetailInfo, String searchKwamok, AuthMemberInfo authMemberInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mKey", memberDetailInfo.getmKey());
		map.put("kwamok", memberDetailInfo.getKwamok());
		map.put("searchKwamok", searchKwamok);
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("lang", getCookieValue(request, "LoginLang"));
		return memberInfoRepository.selectMemberIpgumInfoList(map);
	}

	public List<MemberJindoInfo> getMemberJindoInfo(
			MemberDetailInfo memberDetailInfo, String searchKwamok, AuthMemberInfo authMemberInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		HashMap<String, Object> map = new HashMap<>();
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("kwamok", memberDetailInfo.getKwamok());
		map.put("mKey", memberDetailInfo.getmKey());
		map.put("curYYYYMM", sdf.format(today));
		map.put("searchKwamok", searchKwamok);
		map.put("lang", getCookieValue(request, "LoginLang"));
		return memberInfoRepository.selectMemberJindoInfoList(map);
	}

	public HashMap<String, Object> getMemberJindoSearch(
			MemberDetailInfo memberDetailInfo, String searchYY, String searchMM, String searchKwamok, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		HashMap<String, Object> map = new HashMap<>();
		if(searchYY != null && searchMM != null){
			cal.set(Integer.parseInt(searchYY), Integer.parseInt(searchMM)-1, 1);
		}else{
			searchYY = sdf.format(cal.getTime()).substring(0, 4);
			searchMM = sdf.format(cal.getTime()).substring(5);
		}
		cal.add(Calendar.MONTH, -5);
		String startYYYY = sdf.format(cal.getTime()).substring(0, 4);
		String startMM = sdf.format(cal.getTime()).substring(5);
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("startYYYY", startYYYY);
		map.put("startMM", startMM);
		map.put("searchYY", searchYY);
		map.put("searchMM", searchMM);
		String[][][] bsArray = new String[12][5][2];
		String[][][] indArray = new String[12][5][2];
		for (int i = 0; i < 12; i++) {
			map.put("mKey", memberDetailInfo.getmKey());
			map.put("kwamok", memberDetailInfo.getKwamok());
			map.put("searchKwamok", searchKwamok);
			map.put("yyyy", startYYYY);
			map.put("mm", startMM);
			List<MemberJindoSearchInfo> mjsi = memberInfoRepository.selectMemberJindoSearchInfo(map);
			for (int j = 0; j < mjsi.size(); j++) {
				bsArray[i][Integer.parseInt(mjsi.get(j).getWk())-1][0] = mjsi.get(j).getBs();
				indArray[i][Integer.parseInt(mjsi.get(j).getWk())-1][0] = mjsi.get(j).getInd();
			}
			cal.add(Calendar.MONTH, 1);
			startYYYY = sdf.format(cal.getTime()).substring(0, 4);
			startMM = sdf.format(cal.getTime()).substring(5);
		}
		map.put("bsArray", bsArray);
		map.put("indArray", indArray);
		return map;
	}

	public MemberJindoSearch getMemberInfo(MemberDetailInfo memberDetailInfo, String searchKwamok, AuthMemberInfo authMemberInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mKey", memberDetailInfo.getmKey());
		map.put("kwamok", memberDetailInfo.getKwamok());
		map.put("searchKwamok", searchKwamok);
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("lang", getCookieValue(request, "LoginLang"));
		return memberInfoRepository.selectMemberJindoSearch(map);
	}

	public HashMap<String, Object> getOmrGichoList(MemberDetailInfo memberDetailInfo,
			String searchYY, String searchKwamok, AuthMemberInfo authMemberInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		HashMap<String, Object> map = new HashMap<>();
		map.put("hkey", memberDetailInfo.getmKey());
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("lang", getCookieValue(request, "LoginLang"));
		if(searchYY != null){
			map.put("searchYY", searchYY);
		}else{
			map.put("searchYY", sdf.format(cal.getTime()));
		}
		if(searchKwamok != null){
			map.put("kwamok", searchKwamok);
		}else{
			map.put("kwamok", memberDetailInfo.getKwamok());
		}
		map.put("omrGichoList", memberInfoRepository.selectOmrGichoList(map));
		return map;
	}

	public OmrInfo getMemberJindanCheck(MemberDetailInfo memberDetailInfo) {
		// TODO Auto-generated method stub
		return memberInfoRepository.selectMemberJindanCheck(memberDetailInfo);
	}

	public List<JungDabInfo> getJungDabList(String kwamok, String dung, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("kwamok", kwamok);
		map.put("dung", dung);
		map.put("jisa", authMemberInfo.getJisaCD());
		return memberInfoRepository.selectJungDabList(map);
	}

	public int getTotMunCount(String kwamok, String dung, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("kwamok", kwamok);
		map.put("dung", dung);
		map.put("jisa", authMemberInfo.getJisaCD());
		return memberInfoRepository.selectTotMunCount(map);
	}

	public String getMemberOmrCheck(MemberDetailInfo memberDetailInfo) {
		// TODO Auto-generated method stub
		return memberInfoRepository.selectMemberOmrCheck(memberDetailInfo);
	}

	public void insertMemberOmrGicho(MemberDetailInfo memberDetailInfo,
			String dung, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		map.put("mdi", memberDetailInfo);
		map.put("dung", dung);
		map.put("ami", authMemberInfo);
		memberInfoRepository.insertOmrGicho(map);
	}

	public void insertOdabInfo(MemberDetailInfo memberDetailInfo, String dung,
			String errLst, int errTot, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String odabNo ="";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("omrDate", sdf.format(today));
		map.put("hKey", memberDetailInfo.getmKey());
		map.put("kwamok", memberDetailInfo.getKwamok());
		map.put("dung", dung);
		String[] splitErrList = errLst.split("\\$");
		for (int i = 1; i < splitErrList.length+1; i++) {
			if(splitErrList[i].equals("#"))break;
			String munNo = splitErrList[i].trim();
			if((memberDetailInfo.getKwamok().equals("EM")|| memberDetailInfo.getKwamok().equals("KM"))&& dung.compareTo("C")>0){
				String[] splitMunNo = munNo.split("\\|");
				munNo = splitMunNo[0].trim();
				odabNo = splitMunNo[1].trim();
			}
			map.put("munNo", munNo);
			map.put("odabNo", odabNo);
			memberInfoRepository.insertOdabInfo(map);
		}
	}

	public String omrBan(AuthMemberInfo authMemberInfo,
			MemberDetailInfo memberDetailInfo, String dung) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mdi", memberDetailInfo);
		map.put("ami", authMemberInfo);
		map.put("dung", dung);
		String result = memberInfoRepository.omrBan(map);
		return result;
	}
	
	private String getCookieValue(HttpServletRequest request, String type) {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies();
		if(cookies != null){	
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(type)){
					try {
						return URLDecoder.decode(cookie.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						return null;
					}
				}
			}
		}
		return null;
	}

	public String getKwamokName(String jisa, String kwamok,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		map.put("jisa", jisa);
		map.put("kwamok", kwamok);
		map.put("lang", getCookieValue(request, "LoginLang"));
		return memberInfoRepository.selectKwamokName(map);
	}


	public List<DtlCD> getHuheiSayuList(AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		return memberInfoRepository.selectHuheiSayuList(authMemberInfo.getJisaCD());
	}

}

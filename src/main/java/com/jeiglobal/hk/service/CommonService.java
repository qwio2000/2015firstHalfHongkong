package com.jeiglobal.hk.service;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeiglobal.hk.domain.common.AuthMemberInfo;
import com.jeiglobal.hk.repository.CommonRepository;

@Service
public class CommonService {
	
	@Autowired
	private CommonRepository commonRepository;
	
	/**
	 * 입회 퇴회 가능날자
	 * @param authMemberInfo
	 * @return
	 */
	public List<String> getAvailableDateList(AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		HashMap<String, Object> map = new HashMap<>();
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("empKeyLvCD", authMemberInfo.getEmpKeyLvCD());
		map.put("yyyy", sdf.format(cal.getTime()).substring(0, 4));
		map.put("mm", sdf.format(cal.getTime()).substring(5));
		return commonRepository.findDateSetting(map);
	}
	/**
	 * 교육원 정보 가져오기 depid,deptNM
	 * @param authMemberInfo
	 * @return
	 */
	public List<Map<String,Object>> getDepInfoList(AuthMemberInfo authMemberInfo){
		HashMap<String, Object> map = new HashMap<>();
		map.put("empKeyLvCD", authMemberInfo.getEmpKeyLvCD());
		map.put("jisaCD", authMemberInfo.getJisaCD());
		map.put("depid1", authMemberInfo.getDepid1());
		
		return commonRepository.findDepart(map);
	}
	
	
	/**
	 * 교육원에서 사용할수있는 과목리스트 subj
	 * @param jisaCD
	 * @param depid1
	 * @return
	 */
	public List<String> getKwamokList(HttpServletRequest request, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD", authMemberInfo.getJisaCD());
		map.put("depid1", authMemberInfo.getDepid1());
		map.put("depid2", authMemberInfo.getDepid2());
		map.put("empKeyLvCD", authMemberInfo.getEmpKeyLvCD());
		map.put("lang", getCookieValue(request, "LoginLang"));
		return commonRepository.selectKwamokList(map);
	}

	/**
	 * 교육원 교실정보 리스트 empKey, empName
	 * @param jisaCD
	 * @param depid1
	 * @return
	 */
	public List<Map<String,Object>> getClassList(AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD", authMemberInfo.getJisaCD());
		map.put("depid1", authMemberInfo.getDepid1());
		return commonRepository.selectClassList(map);
	}
	
	/**
	 * 쿠키 값 가져오는 메서드 
	 * @param request
	 * @param type
	 * @return
	 */
	public String getCookieValue(HttpServletRequest request, String type) {
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
}

package com.jeiglobal.hk.service;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.hk.domain.common.*;
import com.jeiglobal.hk.domain.ipgum.*;
import com.jeiglobal.hk.repository.*;

@Service
public class IpgumService {
	
	@Autowired
	private IpgumRepository ipgumRepository;
	/**
	 * 입금 회비 총 합계
	 * @param empKey
	 * @param kwamok
	 * @param existCD
	 * @param chkVal
	 * @param startDay
	 * @param endDay
	 * @param authMemberInfo
	 * @param request 
	 * @return
	 */
	public TotalHeibi getTotHeibi(String empKey, String kwamok, String mKey, String existCD,
			String chkVal, String startDay, String endDay,
			AuthMemberInfo authMemberInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empKey", empKey);
		map.put("kwamok", kwamok);
		map.put("existCD", existCD);
		map.put("chkVal", chkVal);
		map.put("mKey", mKey);
		map.put("startDay", startDay);
		map.put("endDay", endDay);
		map.put("jisaCD", authMemberInfo.getJisaCD());
		map.put("depid1", authMemberInfo.getDepid1());
		map.put("depid2", authMemberInfo.getDepid2());
		return ipgumRepository.selectTotHeibi(map);
	}
	
	
	/**
	 * 입금 정보 리스트 가져오는 메서드
	 * @param empKey
	 * @param kwamok
	 * @param existCD
	 * @param chkVal
	 * @param startDay
	 * @param endDay
	 * @param authMemberInfo
	 * @param request
	 * @return
	 */
	public List<IpgumInfo> getIpgumList(String empKey, String kwamok, String mKey,
			String existCD, String chkVal, String startDay, String endDay,
			AuthMemberInfo authMemberInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empKey", empKey);
		map.put("kwamok", kwamok);
		map.put("existCD", existCD);
		map.put("chkVal", chkVal);
		map.put("startDay", startDay);
		map.put("endDay", endDay);
		map.put("mKey", mKey);
		map.put("jisaCD", authMemberInfo.getJisaCD());
		map.put("depid1", authMemberInfo.getDepid1());
		map.put("depid2", authMemberInfo.getDepid2());
		map.put("lang", getCookieValue(request, "LoginLang"));
		return ipgumRepository.selectIpgumInfoList(map);
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

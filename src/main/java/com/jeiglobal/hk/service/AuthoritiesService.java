package com.jeiglobal.hk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeiglobal.hk.domain.common.MemberAuthority;
import com.jeiglobal.hk.domain.common.AuthMemberInfo;
import com.jeiglobal.hk.repository.AuthoritiesRepository;
import com.jeiglobal.hk.repository.MssqlRepository;

@Service
public class AuthoritiesService {
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	@Autowired
	private MssqlRepository mssqlRepository;
	
	public AuthMemberInfo findMemberById(String memberId){
		return authoritiesRepository.findMemberById(memberId);
	}
	
	public List<MemberAuthority> findPermissionById(String memberId){
		return authoritiesRepository.findPermissionById(memberId);
	}
	
	public String selectEncryptPassWord(String password){
		return mssqlRepository.selectEncryptPassWord(password);
	}
	
	public void updateEncodeCookieById(String memberId,String encodeCookie){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", memberId);
		map.put("encodeCookie", encodeCookie);
		
		authoritiesRepository.updateEncodeCookieById(map);
	}
	
	public long countMemberByIdAndEncodeCookie(String memberId,String encodeCookie){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("memberId", memberId);
		map.put("encodeCookie", encodeCookie);
		
		return authoritiesRepository.countMemberByIdAndEncodeCookie(map);
	}
	
}

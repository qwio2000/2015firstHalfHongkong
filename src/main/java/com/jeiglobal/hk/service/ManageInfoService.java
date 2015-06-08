package com.jeiglobal.hk.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.hk.domain.common.*;
import com.jeiglobal.hk.domain.manageInfo.*;
import com.jeiglobal.hk.repository.*;

@Service
public class ManageInfoService {
	
	@Autowired
	private ManageInfoRepository manageInfoRepository;

	@Autowired
	private MssqlRepository mssqlRepository;

	public List<MemberSearchInfo> getMemberSearchInfo(String type,
			String searchWord, String birthDay, String check, String orderby,
			AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("searchWord", searchWord);
		map.put("birthDay", birthDay);
		map.put("check", check);
		map.put("orderby", orderby);
		map.put("ami", authMemberInfo);
		return manageInfoRepository.selectMemberSearchInfo(map);
	}
	public List<KoreaMemberInfo> getKorMemberSearchInfo(String type,
			String searchWord, String birthDay, String state, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("searchWord", searchWord);
		map.put("birthDay", birthDay);
		map.put("state", state);
		map.put("ami", authMemberInfo);
		return mssqlRepository.selectKoreaMemberSearch(map);
	}
	public List<HuheiMemberInfo> getHuheiMemberList(String empKey, String kwamok,
			String startBirthDate, String endBirthDate, String hu_skey,
			String startHuheiDate, String endHuheiDate,
			AuthMemberInfo authMemberInfo, String loginLang) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empKey", empKey);
		map.put("kwamok", kwamok);
		map.put("startBirthDate", startBirthDate);
		map.put("endBirthDate", endBirthDate);
		map.put("hu_skey", hu_skey);
		map.put("startHuheiDate", startHuheiDate);
		map.put("endHuheiDate", endHuheiDate);
		map.put("ami", authMemberInfo);
		map.put("loginLang", loginLang);
		return manageInfoRepository.selectHuheiMemberList(map);
	}
	public List<StudyState> getStudyStateList(String empKey, String searchDay,
			String loginLang, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		String yy = searchDay.substring(0,4);
		String mm = searchDay.substring(5);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", yy);
		map.put("month", mm);
		map.put("skey", empKey);
		map.put("ami", authMemberInfo);
		map.put("lang", loginLang);
		return manageInfoRepository.selectStudyStateList(map);
	}
	
}

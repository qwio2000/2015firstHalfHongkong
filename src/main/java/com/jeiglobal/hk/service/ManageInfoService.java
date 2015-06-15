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
	/**
	 * 해외 회원 조회
	 * @param type
	 * @param searchWord
	 * @param birthDay
	 * @param check
	 * @param orderby
	 * @param authMemberInfo
	 * @return List<MemberSearchInfo>
	 */
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
	/**
	 * 한국 회원 조회
	 * @param type
	 * @param searchWord
	 * @param birthDay
	 * @param state
	 * @param authMemberInfo
	 * @return List<KoreaMemberInfo>
	 */
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
	/**
	 * 휴회 멤버 리스트
	 * @param empKey
	 * @param kwamok
	 * @param startBirthDate
	 * @param endBirthDate
	 * @param hu_skey
	 * @param startHuheiDate
	 * @param endHuheiDate
	 * @param authMemberInfo
	 * @param loginLang
	 * @return List<HuheiMemberInfo>
	 */
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
	/**
	 * 학습 현황 조회
	 * @param empKey
	 * @param searchDay
	 * @param loginLang
	 * @param authMemberInfo
	 * @return List<StudyState>
	 */
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
	/**
	 * 학적 미발생 회원 조회
	 * @param empKey
	 * @param authMemberInfo
	 * @param loginLang
	 * @return List<EmptyHakjukInfo>
	 */
	public List<EmptyHakjukInfo> getEmptyHakjukInfo(String empKey,
			AuthMemberInfo authMemberInfo, String loginLang) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empKey", empKey);
		map.put("depid1", authMemberInfo.getDepid1());
		map.put("depid2", authMemberInfo.getDepid2());
		map.put("jisaCD", authMemberInfo.getJisaCD());
		map.put("loginLang", loginLang);
		return manageInfoRepository.selectEmptyHakjukInfo(map);
	}
	
}

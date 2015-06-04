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
	 * 입금 회비 총 합계
	 * @param authMemberInfo 
	 * @param empKey
	 * @param kwamok
	 * @param existCD
	 * @param chkVal
	 * @param startDay
	 * @param endDay
	 * @param authMemberInfo
	 * @return
	 */
	/*public TotalHeibi getTotHeibi(String empKey, String kwamok, String mKey, String existCD,
			String chkVal, String startDay, String endDay,
			AuthMemberInfo authMemberInfo) {
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
	}*/

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
	
}

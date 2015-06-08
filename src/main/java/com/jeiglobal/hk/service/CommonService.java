package com.jeiglobal.hk.service;

import java.text.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.hk.domain.common.*;
import com.jeiglobal.hk.repository.*;

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
		Map<String, Object> map = new HashMap<>();
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
		Map<String, Object> map = new HashMap<>();
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
	public List<String> getKwamokList(String loginLang, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD", authMemberInfo.getJisaCD());
		map.put("depid1", authMemberInfo.getDepid1());
		map.put("depid2", authMemberInfo.getDepid2());
		map.put("empKeyLvCD", authMemberInfo.getEmpKeyLvCD());
		map.put("lang", loginLang);
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
	
}

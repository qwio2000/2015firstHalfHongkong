package com.jeiglobal.hk.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeiglobal.hk.domain.common.AuthMemberInfo;
import com.jeiglobal.hk.repository.CommonRepository;

@Service
public class CommonService {
	
	@Autowired
	private CommonRepository commonRepository;
	
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
}

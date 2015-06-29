package com.jeiglobal.hk.repository;

import java.util.List;
import java.util.Map;

import com.jeiglobal.hk.domain.common.Comcode;
import com.jeiglobal.hk.domain.common.JNDate;


@PrimaryRepositoryAnnoInterface
public interface CommonRepository {
	public List<String> findDateSetting(Map<String,Object> map);
	
	public List<Map<String,Object>> findDepart(Map<String,Object> map);
	
	public List<String> selectKwamokList(Map<String, Object> map);

	public List<Map<String, Object>> selectClassList(Map<String, Object> map);
	
	public List<Comcode> selectCodeDtl(Map<String, Object> map);

	public String selectHuheiYMW(Map<String,Object> map);
	
	public List<String> selectFstDay(Map<String,Object> map);
	
	public long countJndate(Map<String,Object> map);
	
	public String selectNewKey(Map<String,Object> map);
	
	public JNDate selectJNDate(Map<String,Object> map);
	
	public JNDate findOneJNDateOrderByYearPwolWeek(Map<String,Object> map);
	
	public Map<String,Object> selectOneJNDateGroupByPYearPWolWeek(Map<String,Object> map);
	
	public Map<String,Object> selectOneJNDateWhereYWWGroupByPYearPWolWeek(Map<String,Object> map);
	
}

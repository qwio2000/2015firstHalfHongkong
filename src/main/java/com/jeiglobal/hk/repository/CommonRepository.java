package com.jeiglobal.hk.repository;

import java.util.List;
import java.util.Map;


@PrimaryRepositoryAnnoInterface
public interface CommonRepository {
	public List<String> findDateSetting(Map<String,Object> map);
	
	public List<Map<String,Object>> findDepart(Map<String,Object> map);
	
	public List<String> selectKwamokList(Map<String, Object> map);
}

package com.jeiglobal.hk.repository;

import java.util.*;

import com.jeiglobal.hk.domain.manageInfo.*;

@AnotherRepositoryAnnoInterface
public interface MssqlRepository {
	public String selectEncryptPassWord(String password);

	public List<KoreaMemberInfo> selectKoreaMemberSearch(Map<String, Object> map);
}

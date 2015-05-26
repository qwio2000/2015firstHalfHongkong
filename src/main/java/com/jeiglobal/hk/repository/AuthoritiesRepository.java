package com.jeiglobal.hk.repository;

import java.util.List;
import java.util.Map;

import com.jeiglobal.hk.domain.common.MemberAuthority;
import com.jeiglobal.hk.domain.common.AuthMemberInfo;


@PrimaryRepositoryAnnoInterface
public interface AuthoritiesRepository {
	public AuthMemberInfo findMemberById(String memberId);
	
	public List<MemberAuthority> findPermissionById(String memberId);
	
	public List<Map<String, Object>> findAllUrlAndRole();
	
	public void updateEncodeCookieById(Map<String,Object> map);
	
	public long countMemberByIdAndEncodeCookie(Map<String,Object> map);
}

package com.jeiglobal.hk.repository;

import java.util.List;

import com.jeiglobal.hk.domain.user.Authorities;
import com.jeiglobal.hk.domain.user.Member;



@PrimaryRepositoryAnnoInterface
public interface AuthoritiesRepository {
	public Member findMemberById(String memberId);
	
	public List<Authorities> findPermissionById(String memberId);
}

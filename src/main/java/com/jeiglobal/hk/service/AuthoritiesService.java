package com.jeiglobal.hk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeiglobal.hk.domain.user.Authorities;
import com.jeiglobal.hk.domain.user.Member;
import com.jeiglobal.hk.repository.AuthoritiesRepository;

@Service
public class AuthoritiesService {
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	public Member findMemberById(String memberId){
		return authoritiesRepository.findMemberById(memberId);
	}
	
	public List<Authorities> findPermissionById(String memberId){
		return authoritiesRepository.findPermissionById(memberId);
	}
	
}

package com.jeiglobal.hk.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jeiglobal.hk.domain.user.Authorities;
import com.jeiglobal.hk.domain.user.Member;
import com.jeiglobal.hk.service.AuthoritiesService;
/**
 * @Compoent를 선언해줘야 스캔할때 스프링이 bean으로 등록해줌
 * @author Administrator
 *
 */
@Component
public class MyCustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
		Member member =  authoritiesService.findMemberById(authToken.getName());
		if(member == null){
			throw new UsernameNotFoundException(authToken.getName());
		}
		
		if(!matchPassword(member.getMemberPassword(),authToken.getCredentials())){
			throw new BadCredentialsException("계정ID와 비밀번호가 맞지않습니다.");
		}
		
		List<GrantedAuthority> authorities = getAuthorities(member.getMemberId());
		return new UsernamePasswordAuthenticationToken(new Member(member.getMemberId(),member.getMemberPassword(),true),null,authorities);
	}

	private List<GrantedAuthority> getAuthorities(String memberId) {
		List<Authorities> perms = authoritiesService.findPermissionById(memberId);
		if (perms == null)
			return Collections.emptyList();

		List<GrantedAuthority> authorities = new ArrayList<>(perms.size());
		for (Authorities perm : perms) {
			authorities.add(new SimpleGrantedAuthority(perm.getAuthority()));
		}
		return authorities;
	}
	
	private boolean matchPassword(String memberPassword, Object credentials) {
		return memberPassword.equals(credentials);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}

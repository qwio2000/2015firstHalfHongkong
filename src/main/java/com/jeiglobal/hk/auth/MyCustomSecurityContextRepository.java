package com.jeiglobal.hk.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import com.jeiglobal.hk.domain.user.Authorities;
import com.jeiglobal.hk.domain.user.Member;
import com.jeiglobal.hk.service.AuthoritiesService;


@Component
public class MyCustomSecurityContextRepository implements SecurityContextRepository{
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Override
	public SecurityContext loadContext(
			HttpRequestResponseHolder requestResponseHolder) {
		
		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
		String authValue = getAuthCookieValue(requestResponseHolder.getRequest());
		if(authValue != null){
			String userName = authValue;
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			List<Authorities> memberAuthories = authoritiesService.findPermissionById(userName);
			
			for(int i = 0; i < memberAuthories.size(); i++){
				authorities.add(new SimpleGrantedAuthority(memberAuthories.get(i).getAuthority()));
			}
			
			Member member = new Member(userName,"",true);
			Authentication authentication = new UsernamePasswordAuthenticationToken(member,"",authorities);
			ctx.setAuthentication(authentication);
		}
		
		return ctx;
	}

	@Override
	public boolean containsContext(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public void saveContext(SecurityContext context,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	private String getAuthCookieValue(HttpServletRequest request){
		Cookie[] cookies =  request.getCookies();
		if(cookies == null){
			return null;
		}
		
		for (Cookie cookie : cookies) {
			if("AUTH".equals(cookie.getName())){
				try {
					return URLDecoder.decode(cookie.getValue(),"utf-8");
				} catch (UnsupportedEncodingException e) {
					return null;
				}
			}
		}
		
		return null;
	}
	
	
}

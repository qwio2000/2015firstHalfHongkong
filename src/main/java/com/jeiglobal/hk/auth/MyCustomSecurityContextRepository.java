package com.jeiglobal.hk.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.jeiglobal.hk.domain.common.MemberAuthority;
import com.jeiglobal.hk.domain.common.AuthMemberInfo;
import com.jeiglobal.hk.service.AuthoritiesService;


@Component
public class MyCustomSecurityContextRepository implements SecurityContextRepository{
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Override
	public SecurityContext loadContext(
			HttpRequestResponseHolder requestResponseHolder) {
		
		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
		Map<String,Object> map = new HashMap<String, Object>();
		
		map = getAuthCookieValue(requestResponseHolder.getRequest());
		
		if(map != null && !map.isEmpty() && map.containsKey("AUTHId") && map.containsKey("AUTHKey")){
			String userName = map.get("AUTHId").toString();
			String encodeCookie = map.get("AUTHKey").toString();
			
			long cnt = authoritiesService.countMemberByIdAndEncodeCookie(userName, encodeCookie);
			
			if(cnt == 1){
				List<GrantedAuthority> authorities = new ArrayList<>();
				
				List<MemberAuthority> memberAuthories = authoritiesService.findPermissionById(userName);
				
				for(int i = 0; i < memberAuthories.size(); i++){
					authorities.add(new SimpleGrantedAuthority(memberAuthories.get(i).getAuthority()));
				}
				
				AuthMemberInfo member = authoritiesService.findMemberById(userName);
				member.setMemberPassword("");
				member.setEncodeCookie("");
				
				Authentication authentication = new UsernamePasswordAuthenticationToken(member,"",authorities);
				ctx.setAuthentication(authentication);
			}
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
	
	private Map<String,Object> getAuthCookieValue(HttpServletRequest request){
		Cookie[] cookies =  request.getCookies();
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(cookies == null){
			return null;
		}
		
		for (Cookie cookie : cookies) {
			if("AUTHId".equals(cookie.getName())){
				try {
					map.put("AUTHId",URLDecoder.decode(cookie.getValue(),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					return null;
				}
			}else if("AUTHKey".equals(cookie.getName())){
				try {
					map.put("AUTHKey",URLDecoder.decode(cookie.getValue(),"utf-8"));
				} catch (UnsupportedEncodingException e) {
					return null;
				}
			}
		}
		
		return map;
	}
	
	
}

package com.jeiglobal.hk.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.jeiglobal.hk.domain.common.AuthMemberInfo;
import com.jeiglobal.hk.service.AuthoritiesService;




/**
 * 인증성공했을때 리다이렉트 시켜줌 요청했던 returl주소로
 * @author Administrator
 *
 */
@Component
public class MyCustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	final static String DEFAULT_INDEX_URL = "/memberCard";
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		String loginLang = request.getParameter("loginLang");
		
		if(loginLang == null || loginLang.isEmpty()){
			loginLang = "E";
		}
				
		addAuthCookie(response, authentication,loginLang);
		String retUrl = request.getParameter("returl");
		if(retUrl == null || retUrl.isEmpty()){
			response.sendRedirect(request.getContextPath()+DEFAULT_INDEX_URL);
			return;
		}
		
		response.sendRedirect(retUrl);
	}
	
	
	private void addAuthCookie(HttpServletResponse response,Authentication authentication,String loginLang){
		AuthMemberInfo member = (AuthMemberInfo)authentication.getPrincipal();
		
		StandardPasswordEncoder standrdPasswordEncoder = new StandardPasswordEncoder();
		
		String authId = member.getMemberId();
		String authKey = standrdPasswordEncoder.encode(authId);
		
		authoritiesService.updateEncodeCookieById(authId,authKey);
		
		try {
			Cookie cookie = new Cookie("AUTHKey",URLEncoder.encode(authKey,"utf-8"));
			cookie.setPath("/");
			response.addCookie(cookie);
			
			Cookie cookie1 = new Cookie("AUTHId",URLEncoder.encode(authId,"utf-8"));
			cookie1.setPath("/");
			response.addCookie(cookie1);
			
			Cookie cookie2 = new Cookie("LoginLang",URLEncoder.encode(loginLang,"utf-8"));
			cookie2.setPath("/");
			response.addCookie(cookie2);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}

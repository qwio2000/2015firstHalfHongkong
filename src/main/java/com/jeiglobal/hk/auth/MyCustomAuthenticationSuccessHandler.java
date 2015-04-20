package com.jeiglobal.hk.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.jeiglobal.hk.domain.user.Member;



/**
 * 인증성공했을때 리다이렉트 시켜줌 요청했던 returl주소로
 * @author Administrator
 *
 */
@Component
public class MyCustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	final static String DEFAULT_INDEX_URL = "/index";
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		addAuthCookie(response, authentication);
		String retUrl = request.getParameter("returl");
		if(retUrl == null || retUrl.isEmpty()){
			response.sendRedirect(request.getContextPath()+DEFAULT_INDEX_URL);
			return;
		}
		
		response.sendRedirect(retUrl);
	}
	
	
	private void addAuthCookie(HttpServletResponse response,Authentication authentication){
		Member member = (Member)authentication.getPrincipal();
		String cookieValue = member.getMemberId();
		
		try {
			Cookie cookie = new Cookie("AUTH",URLEncoder.encode(cookieValue,"utf-8"));
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}

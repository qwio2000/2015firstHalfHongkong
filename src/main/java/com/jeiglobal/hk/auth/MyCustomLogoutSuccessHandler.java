package com.jeiglobal.hk.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.jeiglobal.hk.service.AuthoritiesService;

@Component
public class MyCustomLogoutSuccessHandler implements LogoutSuccessHandler {
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		Cookie cookie = new Cookie("AUTHId","");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		Cookie cookie1 = new Cookie("AUTHKey","");
		cookie1.setPath("/");
		cookie1.setMaxAge(0);
		response.addCookie(cookie1);
		
		Cookie cookie2 = new Cookie("LoginLang","");
		cookie2.setPath("/");
		cookie2.setMaxAge(0);
		response.addCookie(cookie2);
		
		response.sendRedirect(request.getContextPath()+"/login");
	}

}

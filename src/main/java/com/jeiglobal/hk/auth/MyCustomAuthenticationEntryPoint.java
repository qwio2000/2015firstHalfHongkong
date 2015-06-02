package com.jeiglobal.hk.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
/**
 * 로그인 폼으로 리다이렉트할때 현재 경로를 returl 파라미터로 붙여서 보내주는 커스텀 클레스 714줄
 * @author Administrator
 *
 */
@Component
public class MyCustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private String loginFormPath;
	
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		
		if(loginFormPath == null || loginFormPath.isEmpty()){
			setLoginFormPath("/login");
		}
		String redirectUrl = UrlUtils.buildRequestUrl(request);
		String encodedUrl = response.encodeRedirectURL(redirectUrl);
		if("/".equals(encodedUrl)){
			response.sendRedirect(request.getContextPath()+loginFormPath);
		}else{
			response.sendRedirect(request.getContextPath()+loginFormPath+"?returl="+encodedUrl);
		}
	}

	public void setLoginFormPath(String loginFormPath) {
		this.loginFormPath = loginFormPath;
	}
	
}

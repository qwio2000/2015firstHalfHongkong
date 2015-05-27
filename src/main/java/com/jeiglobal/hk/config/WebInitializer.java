package com.jeiglobal.hk.config;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		super.onStartup(servletContext);
		
		String proActiveParam ="";
		
		Properties pro = null;
		try {
			pro = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/spring.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(pro == null){
			proActiveParam = "prod";
		}else{
			proActiveParam = pro.getProperty("spring.profiles.active");
		}
		servletContext.setInitParameter("my.profiles.active",proActiveParam);
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		//SecurityConfig먼저 작동해야함 설정관련해서  http://gotoanswer.stanford.edu/?q=No+bean+named+%27springSecurityFilterChain%27+is+defined+error+with+javaconfig 참조해라
		return new Class<?>[]{SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[]{WebMvcConfig.class};
	}
	
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	}
	
	@Override
	protected boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return super.isAsyncSupported();
	}
	/**
	 * HiddenHttpMethodFilter 레스트풀 방식 put delete 메소드를 사용할려면 추가
	 */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{ characterEncodingFilter,hiddenHttpMethodFilter};
    }
	
}
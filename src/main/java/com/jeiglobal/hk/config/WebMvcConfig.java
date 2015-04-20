package com.jeiglobal.hk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan("com.jeiglobal.hk")
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	/**
	 * 정적자원 설정 브라우져에서 캐쉬기간 1년 362~3 page
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/public/js/**")
		.addResourceLocations("/WEB-INF/public/js/")
		.setCachePeriod(31556926);
		registry.addResourceHandler("/public/css/**")
		.addResourceLocations("/WEB-INF/public/css/")
		.setCachePeriod(31556926);
		registry.addResourceHandler("/public/img/**")
		.addResourceLocations("/WEB-INF/public/img/")
		.setCachePeriod(31556926);
	}
	
	/**
	 * 디폴트 핸들링 설정 360~361 page
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable("default");
	};
	
	/**
	 * 뷰리졸버 설정
	 * @return
	 */
	@Bean
	public ViewResolver getInternalResourceViewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(1);
		return viewResolver;
	}
	
}

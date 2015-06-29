package com.jeiglobal.hk.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.jeiglobal.hk.excel.*;
import com.jeiglobal.hk.intercepter.MenuIntercepter;

import freemarker.template.TemplateException;


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
		.addResourceLocations("/public/js/")
		.setCachePeriod(31556926);
		registry.addResourceHandler("/public/css/**")
		.addResourceLocations("/public/css/")
		.setCachePeriod(31556926);
		registry.addResourceHandler("/public/img/**")
		.addResourceLocations("/public/img/")
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
		viewResolver.setOrder(2);
		return viewResolver;
	}
	@Bean
	  public ViewResolver viewResolver() {
	    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
	    resolver.setCache(true);
	    resolver.setPrefix("");
	    resolver.setSuffix(".ftl");
	    resolver.setContentType("text/html; charset=UTF-8");
	    resolver.setExposeSpringMacroHelpers(true);
	    resolver.setOrder(1);
	    return resolver;
	  }
	/**
	 * 빈네임뷰리졸버(엑셀 다운로드)
	 * @return
	 */
	@Bean
	public ViewResolver beanNameViewResolver() {
		BeanNameViewResolver resolver = new BeanNameViewResolver();
		resolver.setOrder(0);
		return resolver;
	}
	
	 @Bean
	  public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
	    FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
	    factory.setTemplateLoaderPath("classpath:templates");
	    factory.setDefaultEncoding("UTF-8");
	    FreeMarkerConfigurer result = new FreeMarkerConfigurer();
	    result.setConfiguration(factory.createConfiguration());
	    return result;
	  }
	 /**
	  * 학적미발생회원 엑셀 다운로드 빈객체 등록
	  * @return
	  */
	@Bean(name="emptyHakjukExcel")
	public EmptyHakjukExcelView emptyHakjukExcel(){
		return new EmptyHakjukExcelView();
	}
	@Bean(name="huheiListExcel")
	public HuheiListExcelView excelDownloadView(){
		return new HuheiListExcelView();
	}
	@Bean(name="ipheiListExcel")
	public IpheiListExcelView ipheiListExcel(){
		return new IpheiListExcelView();
	}
	 
	@Bean
	public MenuIntercepter menuIntercepter(){
		return new MenuIntercepter();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(menuIntercepter()).addPathPatterns("/**").excludePathPatterns("/","/login");
	}
	
}

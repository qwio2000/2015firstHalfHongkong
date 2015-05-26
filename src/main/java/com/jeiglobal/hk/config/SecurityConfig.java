package com.jeiglobal.hk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.savedrequest.NullRequestCache;

import com.jeiglobal.hk.auth.MyCustomAuthenticationEntryPoint;
import com.jeiglobal.hk.auth.MyCustomAuthenticationFailureHandler;
import com.jeiglobal.hk.auth.MyCustomAuthenticationProvider;
import com.jeiglobal.hk.auth.MyCustomAuthenticationSuccessHandler;
import com.jeiglobal.hk.auth.MyCustomLogoutSuccessHandler;
import com.jeiglobal.hk.auth.MyCustomSecurityContextRepository;



/**
 * 시큐리티 설정 http://docs.spring.io/spring-security/site/docs/3.2.6.RELEASE/reference/htmlsingle/#jc-form 참고
 * @author Administrator
 *
 */
@Configuration
@EnableWebSecurity
@ComponentScan("com.jeiglobal.hk")
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private MyCustomAuthenticationProvider myCustomAuthenticationProvider;
	
		@Autowired
		private MyCustomAuthenticationSuccessHandler myCustomAuthenticationSuccessHandler;
		
		@Autowired
		private MyCustomAuthenticationFailureHandler myCustomAuthenticationFailureHandler;
		
		@Autowired
		private MyCustomAuthenticationEntryPoint myCustomAuthenticationEntryPoint;
		
		@Autowired
		private MyCustomLogoutSuccessHandler myCustomLogoutSuccessHandler;
		
		@Autowired
		private MyCustomSecurityContextRepository myCustomSecurityContextRepository;
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
			auth.authenticationProvider(myCustomAuthenticationProvider);
		}
	
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/public/js/**","/public/css/**","/public/img/**","/favicon.ico");
		}
		
		@Override
		public void configure(HttpSecurity http) throws Exception {
				http
					.securityContext().securityContextRepository(myCustomSecurityContextRepository)
				.and()
					.csrf().disable()
					.authorizeRequests()
					.antMatchers("/login","/").permitAll()
					.antMatchers("/adminManager/**").hasAuthority("SUPERADMIN")
					.anyRequest().authenticated()
				.and()
					.formLogin()
						.usernameParameter("memberId")
						.passwordParameter("memberPassword")
						.loginProcessingUrl("/loginCheck")
						.successHandler(myCustomAuthenticationSuccessHandler)
						.failureHandler(myCustomAuthenticationFailureHandler)
						.permitAll()
				.and()
					.logout()
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login")
						.logoutSuccessHandler(myCustomLogoutSuccessHandler)
						.permitAll()
				.and()
					.httpBasic()
				.and()
					.exceptionHandling()
						.authenticationEntryPoint(myCustomAuthenticationEntryPoint)
				.and()
					.requestCache().requestCache(new NullRequestCache());
		}
		
}

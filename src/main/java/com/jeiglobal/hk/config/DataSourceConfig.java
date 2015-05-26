package com.jeiglobal.hk.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jeiglobal.hk.repository.AnotherRepositoryAnnoInterface;
import com.jeiglobal.hk.repository.PrimaryRepositoryAnnoInterface;



@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DataSourceConfig {
	
	@Bean
	public DataSource getPrimaryDataSource() {
		final JndiDataSourceLookup basicDataSource = new JndiDataSourceLookup();
		basicDataSource.setResourceRef(true);
		return basicDataSource.getDataSource("java:comp/env/jdbc/myJeiGlobal");
	}
	
	@Bean
	public DataSource getAnotherDataSource() {
		final JndiDataSourceLookup basicDataSource = new JndiDataSourceLookup();
		basicDataSource.setResourceRef(true);
		return basicDataSource.getDataSource("java:comp/env/jdbc/jeiMssql");
	}
	/**
	 * DB두대연결해보기위해 설정함
	 * @return
	 */
	@Bean
	public PlatformTransactionManager primaryTransactionManager() {
		return new DataSourceTransactionManager(getPrimaryDataSource());
	}
	/**
	 * DB두대연결해보기위해 설정함
	 * @return
	 */
	@Bean
	public PlatformTransactionManager anotherTransactionManager() {
		return new DataSourceTransactionManager(getAnotherDataSource());
	}
	/**
	 * DB두대연결해보기위해 설정함
	 * @return
	 */
	@Bean(name="mySqlSession")
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(getPrimaryDataSource());
		/**
		 * 리소스에 정의되어있는 mapper 읽어드릴 xml 위치설정
		 */
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
		/**
		 * alias 검색할 패키지 위치
		 */
		sqlSessionFactoryBean.setTypeAliasesPackage("com.jeiglobal.hk.domain");
		
		return sqlSessionFactoryBean.getObject();
	}
	/**
	 * DB두대연결해보기위해 설정함
	 * @return
	 */
	@Bean(name="myAnotherSqlSession")
	public SqlSessionFactory getAnotherSqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(getAnotherDataSource());
		/**
		 * 리소스에 정의되어있는 mapper 읽어드릴 xml 위치설정
		 */
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
		/**
		 * alias 검색할 패키지 위치
		 */
		sqlSessionFactoryBean.setTypeAliasesPackage("com.jeiglobal.hk.fa.domain");
		
		return sqlSessionFactoryBean.getObject();
	}
	/**
	 * DB두대연결해보기위해 설정함
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer setPrimaryMapperScannerConfigurer(){
		MapperScannerConfigurer primaryMapperScanConfigurer = new MapperScannerConfigurer();
		primaryMapperScanConfigurer.setBasePackage("com.jeiglobal.hk");
		primaryMapperScanConfigurer.setAnnotationClass(PrimaryRepositoryAnnoInterface.class);
		primaryMapperScanConfigurer.setBeanName("primaryMapperScanConfigurer");
		primaryMapperScanConfigurer.setSqlSessionFactoryBeanName("mySqlSession");
		return primaryMapperScanConfigurer;
	}
	/**
	 * DB두대연결해보기위해 설정함
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer setAnotherMapperScannerConfigurer(){
		MapperScannerConfigurer anotherMapperScanConfigurer = new MapperScannerConfigurer();
		anotherMapperScanConfigurer.setBasePackage("com.jeiglobal.hk");
		anotherMapperScanConfigurer.setAnnotationClass(AnotherRepositoryAnnoInterface.class);
		anotherMapperScanConfigurer.setBeanName("anotherMapperScanConfigurer");
		anotherMapperScanConfigurer.setSqlSessionFactoryBeanName("myAnotherSqlSession");
		return anotherMapperScanConfigurer;
	}
	
}

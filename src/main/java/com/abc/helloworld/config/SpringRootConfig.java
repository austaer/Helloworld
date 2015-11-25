package com.abc.helloworld.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.abc.helloworld.config.CommonConfig;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.abc.helloworld.serviceimpl", "com.abc.helloworld.dao" })
public class SpringRootConfig {


/*	@Bean
	public BasicDataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(CommonConfig.DB_PARAMETERMAP_DRIVER);
		ds.setUrl(CommonConfig.DB_PARAMETERMAP_URL);
		ds.setUsername(CommonConfig.DB_PARAMETERMAP_USER_NAME);
		ds.setPassword(CommonConfig.DB_PARAMETERMAP_PASSWORD);
		return ds;
	}*/

	
/*	@Bean
	public JdbcTemplate jdbcTemplate() {
		final JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(getDataSource());
		jdbcTemplate.afterPropertiesSet();
		return jdbcTemplate;
	}*/

	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(CommonConfig.DB_PARAMETERMAP_DRIVER);
		dataSource.setUrl(CommonConfig.DB_PARAMETERMAP_URL);
		dataSource.setUsername(CommonConfig.DB_PARAMETERMAP_USER_NAME);
		dataSource.setPassword(CommonConfig.DB_PARAMETERMAP_PASSWORD);
		return dataSource;
	}
	
	public Properties getProperties() {
		Properties prop = new Properties();
		prop.put(CommonConfig.PROPERTY_NAME_HIBERNATE_SHOW_SQL, "true");
		prop.put(CommonConfig.PROPERTY_NAME_HIBERNATE_DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
		return prop;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(getDataSource());
		sessionFactoryBean.setPackagesToScan(CommonConfig.PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
		sessionFactoryBean.setHibernateProperties(getProperties());
		return sessionFactoryBean;
	}

}
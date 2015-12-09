package com.abc.helloworld.config;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.abc.helloworld.controller", "com.abc.helloworld.exception.controller" })
public class SpringWebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		CacheControl cacheControl = CacheControl.maxAge(3600, TimeUnit.SECONDS);
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/")
				.setCacheControl(cacheControl);
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	private Properties getProperties(){
		Properties prop = new Properties();
		prop.setProperty("java.lang.Exception", "500");
		return prop;
	}
	
	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		exceptionResolver.setDefaultStatusCode(500);
		exceptionResolver.setDefaultErrorView("error/500");
		exceptionResolver.setExceptionMappings(getProperties());
		return exceptionResolver;
	}
}

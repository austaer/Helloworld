package com.abc.helloworld.web;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.abc.helloworld.config.*;
import com.abc.helloworld.filter.Log4jInitFilter;
import com.abc.helloworld.filter.MyUserNamePasswordAuthorizeFilter;

@Order(1)
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// 另一個做法
	// @Override
	// public void onStartup(ServletContext servletContext) throws
	// ServletException {
	// super.onStartup(servletContext);
	// FilterRegistration.Dynamic encodingFilter =
	// servletContext.addFilter("encodingFilter", new
	// CharacterEncodingFilter());
	// encodingFilter.setInitParameter("encoding", "UTF-8");
	// encodingFilter.setInitParameter("forceEncoding", "true");
	// encodingFilter.addMappingForUrlPatterns(null, true, "/*");
	// }

	@Override
	protected Filter[] getServletFilters() {
		// 有順序性 characterEncodingFilter 擺在最前面。
		// 可能dofilter chain filter時會自己把其他實做filter的class也加進來，所以這邊只要加一個filter不需要加自己實做的filter
		return new Filter[] { new CharacterEncodingFilter("UTF-8",
				true)/* , new Log4jInitFilter() */, new HiddenHttpMethodFilter() };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringRootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}

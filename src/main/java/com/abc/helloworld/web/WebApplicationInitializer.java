package com.abc.helloworld.web;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.abc.helloworld.config.*;
import com.abc.helloworld.filter.Log4jInitFilter;
import com.abc.helloworld.filter.MyUserNamePasswordAuthorizeFilter;
import com.abc.helloworld.listener.SessionListener;

@Order(1)
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Filter[] getServletFilters() {
		// 有順序性 characterEncodingFilter 擺在最前面。
		// 可能dofilter chain
		// filter時會自己把其他實做filter的class也加進來，所以這邊只要加一個filter不需要加自己實做的filter
		return new Filter[] { new CharacterEncodingFilter("UTF-8", true), new HiddenHttpMethodFilter() };
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

	@Override
	protected void registerDispatcherServlet(ServletContext servletContext) {
		String servletName = getServletName();
		Assert.hasLength(servletName, "getServletName() may not return empty or null");

		WebApplicationContext servletAppContext = createServletApplicationContext();
		Assert.notNull(servletAppContext, "createServletApplicationContext() did not return an application "
				+ "context for servlet [" + servletName + "]");

		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);

		// throw NoHandlerFoundException to Controller
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		servletContext.addListener(new SessionListener());
		
		ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, dispatcherServlet);
		Assert.notNull(registration, "Failed to register servlet with name '" + servletName + "'."
				+ "Check if there is another servlet registered under the same name.");

		registration.setLoadOnStartup(1);
		registration.addMapping(getServletMappings());
		registration.setAsyncSupported(isAsyncSupported());

		Filter[] filters = getServletFilters();
		if (!ObjectUtils.isEmpty(filters)) {
			for (Filter filter : filters) {
				registerServletFilter(servletContext, filter);
			}
		}

		customizeRegistration(registration);
	}
}

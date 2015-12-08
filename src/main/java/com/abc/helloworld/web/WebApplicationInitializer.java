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

	// �t�@�Ӱ��k
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
		// �����ǩ� characterEncodingFilter �\�b�̫e���C
		// �i��dofilter chain filter�ɷ|�ۤv���L�갵filter��class�]�[�i�ӡA�ҥH�o��u�n�[�@��filter���ݭn�[�ۤv�갵��filter
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

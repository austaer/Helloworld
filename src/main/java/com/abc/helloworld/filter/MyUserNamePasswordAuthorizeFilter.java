package com.abc.helloworld.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.SessionAttributes;

@WebFilter(urlPatterns = { "/list", "/list/*", "/add", "/edit/*" })
@SessionAttributes({ "loginStatus", "username" })
public class MyUserNamePasswordAuthorizeFilter implements Filter {
	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		System.out.println("======================================");
		System.out.println(request.getSession().getAttribute("loginStatus"));
		System.out.println("======================================");
		String loginStatus = request.getSession().getAttribute("loginStatus") == null ? "false"
				: request.getSession().getAttribute("loginStatus").toString();
		boolean isLogin = Boolean.parseBoolean(loginStatus);
		if (isLogin == false) {
			response.sendRedirect(request.getContextPath() + "/login");
		}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}

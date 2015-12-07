package com.abc.helloworld.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.abc.helloworld.config.LogUtil;

public class Log4jInitFilter implements Filter {

	private String charset = "UTF-8";
	private String defaulteLog4jPropertiesPath = "/WEB-INF/classes/log4j.properties";
	private String defaultLogFilePath = "/logs";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		String reqContentType = req.getContentType();
		if (reqContentType == null) {
			req.setCharacterEncoding(charset);
		} else {
			String[] contentType = reqContentType.split(";");
			if (contentType.length != 2) {
				req.setCharacterEncoding(charset);
			} else {
				String reqCharset = contentType[1].split("=")[1];
				if (reqCharset.toLowerCase().equals("big5") // for fileupload
						|| reqCharset.toLowerCase().equals("gb2312") || reqCharset.toLowerCase().equals("utf-8")
						|| reqCharset.toLowerCase().equals("utf8") || reqCharset.toLowerCase().equals("gb18030")) {
					req.setCharacterEncoding(reqCharset);
				} else {
					req.setCharacterEncoding(charset);
				}

			}
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		initLog4j(config);
		setCharacterEncoding(config);
		LogUtil.info("系統配置已載入完成");
	}

	private void setCharacterEncoding(FilterConfig config) {
		String initCharset = config.getInitParameter("charSet");
		if (initCharset == null || initCharset.trim().length() == 0)
			return;
		this.charset = initCharset;
		LogUtil.info("已設定文字集為:"+this.charset);
	}

	//初始化Log4j
	private void initLog4j(FilterConfig config) {
		String log4jPropertiesPath = config.getInitParameter("log4jPropertiesPath");
		String logFilePath = config.getInitParameter("logFilePath");
		if (log4jPropertiesPath == null || log4jPropertiesPath.trim().length() == 0) {
			log4jPropertiesPath = this.defaulteLog4jPropertiesPath;
		}

		if (logFilePath == null || logFilePath.trim().length() == 0) {
			logFilePath = this.defaultLogFilePath;
		}

		LogUtil.log4jInit(config.getServletContext().getRealPath(log4jPropertiesPath),
				config.getServletContext().getRealPath(logFilePath));
	}
}
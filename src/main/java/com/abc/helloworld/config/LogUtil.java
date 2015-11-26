package com.abc.helloworld.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	
	private static Logger logger = LoggerFactory.getLogger(LogUtil.class);
	
	public static void info(String message){
		logger.info(message);
	}
	
	public static void log4jInit(String configPath, String logDirectory) {
		File file = new File(configPath);
		if (!file.exists())
			return;
		try {
			InputStream in = new FileInputStream(file);
			Properties p = new Properties();
			p.load(in);

			String basePath = logDirectory + File.separator;

			if (p.getProperty("log4j.appender.info.File") != null) {
				p.setProperty("log4j.appender.info.File", basePath + p.getProperty("log4j.appender.info.File"));
			}

			if (p.getProperty("log4j.appender.debug.File") != null) {
				p.setProperty("log4j.appender.debug.File", basePath + p.getProperty("log4j.appender.debug.File"));
			}

			if (p.getProperty("log4j.appender.warn.File") != null) {
				p.setProperty("log4j.appender.warn.File", basePath + p.getProperty("log4j.appender.warn.File"));
			}

			if (p.getProperty("log4j.appender.error.File") != null) {
				p.setProperty("log4j.appender.error.File", basePath + p.getProperty("log4j.appender.error.File"));
			}

			PropertyConfigurator.configure(p);
			in.close();
			LogUtil.info("日誌位置：" + basePath);
		} catch (Exception e) {
			System.out.println("載入log4j配置檔案失敗!");
		}
	}
}

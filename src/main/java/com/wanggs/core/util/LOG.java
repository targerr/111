package com.wanggs.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LOG {

	private static Logger logger = LoggerFactory.getLogger(LOG.class);
	
	// ------------info------------
	
	public static void info(String format, Object ...argArray) {
		logger.info(format, argArray);
	}
	
	public static void info(String msg, Throwable t) {
		logger.info(msg, t);
	}
	
	public static void info(String msg) {
		logger.info(msg);
	}
	
	// ------------error------------
	
	public static void error(String format, Object ...argArray) {
		logger.info(format, argArray);
	}
	
	public static void error(String msg, Throwable t) {
		logger.info(msg, t);
	}
	
	public static void error(String msg) {
		logger.info(msg);
	}
	
	// ------------debug------------
	
	public static void debug(String format, Object ...argArray) {
		logger.info(format, argArray);
	}
	
	public static void debug(String msg, Throwable t) {
		logger.info(msg, t);
	}
	
	public static void debug(String msg) {
		logger.info(msg);
	}
	
	
}

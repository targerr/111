package com.wanggs.core.utils;

import com.wanggs.core.beans.EnumMessage;
import org.apache.log4j.Logger;

/**
 * ͨ����־�����
 * @author Sean
 *
 */
public class Log {

	private static Logger logger;

	private Log() {
	}

	private static Logger getLogger() {
		if (logger == null) {
			logger = Logger.getLogger(Log.class);
		}
		return logger;
	}

	public static void info(String message, Exception e) {
		getLogger().info(message, e);
	}

	
	public static void error(EnumMessage.Message message, Exception e) {
		getLogger().error(message.toString(), e);
	}

	
	public static void warn(EnumMessage.Message message, Exception e) {
		getLogger().warn(message.toString(), e);
	}
	public static void info(EnumMessage.Message message, Exception e) {
		info(message.toString(), e);
	}
	
	
	public static void error(String message, Exception e) {
		getLogger().error(message, e);
	}
	
	
	public static void warn(String message, Exception e) {
		getLogger().warn(message, e);
	}

	
	public static void main(String[] args) {
		Log.info(EnumMessage.Message.Log_0001,null);
	}

}

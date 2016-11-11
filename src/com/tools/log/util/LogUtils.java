package com.tools.log.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogUtils {
	private static Log errorLog = LogFactory.getLog("error");
	private static Log debug = LogFactory.getLog("debug");
	
	public static void error(Object e){
		errorLog.error(e);
	}
	
	public static void error(Object o,Throwable e){
		errorLog.error(o,e);
	}
	
	public static void debug(Object e){
		debug.debug(e);
	}
}

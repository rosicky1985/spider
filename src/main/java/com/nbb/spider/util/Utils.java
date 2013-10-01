package com.nbb.spider.util;

/**
 * 常用工具
 * 
 * @author rosicky
 * 
 */
public class Utils {
	public static Integer atoi(String text) {
		Integer ret = 0;
		text = text.replaceAll(",", "");
		try {
			ret = Integer.parseInt(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}

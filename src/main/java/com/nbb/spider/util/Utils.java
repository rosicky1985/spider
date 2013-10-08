package com.nbb.spider.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * 常用工具
 * 
 * @author rosicky
 * 
 */
public class Utils {
	public interface MkStringPart {
		public String getString(Object obj);
	}

	public static String formatTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static String mkString(Iterator<?> itr, String delimeter,
			MkStringPart op) {
		String ret = "";
		boolean first = true;
		while (itr.hasNext()) {
			if (!first) {
				ret += delimeter;
			}
			ret += op.getString(itr.next());
			if (first) {
				first = false;
			}
		}
		return ret;
	}

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

	public static String httpGet(String urlToRead, String charset) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), charset));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

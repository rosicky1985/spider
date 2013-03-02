package com.nbb.spider.manager.webspider.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;

import com.nbb.spider.entity.Item;
import com.nbb.spider.entity.impl.Sohu;
import com.nbb.spider.manager.webspider.AbstractSpider;
import com.nbb.spider.manager.webspider.Spider;

public class SohuSpider extends AbstractSpider implements Spider {
	private static String[] MOVIES = {
			"http://tv.sohu.com/frag/vrs_inc/phb_mv_day_50.js",
			"http://tv.sohu.com/frag/vrs_inc/phb_mv_week_50.js",
			"http://tv.sohu.com/frag/vrs_inc/phb_mv_month_50.js" };
	private static String[] TVS = {
			"http://tv.sohu.com/frag/vrs_inc/phb_tv_day_50.js",
			"http://tv.sohu.com/frag/vrs_inc/phb_tv_week_50.js",
			"http://tv.sohu.com/frag/vrs_inc/phb_tv_month_50.js" };

	private String[] target;
	private int i;

	@Override
	public List<? extends Item> extract() {
		try {
			List<Sohu> ret = extractOnePage(target[i]);
			return ret;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		} finally {
			i++;
		}
	}

	@Override
	protected Item parseOneline(Element ele) {
		return null;
	}

	public void movie() {
		target = MOVIES;
	}

	public void start() throws IOException {
		i = 0;
	}

	public void tv() {
		target = TVS;
	}

	public String getHttpResponse(String u) {
		BufferedReader rd = null;
		StringBuilder sb = null;
		String line = null;
		try {
			URL url = new URL(u);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			rd = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "gbk"));
			sb = new StringBuilder();

			while ((line = rd.readLine()) != null) {
				sb.append(line + '\n');
			}
			return sb.toString();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) throws JSONException {
	}

	public List<Sohu> extractOnePage(String url) throws JSONException {
		List<Sohu> ret = new ArrayList<Sohu>();
		String line = getHttpResponse(url);
		int indexOfStart = line.indexOf('{');
		String jsoncontent = line.substring(indexOfStart);
		JSONObject root = new JSONObject(jsoncontent);
		JSONArray arrays = root.getJSONArray("videos");
		for (int i = 0; i < arrays.length(); i++) {
			JSONObject video = arrays.getJSONObject(i);
			String tv_name = video.getString("tv_name");
			int rank = i + 1;
			long count = video.getLong("tv_count");
			String mainActor = video.getString("MAIN_ACTOR");
			String director = video.getString("DIRECTOR");
			Sohu sohu = new Sohu(rank, count, tv_name, mainActor, director);
			ret.add(sohu);
			System.out.println(sohu.toString());
		}
		return ret;
	}
}

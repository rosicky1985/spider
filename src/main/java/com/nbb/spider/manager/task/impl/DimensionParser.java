package com.nbb.spider.manager.task.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nbb.spider.dao.DimensionDao;
import com.nbb.spider.entity.full.Dimension;

public class DimensionParser<T extends Dimension> {

	public List<T> parseFromStringTokens(String values, String delimeter,
			DimensionDao<T> dao) {
		List<T> dimensions = new ArrayList<T>();
		StringTokenizer tokens = new StringTokenizer(values, delimeter);
		while (tokens.hasMoreTokens()) {
			String name = tokens.nextToken();
			dimensions.add(dao.getByName(name));
		}
		return dimensions;
	}

	public List<T> parseFromJsonArray(JSONArray dimensions,
			DimensionDao<T> dao, String tagName) throws JSONException {
		List<T> ret = new ArrayList<T>();
		for (int j = 0; j < dimensions.length(); j++) {
			String name = null;
			if (tagName == null) {
				name = dimensions.getString(j);
			} else {
				JSONObject a = dimensions.getJSONObject(j);
				name = a.getString(tagName);
			}
			ret.add(dao.getByName(name));
		}
		return ret;
	}
}

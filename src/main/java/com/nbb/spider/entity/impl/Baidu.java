package com.nbb.spider.entity.impl;

import com.nbb.spider.entity.AbstractItem;
import com.nbb.spider.entity.Item;

public class Baidu extends AbstractItem implements Item {
	private String index;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Baidu [index=" + index + ", rank=" + rank + ", title=" + title
				+ "]";
	}
	

}

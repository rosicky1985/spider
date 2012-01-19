package com.nbb.spider.entity.impl;

import com.nbb.spider.entity.AbstractItem;
import com.nbb.spider.entity.Item;

public class Baidu extends AbstractItem implements Item {
	private Long today;
	private Long lastWeek;

	public Long getToday() {
		return today;
	}

	public void setToday(String string) {
		this.today = Long.parseLong(this.parseNumeric(string));
	}

	public Long getLastWeek() {
		return lastWeek;
	}

	public void setLastWeek(String string) {
		this.lastWeek = Long.parseLong(this.parseNumeric(string));
	}
}

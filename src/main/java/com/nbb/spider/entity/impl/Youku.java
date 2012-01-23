package com.nbb.spider.entity.impl;

import java.util.List;

import com.nbb.spider.entity.AbstractItem;
import com.nbb.spider.entity.Item;

public class Youku extends AbstractItem implements Item {
	private List<String> actors;
	private Long trend;

	public List<String> getActors() {
		return actors;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	public Long getTrend() {
		return trend;
	}

	public void setTrend(Long trend) {
		this.trend = trend;
	}

	public void setTrend(String text) {
		trend = Long.parseLong(this.parseNumeric(text));
	}

	public String getActorsList() {
		return this.makeString(actors, ",");
	}
}

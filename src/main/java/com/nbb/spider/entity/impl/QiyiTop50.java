package com.nbb.spider.entity.impl;

import java.util.List;

import com.nbb.spider.entity.AbstractItem;
import com.nbb.spider.entity.Item;

public class QiyiTop50 extends AbstractItem implements Item {
	private List<String> actors;
	private Long yesterDay;
	private Long lastWeek;
	private Long history;

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Integer getRank() {
		return rank;
	}

	public List<String> getActors() {
		return actors;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	public Long getYesterDay() {
		return yesterDay;
	}

	public void setYesterDay(Long yesterDay) {
		this.yesterDay = yesterDay;
	}

	public Long getLastWeek() {
		return lastWeek;
	}

	public void setLastWeek(Long lastWeek) {
		this.lastWeek = lastWeek;
	}

	public Long getHistory() {
		return history;
	}

	public void setHistory(Long history) {
		this.history = history;
	}

	public void setHistory(String text) {
		text = this.parseNumeric(text);
		this.history = Long.parseLong(text);
	}

	public void setLastWeek(String text) {
		text = this.parseNumeric(text);
		this.lastWeek = Long.parseLong(text);
	}

	public void setYesterDay(String text) {
		text = this.parseNumeric(text);
		this.yesterDay = Long.parseLong(text);
	}

	public String getActorsString() {
		return this.makeString(actors, ",");
	}

	public int compareRankto(Item arg1) {
		return 0;
	}

}

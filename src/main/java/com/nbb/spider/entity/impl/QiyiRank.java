package com.nbb.spider.entity.impl;

import java.util.List;

import com.nbb.spider.entity.AbstractItem;
import com.nbb.spider.entity.Item;

public class QiyiRank extends AbstractItem implements Item {
	private List<String> keyworks;
	private Double lastDayIncreased;

	public List<String> getKeyworks() {
		return keyworks;
	}

	public void setKeyworks(List<String> keyworks) {
		this.keyworks = keyworks;
	}

	public Double getLastDayIncreased() {
		return lastDayIncreased;
	}

	public void setLastDayIncreased(String lastDayIncreased) {
		lastDayIncreased = parseNumeric(lastDayIncreased);
		this.lastDayIncreased = Double.parseDouble(lastDayIncreased);
	}

	public String getKeywords() {
		return makeString(keyworks, ",");
	}

	public int compareRankto(Item arg1) {
		return 0;
	}
}

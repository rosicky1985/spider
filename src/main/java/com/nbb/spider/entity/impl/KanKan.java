package com.nbb.spider.entity.impl;

import com.nbb.spider.entity.AbstractItem;

public class KanKan extends AbstractItem{
	private String area;
	private String mainActor;
	private String category;
	private Integer index;

	public String getArea() {
		return area;
	}

	@Override
	public String toString() {
		return "KanKan [area=" + area + ", mainActor=" + mainActor
				+ ", category=" + category + ", index=" + index + ", rank="
				+ rank + ", title=" + title + "]";
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getMainActor() {
		return mainActor;
	}

	public void setMainActor(String mainActor) {
		this.mainActor = mainActor;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setIndex(String text) {
		this.index = Integer.parseInt(this.parseNumeric(text));
	}
}

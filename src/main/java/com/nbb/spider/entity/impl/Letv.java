package com.nbb.spider.entity.impl;

import com.nbb.spider.entity.AbstractItem;
import com.nbb.spider.entity.Item;

public class Letv extends AbstractItem implements Item {
	private String actors;
	private String director;
	private String category;
	@Override
	public String toString() {
		return "Letv [actors=" + actors + ", director=" + director
				+ ", category=" + category + ", rank=" + rank + ", title="
				+ title + "]";
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}

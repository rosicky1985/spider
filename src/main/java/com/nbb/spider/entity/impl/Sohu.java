package com.nbb.spider.entity.impl;

import com.nbb.spider.entity.AbstractItem;

public class Sohu extends AbstractItem {
	private Long count;
	private String mainActor;
	private String director;
	public Sohu(int rank, Long count,String tv_name, String mainActor2, String director2) {
		this.rank = rank;
		this.title = tv_name;
		this.mainActor = mainActor2;
		this.director = director2;
		this.count = count;
	}
	@Override
	public String toString() {
		return "Sohu [count=" + count + ", mainActor=" + mainActor
				+ ", director=" + director + ", rank=" + rank + ", title="
				+ title + "]";
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getMainActor() {
		return mainActor;
	}
	public void setMainActor(String mainActor) {
		this.mainActor = mainActor;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
}

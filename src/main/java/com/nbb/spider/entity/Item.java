package com.nbb.spider.entity;

public interface Item {
	public String getTitle();
	public Integer getRank();
	public abstract int compareRankto(Item arg1);
}

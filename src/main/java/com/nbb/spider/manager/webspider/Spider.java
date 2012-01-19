package com.nbb.spider.manager.webspider;

import java.util.List;

import com.nbb.spider.entity.Item;

public interface Spider {
	public List<? extends Item> extract();
}
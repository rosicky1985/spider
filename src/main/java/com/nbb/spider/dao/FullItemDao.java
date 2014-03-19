package com.nbb.spider.dao;

import com.nbb.spider.entity.full.FullItem;

public interface FullItemDao {

	public void save(FullItem fi);

	public FullItemQuery createQuey();

}

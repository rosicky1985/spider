package com.nbb.spider.dao;

import java.util.List;

import com.nbb.spider.entity.full.FullItem;

public interface FullItemDao {

	public void save(FullItem fi);

	public List<FullItem> all();

}

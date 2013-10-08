package com.nbb.spider.dao;

import java.util.List;

import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.Type;

public interface DataSourceDao {

	public List<DataSource> find();

	public List<DataSource> find(Type dAILY);

}

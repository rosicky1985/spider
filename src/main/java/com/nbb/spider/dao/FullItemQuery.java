package com.nbb.spider.dao;

import java.util.Date;
import java.util.Iterator;

import com.nbb.spider.entity.full.FullItem;

public interface FullItemQuery {

	public void start(Date start);

	public void end(Date end);

	public Iterator<FullItem> list();

	public void createdstart(Date start);

	public void createdend(Date end);

}

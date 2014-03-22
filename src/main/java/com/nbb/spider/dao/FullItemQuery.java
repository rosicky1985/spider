package com.nbb.spider.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.nbb.spider.entity.full.FullItem;

public interface FullItemQuery {

	public void start(Date start);

	public void end(Date end);

	public Iterator<FullItem> list();

}

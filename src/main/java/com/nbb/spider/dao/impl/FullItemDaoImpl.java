package com.nbb.spider.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nbb.spider.dao.AbstractDao;
import com.nbb.spider.dao.FullItemDao;
import com.nbb.spider.entity.full.FullItem;

@Repository
public class FullItemDaoImpl extends AbstractDao<FullItem> implements
		FullItemDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<FullItem> all() {
		return this.getSession().createQuery("from FullItem").list();
	}

}

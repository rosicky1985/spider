package com.nbb.spider.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nbb.spider.dao.AbstractDao;
import com.nbb.spider.dao.FullItemDao;
import com.nbb.spider.dao.FullItemQuery;
import com.nbb.spider.entity.full.FullItem;

@Repository
public class FullItemDaoImpl extends AbstractDao<FullItem> implements
		FullItemDao {

	@Override
	public FullItemQuery createQuey() {
		class FullItemQueryImpl implements FullItemQuery {
			private Date start;
			private Date end;

			@Override
			public void start(Date start) {
				DateUtils.
				this.start = start;
			}

			@Override
			public void end(Date end) {
				this.end = end;
			}

			@Override
			public List<FullItem> list() {
				String hql = "from FullItem item where 1 ";
				if (start != null) {
					hql += " and item.task.target >= '" + start + "'";
				}
				if (start != null) {
					hql += " and item.task.target <= '" + end + "'";
				}
				return null;
			}

		}
		return null;
	}

}

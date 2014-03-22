package com.nbb.spider.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
				if (start != null) {
					Calendar c = Calendar.getInstance();
					c.setTime(start);
					c.set(Calendar.HOUR, 0);
					c.set(Calendar.MINUTE, 0);
					c.set(Calendar.SECOND, 0);
					this.start = c.getTime();
				} else {
					this.start = null;
				}
			}

			@Override
			public void end(Date end) {
				if (end != null) {
					Calendar c = Calendar.getInstance();
					c.setTime(end);
					c.set(Calendar.HOUR, 23);
					c.set(Calendar.MINUTE, 59);
					c.set(Calendar.SECOND, 59);
					this.end = c.getTime();
				} else {
					this.end = null;
				}
			}

			@SuppressWarnings("unchecked")
			@Override
			public Iterator<FullItem> list() {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String hql = "from FullItem";
				class Where {
					String condition;
					String op;
					String value;

					public Where(String condition, String op, String value) {
						super();
						this.condition = condition;
						this.op = op;
						this.value = value;
					}
				}
				List<Where> wheres = new ArrayList<Where>();
				if (start != null) {
					wheres.add(new Where("task.target", ">", sdf.format(start)));
				}
				if (end != null) {
					wheres.add(new Where("task.target", "<", sdf.format(end)));
				}
				if (wheres.size() > 0) {
					hql += " where ";
					boolean first = true;
					for (Where w : wheres) {
						if (!first) {
							hql += " and ";
						} else {
							first = false;
						}
						hql += w.condition + w.op + "'" + w.value + "'";
					}
				}
				return getSession().createQuery(hql).iterate();
			}

		}
		return new FullItemQueryImpl();
	}
}

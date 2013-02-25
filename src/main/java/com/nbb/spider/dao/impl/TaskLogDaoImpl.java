package com.nbb.spider.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbb.spider.dao.AbstractDao;
import com.nbb.spider.dao.TaskLogDao;
import com.nbb.spider.entity.TaskLog;

@Repository
public class TaskLogDaoImpl extends AbstractDao<TaskLog> implements TaskLogDao {
	@Transactional
	public TaskLog get(Integer id) {
		return get(id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<TaskLog> getAllTasks() {
		return getSession().createQuery("from TaskLog order by created desc limit 50")
				.list();
	}
}

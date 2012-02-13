package com.nbb.spider.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbb.spider.dao.AbstractDao;
import com.nbb.spider.dao.TaskLogDao;
import com.nbb.spider.entity.TaskLog;

@Repository
public class TaskLogDaoImpl extends AbstractDao<TaskLog> implements TaskLogDao {

	@Override
	@Transactional
	public TaskLog get(Integer id) {
		return this.get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<TaskLog> getAllTasks() {
		return this.getSession().createQuery("from TaskLog").list();
	}

}

package com.nbb.spider.dao.impl;

import org.springframework.stereotype.Repository;

import com.nbb.spider.dao.AbstractDao;
import com.nbb.spider.dao.TaskLogDao;
import com.nbb.spider.entity.TaskLog;

@Repository
public class TaskLogDaoImpl extends AbstractDao<TaskLog> implements TaskLogDao {

	@Override
	public TaskLog get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

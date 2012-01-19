package com.nbb.spider.dao;

import com.nbb.spider.entity.TaskLog;

public interface TaskLogDao {
	public void save(TaskLog taskLog);

	public void delete(TaskLog taskLog);

	public TaskLog get(Integer id);

}

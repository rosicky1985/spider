package com.nbb.spider.dao;

import java.util.List;

import com.nbb.spider.entity.TaskLog;

public interface TaskLogDao {
	public void save(TaskLog taskLog);

	public void delete(TaskLog taskLog);

	public TaskLog get(Integer id);

	public List<TaskLog> getAllTasks();

}

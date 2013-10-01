package com.nbb.spider.dao;

import java.util.List;

import com.nbb.spider.entity.TaskLog;

public abstract interface TaskLogDao
{
  public abstract void save(TaskLog paramTaskLog);

  public abstract void delete(TaskLog paramTaskLog);

  public abstract TaskLog get(Integer paramInteger);

  public abstract List<TaskLog> getAllTasks();
}


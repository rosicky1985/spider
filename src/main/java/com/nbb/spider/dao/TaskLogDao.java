package com.nbb.spider.dao;

import com.nbb.spider.entity.TaskLog;
import java.util.List;

public abstract interface TaskLogDao
{
  public abstract void save(TaskLog paramTaskLog);

  public abstract void delete(TaskLog paramTaskLog);

  public abstract TaskLog get(Integer paramInteger);

  public abstract List<TaskLog> getAllTasks();
}


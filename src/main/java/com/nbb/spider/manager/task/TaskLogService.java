package com.nbb.spider.manager.task;

import com.nbb.spider.entity.TaskLog;
import java.util.List;

public abstract interface TaskLogService
{
  public abstract List<TaskLog> list();
}


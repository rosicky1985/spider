package com.nbb.spider.manager.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbb.spider.dao.TaskLogDao;
import com.nbb.spider.entity.TaskLog;

@Service
public class TaskLogServiceImpl implements TaskLogService{
	@Autowired
	private TaskLogDao taskLogDao;
	@Transactional
	@Override
	public List<TaskLog> list() {
		return taskLogDao.getAllTasks();
	}

}

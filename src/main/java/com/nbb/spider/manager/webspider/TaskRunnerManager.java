package com.nbb.spider.manager.webspider;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbb.spider.dao.DataSourceDao;
import com.nbb.spider.dao.FullItemDao;
import com.nbb.spider.entity.Task;
import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.FullItem;
import com.nbb.spider.entity.full.Type;
import com.nbb.spider.manager.task.TaskFactory;
import com.nbb.spider.manager.task.TaskRunner;

/**
 * 抓取数据的任务运行管理器
 * 
 * @author rosicky
 * 
 */
@Service
public class TaskRunnerManager {
	private static final Logger logger = LoggerFactory
			.getLogger(TaskRunnerManager.class);
	@Autowired
	private ApplicationContext ctx;
	@Autowired
	private DataSourceDao dataSourceDao;
	@Autowired
	private FullItemDao fullItemDao;

	/**
	 * 一次性运行所有的抓取任务。 <br>
	 * 主要是在测试的时候使用
	 */
	@Transactional
	public void rullAll() {
		List<DataSource> ds = dataSourceDao.find();
		Date now = new Date();
		// 分类
		Map<Type, List<DataSource>> map = new HashMap<Type, List<DataSource>>();
		for (DataSource d : ds) {
			List<DataSource> list = map.get(d.getType());
			if (list == null) {
				list = new ArrayList<DataSource>();
				map.put(d.getType(), list);
			}
			list.add(d);
		}
		// 运行
		for (Entry<Type, List<DataSource>> entry : map.entrySet()) {
			Task task = TaskFactory.createTask(entry.getKey(), now);
			for (DataSource d : entry.getValue())
				runOneDs(task, d);
		}
	}

	private void runOneDs(Task task, DataSource d) {
		Object bean = ctx.getBean(d.getBean());
		if (bean != null && bean instanceof TaskRunner) {
			logger.debug("Running for : " + d);
			TaskRunner tr = (TaskRunner) bean;
			List<FullItem> list = tr.run(d, task);
			for (FullItem fi : list) {
				fullItemDao.save(fi);
			}
		}
	}
}

package com.nbb.spider.manager.task;

import java.util.List;

import com.nbb.spider.entity.Task;
import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.FullItem;

/**
 * 运行抓取任务的接口定义
 * 
 * @author rosicky
 * @param <FullItem>
 * 
 */
public interface TaskRunner {
	/**
	 * 运行一次抓取任务
	 * 
	 * @param dataSource
	 *            抓取任务的数据源
	 * @return
	 */
	public List<FullItem> run(DataSource dataSource, Task task);
}

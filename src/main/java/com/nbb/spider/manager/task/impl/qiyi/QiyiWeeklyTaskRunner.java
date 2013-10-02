package com.nbb.spider.manager.task.impl.qiyi;

public class QiyiWeeklyTaskRunner extends QiyiTaskRunner {

	@Override
	protected String getIndexTag() {
		return INDEX_LASTWEEK;
	}

}

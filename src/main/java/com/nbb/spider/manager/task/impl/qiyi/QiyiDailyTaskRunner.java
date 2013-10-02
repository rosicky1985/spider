package com.nbb.spider.manager.task.impl.qiyi;

public class QiyiDailyTaskRunner extends QiyiTaskRunner {

	@Override
	protected String getIndexTag() {
		return INDEX_YESTERDAY;
	}

}

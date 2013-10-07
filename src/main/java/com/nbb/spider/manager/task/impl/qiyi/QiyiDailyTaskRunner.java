package com.nbb.spider.manager.task.impl.qiyi;

import org.springframework.stereotype.Service;

@Service
public class QiyiDailyTaskRunner extends QiyiTaskRunner {

	@Override
	protected String getIndexTag() {
		return INDEX_YESTERDAY;
	}

}

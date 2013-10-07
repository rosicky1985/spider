package com.nbb.spider.manager.task.impl.letv;

import org.springframework.stereotype.Service;

@Service
public class LetvDailyTaskRunner extends LetvTaskRunner {

	@Override
	int getTabIndex() {
		return 0;
	}

}

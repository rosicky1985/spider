package com.nbb.spider.manager.task.impl.letv;

import org.springframework.stereotype.Service;

@Service
public class LetvWeeklyTaskRunner extends LetvTaskRunner {

	@Override
	int getTabIndex() {
		return 1;
	}

}

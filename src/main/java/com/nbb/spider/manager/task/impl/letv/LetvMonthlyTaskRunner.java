package com.nbb.spider.manager.task.impl.letv;

import org.springframework.stereotype.Service;

@Service
public class LetvMonthlyTaskRunner extends LetvTaskRunner {

	@Override
	int getTabIndex() {
		return 2;
	}

}

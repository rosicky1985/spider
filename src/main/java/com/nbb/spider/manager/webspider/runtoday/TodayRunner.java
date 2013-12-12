package com.nbb.spider.manager.webspider.runtoday;

import java.util.Calendar;
import java.util.Date;

public class TodayRunner {
	public void run(Runner runner, Date now) {
		runner.runDaily();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
			runner.runWeekly();
		}
		if (c.get(Calendar.DAY_OF_MONTH) == 3) {
			runner.runMonthly();
		}
	}
}

package com.nbb.spider.manager.task;

import java.util.Calendar;
import java.util.Date;

import com.nbb.spider.entity.Task;
import com.nbb.spider.entity.full.Type;

public class TaskFactory {

	public static Task createTask(Type type,Date created) {
		Task t = new Task();
		t.setCreated(created);
		Calendar c = Calendar.getInstance();
		c.setTime(created);
		if(type.equals(Type.DAILY)){
			c.add(Calendar.DAY_OF_MONTH, -1);
		}else if(type.equals(Type.WEEKILY)){
			c.add(Calendar.WEEK_OF_MONTH,-1);
		}else if(type.equals(Type.MONTHLY)){
			c.add(Calendar.MONTH,-1);
		}
		t.setTarget(c.getTime());
		return t;
	}

}

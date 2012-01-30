package com.nbb.spider.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbb.spider.manager.task.SpiderTask;

@Controller
public class TaskController {
	@Autowired
	private SpiderTask spiderTask;

	@RequestMapping(value = "/task/run.do")
	public void run() throws IOException {
		spiderTask.run();
	}
}

package com.nbb.spider.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbb.spider.manager.webspider.TaskRunnerManager;

@Controller
public class TaskController {
	@Autowired
	private TaskRunnerManager taskRunnerManager;

	@RequestMapping({ "/api/task/run.do" })
	public String run() throws IOException {
		this.taskRunnerManager.rullAll();
		return "redirect:/task/list.do";
	}
}

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

	@RequestMapping({ "/task/run/all" })
	public String run() throws IOException {
		this.taskRunnerManager.rullAll();
		return "redirect:/task/list.do";
	}

	@RequestMapping({ "/task/run/daily" })
	public String daily() throws IOException {
		this.taskRunnerManager.runDaily();
		return "redirect:/task/list.do";
	}

	@RequestMapping({ "/task/run/weekly" })
	public String weekly() throws IOException {
		this.taskRunnerManager.runWeekly();
		return "redirect:/task/list.do";
	}

	@RequestMapping({ "/task/run/monthly" })
	public String monthly() throws IOException {
		this.taskRunnerManager.runMonthly();
		return "redirect:/task/list.do";
	}
}

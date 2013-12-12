package com.nbb.spider.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbb.spider.manager.webspider.TaskRunnerManager;

@Controller
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskRunnerManager taskRunnerManager;

	@RequestMapping({ "/run/all" })
	public String run() throws IOException {
		this.taskRunnerManager.rullAll();
		return "redirect:/api/";
	}

	@RequestMapping({ "/run/daily" })
	public String daily() throws IOException {
		this.taskRunnerManager.runDaily();
		return "redirect:/api/";
	}

	@RequestMapping({ "/run/weekly" })
	public String weekly() throws IOException {
		this.taskRunnerManager.runWeekly();
		return "redirect:/api/";
	}

	@RequestMapping({ "/run/monthly" })
	public String monthly() throws IOException {
		this.taskRunnerManager.runMonthly();
		return "redirect:/api/";
	}

	@RequestMapping({ "/run/today" })
	public String today() throws IOException {
		this.taskRunnerManager.runToday();
		return "redirect:/api/";
	}
}

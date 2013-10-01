package com.nbb.spider.manager.taskrunnerimpl;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.nbb.spider.entity.Task;
import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.FullItem;
import com.nbb.spider.manager.task.AbstractTaskRunner;
import com.nbb.spider.manager.task.TaskRunner;
import com.nbb.spider.util.Utils;

/**
 * 抓取百度的每日数据
 * 
 * @author rosicky
 * 
 */
public class BaiduDailyTaskRunner extends AbstractTaskRunner implements
		TaskRunner {
	@Override
	public List<FullItem> run(DataSource dataSource, Task task) {
		try {
			Document doc = getRootDocument(dataSource.getUrl());
			Element tabb = doc.getElementsByClass("list-table").first();
			List<FullItem> allLine = new Runner(dataSource, task).loopAllLine(
					tabb, "tr", true);
			return allLine;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private class Runner extends AbstractRunner {
		public Runner(DataSource dataSource, Task task) {
			super(dataSource, task);
		}

		protected FullItem parseOneline(Element ele) {
			if (ele.hasClass("item-tr"))
				return null;
			FullItem item = getFullItem();
			item.setRank(Utils.atoi(ele.getElementsByClass("first").first()
					.text()));
			item.setTitle(ele.getElementsByClass("keyword").first().text());
			item.setIndex(Utils.atoi(ele.getElementsByClass("last").first()
					.getElementsByTag("span").first().text()));
			return item;
		}
	}
}

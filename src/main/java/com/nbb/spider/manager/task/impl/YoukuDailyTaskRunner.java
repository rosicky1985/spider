package com.nbb.spider.manager.task.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.nbb.spider.dao.PersonDao;
import com.nbb.spider.entity.Task;
import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.FullItem;
import com.nbb.spider.entity.full.Person;
import com.nbb.spider.manager.task.AbstractTaskRunner;
import com.nbb.spider.manager.task.TaskRunner;
import com.nbb.spider.util.Utils;

/**
 * 抓取优酷的每日数据
 * 
 * @author rosicky
 * 
 */
public class YoukuDailyTaskRunner extends AbstractTaskRunner implements
		TaskRunner {
	@Autowired
	private PersonDao personDao;

	@Override
	public List<FullItem> run(DataSource dataSource, Task task) {
		try {
			Document doc = getRootDocument(dataSource.getUrl());
			Element tabb = doc.getElementsByClass("rank").first()
					.getElementsByTag("tbody").first();
			List<FullItem> allLine = new Runner(dataSource, task).loopAllLine(
					tabb, "tr", false);
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
			FullItem item = super.getFullItem();
			item.setRank(Utils.atoi(ele.getElementsByClass("order").first()
					.text()));
			item.setTitle(ele.getElementsByClass("key").first().text());
			item.setIndex(Utils.atoi(ele.getElementsByClass("status").first()
					.text()));
			List<Person> actors = new ArrayList<Person>();
			Iterator<Element> itr = ele.getElementsByClass("intro").first()
					.getElementsByTag("a").iterator();
			while (itr.hasNext()) {
				String name = ((Element) itr.next()).text();
				actors.add(personDao.getByName(name));
			}
			item.setActors(actors);
			return item;
		}
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}

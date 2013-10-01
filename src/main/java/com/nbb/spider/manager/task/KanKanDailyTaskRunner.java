package com.nbb.spider.manager.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.nbb.spider.dao.CateGoryDao;
import com.nbb.spider.dao.PersonDao;
import com.nbb.spider.entity.Task;
import com.nbb.spider.entity.full.Category;
import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.FullItem;
import com.nbb.spider.entity.full.Person;
import com.nbb.spider.manager.taskrunnerimpl.AbstractRunner;
import com.nbb.spider.util.Utils;

public class KanKanDailyTaskRunner extends AbstractTaskRunner implements
		TaskRunner {
	@Autowired
	private PersonDao personDao;
	@Autowired
	private CateGoryDao cateGoryDao;
	@Override
	public List<FullItem> run(DataSource dataSource, Task task) {
		try {
			Document doc = getRootDocument(dataSource.getUrl());
			Element tabb = doc.getElementsByClass("ranklist_wide").first();
			List<FullItem> allLine = new Runner(dataSource, task).loopAllLine(
					tabb, "li", false);
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
			FullItem kankan = getFullItem();
			kankan.setRank(Utils
					.atoi(ele.getElementsByTag("em").first().text()));
			kankan.setTitle(ele.getElementsByTag("p").first()
					.getElementsByTag("a").first().text());
			Element amc = ele.getElementsByTag("div").first();
			String amctext = amc.text();
			String[] amcsplit = amctext.split("\\|");
			if (amcsplit != null) {
				if (amcsplit.length >= 1)
					kankan.setArea(amcsplit[0]);
				if (amcsplit.length >= 2) {
					String str_actors = amcsplit[1];
					List<Person> actors = new ArrayList<Person>();
					StringTokenizer tokens = new StringTokenizer(str_actors,
							" ");
					while (tokens.hasMoreTokens()) {
						String name = tokens.nextToken();
						actors.add(personDao.getPerson(name));
					}
					kankan.setActors(actors);
				}
				if (amcsplit.length >= 3) {
					String str_categories = amcsplit[2];
					List<Category> categories = new ArrayList<Category>();
					StringTokenizer tokens = new StringTokenizer(str_categories,
							" ");
					while (tokens.hasMoreTokens()) {
						String name = tokens.nextToken();
						categories.add(cateGoryDao.getCateGory(name));
					}
					kankan.setCategories(categories);
				}
			}
			kankan.setIndex(Utils.atoi(ele.getElementsByTag("span").first()
					.text()));
			return kankan;
		}
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public CateGoryDao getCateGoryDao() {
		return cateGoryDao;
	}

	public void setCateGoryDao(CateGoryDao cateGoryDao) {
		this.cateGoryDao = cateGoryDao;
	}
}

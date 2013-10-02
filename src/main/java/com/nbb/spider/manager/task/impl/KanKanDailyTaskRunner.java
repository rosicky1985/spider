package com.nbb.spider.manager.task.impl;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.nbb.spider.dao.CategoryDao;
import com.nbb.spider.dao.PersonDao;
import com.nbb.spider.entity.Task;
import com.nbb.spider.entity.full.Category;
import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.FullItem;
import com.nbb.spider.entity.full.Person;
import com.nbb.spider.manager.task.AbstractTaskRunner;
import com.nbb.spider.manager.task.TaskRunner;
import com.nbb.spider.util.Utils;

public class KanKanDailyTaskRunner extends AbstractTaskRunner implements
		TaskRunner {
	@Autowired
	private PersonDao personDao;
	@Autowired
	private CategoryDao cateGoryDao;

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
					List<Person> actors = new DimensionParser<Person>()
							.parseFromStringTokens(str_actors, " ", personDao);
					kankan.setActors(actors);
				}
				if (amcsplit.length >= 3) {
					String str_categories = amcsplit[2];
					List<Category> categories = new DimensionParser<Category>()
							.parseFromStringTokens(str_categories, " ", cateGoryDao);
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

	public CategoryDao getCateGoryDao() {
		return cateGoryDao;
	}

	public void setCateGoryDao(CategoryDao cateGoryDao) {
		this.cateGoryDao = cateGoryDao;
	}
}

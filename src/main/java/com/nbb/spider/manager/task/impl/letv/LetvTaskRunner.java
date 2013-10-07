package com.nbb.spider.manager.task.impl.letv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import com.nbb.spider.manager.task.impl.AbstractRunner;
import com.nbb.spider.manager.task.impl.DimensionParser;
import com.nbb.spider.util.Utils;

/**
 * 乐视视频排名抓取
 * @author rosicky
 *
 */
public abstract class LetvTaskRunner extends AbstractTaskRunner implements
		TaskRunner {
	@Autowired
	private PersonDao personDao;
	@Autowired
	private CategoryDao categoryDao;

	abstract int getTabIndex();

	@Override
	public List<FullItem> run(DataSource dataSource, Task task) {
		try {
			Document doc = getRootDocument(dataSource.getUrl());
			Elements j_fors = doc.getElementsByClass("j-for");
			Element tabb = j_fors.get(getTabIndex());
			List<FullItem> ranks = new ArrayList<FullItem>();
			Elements trs = tabb.getElementsByTag("li");// 所有的行
			Iterator<Element> itr = trs.iterator();
			boolean first = true;
			while (itr.hasNext()) {
				Element tr = itr.next();
				if(tr.hasClass("chart-cut")){
					continue;
				}
				if (first) {
					first = false;
					if (true) {
						continue;
					}
				}
				FullItem rank = new Runner(dataSource, task).parseOneline(tr);
				if (rank != null)
					ranks.add(rank);
			}
			List<FullItem> allLine = ranks;
			return allLine;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private class Runner extends AbstractRunner {

		public Runner(DataSource dataSource, Task task) {
			super(dataSource, task);
		}

		protected FullItem parseOneline(Element ele) {
			FullItem l = getFullItem();
			l.setRank(Utils.atoi(ele.getElementsByClass("t-1").first().text()));
			l.setTitle(ele.getElementsByClass("t-2").first().text());
			String actors = ele.getElementsByClass("t-10").first().text();
			l.setActors(new DimensionParser<Person>().parseFromStringTokens(actors,
					" ", personDao));
			l.setArea(ele.getElementsByClass("t-4").first().text());
			String categories = ele.getElementsByClass("t-4").get(1).text();
			l.setCategories(new DimensionParser<Category>().parseFromStringTokens(
					categories, " ", categoryDao));
			l.setIndex(Utils.atoi(ele.getElementsByClass("t-5").get(0).text()));
			return l;
		}
	}


	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}

package com.nbb.spider.manager.task.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.nbb.spider.dao.CategoryDao;
import com.nbb.spider.dao.PersonDao;
import com.nbb.spider.entity.full.Category;
import com.nbb.spider.entity.full.Company;
import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.FullItem;
import com.nbb.spider.entity.full.Person;
import com.nbb.spider.entity.full.Type;
import com.nbb.spider.manager.task.impl.letv.LetvDailyTaskRunner;
import com.nbb.spider.manager.task.impl.letv.LetvMonthlyTaskRunner;
import com.nbb.spider.manager.task.impl.letv.LetvTaskRunner;
import com.nbb.spider.manager.task.impl.letv.LetvWeeklyTaskRunner;

public class LetvTaskRunnerTest {
	@Test
	public void testDailyTv() {
		Company letv = new Company(1, "letv");
		DataSource ltv = new DataSource(1, "ltv", letv,
				"http://top.letv.com/tvhp.html", Type.DAILY);
		LetvDailyTaskRunner letvrunner = new LetvDailyTaskRunner();
		initRunner(letvrunner);
		List<FullItem> tv = letvrunner.run(ltv, null);
		assertResult(tv);
	}
	
	@Test
	public void testWeeklyTv() {
		Company letv = new Company(1, "letv");
		DataSource ltv = new DataSource(1, "ltv", letv,
				"http://top.letv.com/tvhp.html", Type.WEEKILY);
		LetvWeeklyTaskRunner letvrunner = new LetvWeeklyTaskRunner();
		initRunner(letvrunner);
		List<FullItem> tv = letvrunner.run(ltv, null);
		assertResult(tv);
	}
	
	@Test
	public void testMonthlyTv() {
		Company letv = new Company(1, "letv");
		DataSource ltv = new DataSource(1, "ltv", letv,
				"http://top.letv.com/tvhp.html", Type.MONTHLY);
		LetvMonthlyTaskRunner letvrunner = new LetvMonthlyTaskRunner();
		initRunner(letvrunner);
		List<FullItem> tv = letvrunner.run(ltv, null);
		assertResult(tv);
	}

	private void initRunner(LetvTaskRunner letvrunner1) {
		letvrunner1.setPersonDao(new PersonDao() {
		
			@Override
			public Person getByName(String name) {
				return new Person(1, name);
			}
		
		});
		letvrunner1.setCategoryDao(new CategoryDao() {
		
			@Override
			public Category getByName(String name) {
				return new Category(1, name);
			}
		
		});
	}
	
	@Test
	public void testDailyMovie() {
		Company letv = new Company(1, "letv");
		DataSource lmovie = new DataSource(1, "lmovie", letv,
				"http://top.letv.com/filmhp.html", Type.DAILY);
		LetvDailyTaskRunner letvrunner = new LetvDailyTaskRunner();
		initRunner(letvrunner);
		List<FullItem> movie = letvrunner.run(lmovie, null);
		assertResult(movie);
	}


	private void assertResult(List<FullItem> result) {
		assertEquals(50, result.size());
		for (int i = 0; i <= 49; i++) {
			FullItem item = result.get(i);
			System.out.println(item);
			assertEquals(i + 1, item.getRank().intValue());
			assertNotNull(item.getTitle());
			assertNotNull(item.getDataSource());
			assertNotNull(item.getIndex());
			Assert.assertTrue(item.getIndex() != 0);
			assertNotNull(item.getActors());
			Assert.assertTrue(item.getActors().size() != 0);
			assertNotNull(item.getCategories());
			Assert.assertTrue(item.getCategories().size() != 0);
			assertNotNull(item.getArea());
		}
	}
}

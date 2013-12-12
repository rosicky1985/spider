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
import com.nbb.spider.manager.task.impl.sohu.SohuTaskRunner;

/**
 * 参考: <a href="http://tv.sohu.com/hotdrama/">TV</a> <a
 * href="http://tv.sohu.com/hotmovie/">Moive</a>
 * 
 * @author rosicky
 * 
 */
public class SohuTaskRunnerTest {
	@Test
	public void testDailyTv() {
		Company sohu = new Company(1, "sohu");
		DataSource ltv = new DataSource(1, "sohu", sohu, SohuTaskRunner.TVS[0],
				Type.DAILY);
		SohuTaskRunner runer = new SohuTaskRunner();
		initRunner(runer);
		List<FullItem> tv = runer.run(ltv, null);
		assertResult(tv);
	}

	@Test
	public void testWeeklyTv() {
		Company sohu = new Company(1, "sohu");
		DataSource ltv = new DataSource(1, "sohu", sohu, SohuTaskRunner.TVS[1],
				Type.WEEKILY);
		SohuTaskRunner runer = new SohuTaskRunner();
		initRunner(runer);
		List<FullItem> tv = runer.run(ltv, null);
		assertResult(tv);
	}

	@Test
	public void testMonthlyTv() {
		Company sohu = new Company(1, "sohu");
		DataSource ltv = new DataSource(1, "sohu", sohu, SohuTaskRunner.TVS[2],
				Type.MONTHLY);
		SohuTaskRunner runer = new SohuTaskRunner();
		initRunner(runer);
		List<FullItem> tv = runer.run(ltv, null);
		assertResult(tv);
	}

	@Test
	public void testDailyMovie() {
		Company sohu = new Company(1, "sohu");
		DataSource ltv = new DataSource(1, "sohu", sohu,
				SohuTaskRunner.MOVIES[0], Type.DAILY);
		SohuTaskRunner runer = new SohuTaskRunner();
		initRunner(runer);
		List<FullItem> tv = runer.run(ltv, null);
		assertResult(tv);
	}

	@Test
	public void testWeeklyMovie() {
		Company sohu = new Company(1, "sohu");
		DataSource ltv = new DataSource(1, "sohu", sohu,
				SohuTaskRunner.MOVIES[1], Type.WEEKILY);
		SohuTaskRunner runer = new SohuTaskRunner();
		initRunner(runer);
		List<FullItem> tv = runer.run(ltv, null);
		assertResult(tv);
	}

	@Test
	public void testMonthlyMovie() {
		Company sohu = new Company(1, "sohu");
		DataSource ltv = new DataSource(1, "sohu", sohu,
				SohuTaskRunner.MOVIES[2], Type.MONTHLY);
		SohuTaskRunner runer = new SohuTaskRunner();
		initRunner(runer);
		List<FullItem> tv = runer.run(ltv, null);
		assertResult(tv);
	}

	private void initRunner(SohuTaskRunner run) {
		run.setPersonDao(new PersonDao() {
			@Override
			public Person getByName(String name) {
				return new Person(1, name);
			}

		});
		run.setCategoryDao(new CategoryDao() {
			@Override
			public Category getByName(String name) {
				return new Category(1, name);
			}

		});
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
//			Assert.assertTrue(item.getCategories().size() != 0);
			assertNotNull(item.getDirector());
			assertNotNull(item.getArea());
		}
	}

}

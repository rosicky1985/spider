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
import com.nbb.spider.manager.task.impl.qiyi.QiyiDailyTaskRunner;
import com.nbb.spider.manager.task.impl.qiyi.QiyiTaskRunner;
import com.nbb.spider.manager.task.impl.qiyi.QiyiWeeklyTaskRunner;

public class QiyiTaskRunnerTest {
	@Test
	public void testDailyTv() {
		Company qiyi = new Company(1, "qiyi");
		DataSource ltv = new DataSource(1, "qtv", qiyi, QiyiTaskRunner.TVDAILY,
				Type.DAILY);
		QiyiTaskRunner runer = new QiyiDailyTaskRunner();
		initRunner(runer);
		List<FullItem> tv = runer.run(ltv, null);
		assertResult(tv);
	}

	@Test
	public void testWeeklyTv() {
		Company qiyi = new Company(1, "qiyi");
		DataSource ltv = new DataSource(1, "qtv", qiyi,
				QiyiTaskRunner.TVWEEKLY, Type.DAILY);
		QiyiTaskRunner runer = new QiyiWeeklyTaskRunner();
		initRunner(runer);
		List<FullItem> tv = runer.run(ltv, null);
		assertResult(tv);
	}

	@Test
	public void testDailyMovie() {
		Company qiyi = new Company(1, "qiyi");
		DataSource ltv = new DataSource(1, "qtv", qiyi, QiyiTaskRunner.MVDAILY,
				Type.DAILY);
		QiyiTaskRunner runer = new QiyiDailyTaskRunner();
		initRunner(runer);
		List<FullItem> tv = runer.run(ltv, null);
		assertResult(tv);
	}

	@Test
	public void testWeeklyMovie() {
		Company qiyi = new Company(1, "qiyi");
		DataSource ltv = new DataSource(1, "qtv", qiyi,
				QiyiTaskRunner.MVWEEKLY, Type.DAILY);
		QiyiTaskRunner runer = new QiyiWeeklyTaskRunner();
		initRunner(runer);
		List<FullItem> tv = runer.run(ltv, null);
		assertResult(tv);
	}

	private void initRunner(QiyiTaskRunner qrun) {
		qrun.setPersonDao(new PersonDao() {

			@Override
			public Person getByName(String name) {
				return new Person(1, name);
			}

		});
		qrun.setCategoryDao(new CategoryDao() {

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
			Assert.assertTrue(item.getCategories().size() != 0);
		}
	}
}

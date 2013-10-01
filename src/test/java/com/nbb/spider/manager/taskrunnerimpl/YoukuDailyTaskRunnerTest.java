package com.nbb.spider.manager.taskrunnerimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.nbb.spider.dao.PersonDao;
import com.nbb.spider.entity.full.Company;
import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.FullItem;
import com.nbb.spider.entity.full.Person;
import com.nbb.spider.entity.full.Type;

public class YoukuDailyTaskRunnerTest {
	@Test
	public void testRun() {
		Company youku = new Company(1, "youku");
		DataSource youkutv = new DataSource(1, "baidutv", youku,
				"http://index.youku.com/protop/0", Type.DAILY);
		DataSource youkumoivie = new DataSource(1, "baidutv", youku,
				"http://index.youku.com/protop/2", Type.DAILY);
		YoukuDailyTaskRunner youkuTaskRunner = new YoukuDailyTaskRunner();
		youkuTaskRunner.setPersonDao(new PersonDao() {

			@Override
			public Person getPerson(String name) {
				return new Person(1, name);
			}

		});
		List<FullItem> tv = youkuTaskRunner.run(youkutv, null);
		List<FullItem> movie = youkuTaskRunner.run(youkumoivie, null);
		assertBaiduResult(tv);
		assertBaiduResult(movie);
	}

	private void assertBaiduResult(List<FullItem> baiduResults) {
		assertEquals(50, baiduResults.size());
		for (int i = 0; i <= 49; i++) {
			FullItem item = baiduResults.get(i);
			System.out.println(item);
			assertEquals(i + 1, item.getRank().intValue());
			assertNotNull(item.getTitle());
			assertNotNull(item.getDataSource());
			assertNotNull(item.getIndex());
			Assert.assertTrue(item.getIndex() != 0);
			assertNotNull(item.getActors());
			Assert.assertTrue(item.getActors().size() != 0);
		}
	}
}

package com.nbb.spider.manager.taskrunnerimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.nbb.spider.dao.CateGoryDao;
import com.nbb.spider.dao.PersonDao;
import com.nbb.spider.entity.full.Category;
import com.nbb.spider.entity.full.Company;
import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.FullItem;
import com.nbb.spider.entity.full.Person;
import com.nbb.spider.entity.full.Type;
import com.nbb.spider.manager.task.KanKanDailyTaskRunner;

public class KanKanDailyTaskRunnerTest {
	@Test
	public void testRun() {
		Company kankan = new Company(1, "kankan");
		DataSource kankantv = new DataSource(1, "kankantv", kankan,
				"http://movie.kankan.com/top/teleplay.html", Type.DAILY);
		DataSource kankanmovie = new DataSource(1, "kankanmovie", kankan,
				"http://movie.kankan.com/top/movie.html", Type.DAILY);
		KanKanDailyTaskRunner kankanTaskRunner = new KanKanDailyTaskRunner();
		kankanTaskRunner.setPersonDao(new PersonDao() {

			@Override
			public Person getPerson(String name) {
				return new Person(1, name);
			}

		});
		kankanTaskRunner.setCateGoryDao(new CateGoryDao() {

			@Override
			public Category getCateGory(String name) {
				return new Category(1, name);
			}

		});
		List<FullItem> tv = kankanTaskRunner.run(kankantv, null);
		List<FullItem> movie = kankanTaskRunner.run(kankanmovie, null);
		assertBaiduResult(tv);
		assertBaiduResult(movie);
	}

	private void assertBaiduResult(List<FullItem> result) {
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

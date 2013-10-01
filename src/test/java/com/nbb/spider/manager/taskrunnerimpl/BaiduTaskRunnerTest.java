package com.nbb.spider.manager.taskrunnerimpl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.nbb.spider.entity.full.Company;
import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.FullItem;
import com.nbb.spider.entity.full.Type;

public class BaiduTaskRunnerTest {
	@Test
	public void testRun() {
		Company baidu = new Company(1, "baidu");
		DataSource baiduTv = new DataSource(1, "baidutv", baidu,
				"http://top.baidu.com/buzz.php?p=tv", Type.DAILY);
		DataSource baiduMovie = new DataSource(1, "baidutv", baidu,
				"http://top.baidu.com/buzz.php?p=movie", Type.DAILY);
		BaiduDailyTaskRunner baiduTaskRunner = new BaiduDailyTaskRunner();
		List<FullItem> tv = baiduTaskRunner.run(baiduTv, null);
		List<FullItem> movie = baiduTaskRunner.run(baiduMovie, null);
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
		}
	}
}

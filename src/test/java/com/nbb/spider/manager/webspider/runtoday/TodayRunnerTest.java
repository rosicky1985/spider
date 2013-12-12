package com.nbb.spider.manager.webspider.runtoday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TodayRunnerTest {

	@Test
	public void testDaily() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final List<Integer> result = Arrays.asList(0, 0, 0);
		TodayRunner runner = new TodayRunner();
		Runner run = new Runner() {
			@Override
			public void runDaily() {
				result.set(0, 1);
			}

			@Override
			public void runWeekly() {
				result.set(1, 1);

			}

			@Override
			public void runMonthly() {
				result.set(2, 1);
			}
		};
		runner.run(run, sdf.parse("2013-12-12 15:43:00"));
		Assert.assertArrayEquals(Arrays.asList(1, 0, 0).toArray(),
				result.toArray());
	}

	@Test
	public void testWeekly() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final List<Integer> result = Arrays.asList(0, 0, 0);
		TodayRunner runner = new TodayRunner();
		Runner run = new Runner() {
			@Override
			public void runDaily() {
				result.set(0, 1);
			}

			@Override
			public void runWeekly() {
				result.set(1, 1);

			}

			@Override
			public void runMonthly() {
				result.set(2, 1);
			}
		};
		runner.run(run, sdf.parse("2013-12-10 15:43:00"));
		Assert.assertArrayEquals(Arrays.asList(1, 1, 0).toArray(),
				result.toArray());
	}

	@Test
	public void testMonthly1() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final List<Integer> result = Arrays.asList(0, 0, 0);
		TodayRunner runner = new TodayRunner();
		Runner run = new Runner() {
			@Override
			public void runDaily() {
				result.set(0, 1);
			}

			@Override
			public void runWeekly() {
				result.set(1, 1);

			}

			@Override
			public void runMonthly() {
				result.set(2, 1);
			}
		};
		runner.run(run, sdf.parse("2013-12-03 15:43:00"));
		Assert.assertArrayEquals(Arrays.asList(1, 1, 1).toArray(),
				result.toArray());
	}

	@Test
	public void testMonthly2() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final List<Integer> result = Arrays.asList(0, 0, 0);
		TodayRunner runner = new TodayRunner();
		Runner run = new Runner() {
			@Override
			public void runDaily() {
				result.set(0, 1);
			}

			@Override
			public void runWeekly() {
				result.set(1, 1);

			}

			@Override
			public void runMonthly() {
				result.set(2, 1);
			}
		};
		runner.run(run, sdf.parse("2013-11-03 15:43:00"));
		Assert.assertArrayEquals(Arrays.asList(1, 0, 1).toArray(),
				result.toArray());
	}
}

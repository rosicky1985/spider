package com.nbb.spider.manager.task.impl.sohu;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nbb.spider.dao.CategoryDao;
import com.nbb.spider.dao.PersonDao;
import com.nbb.spider.entity.Task;
import com.nbb.spider.entity.full.Category;
import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.FullItem;
import com.nbb.spider.entity.full.Person;
import com.nbb.spider.manager.task.AbstractTaskRunner;
import com.nbb.spider.manager.task.TaskRunner;
import com.nbb.spider.manager.task.impl.DimensionParser;
import com.nbb.spider.util.Utils;

public class SohuTaskRunner extends AbstractTaskRunner implements TaskRunner {
	public static String[] MOVIES = {
			"http://tv.sohu.com/frag/vrs_inc/phb_mv_day_50.js",
			"http://tv.sohu.com/frag/vrs_inc/phb_mv_week_50.js",
			"http://tv.sohu.com/frag/vrs_inc/phb_mv_month_50.js" };
	public static String[] TVS = {
			"http://tv.sohu.com/frag/vrs_inc/phb_tv_day_50.js",
			"http://tv.sohu.com/frag/vrs_inc/phb_tv_week_50.js",
			"http://tv.sohu.com/frag/vrs_inc/phb_tv_month_50.js" };

	private PersonDao personDao;
	private CategoryDao categoryDao;

	@Override
	public List<FullItem> run(DataSource dataSource, Task task) {
		try {
			List<FullItem> ret1 = new ArrayList<FullItem>();
			String line = Utils.httpGet(dataSource.getUrl(),"GBK");
			int indexOfStart = line.indexOf('{');
			String jsoncontent = line.substring(indexOfStart);
			JSONObject root = new JSONObject(jsoncontent);
			JSONArray arrays = root.getJSONArray("videos");
			for (int i = 0; i < arrays.length(); i++) {
				FullItem item = new FullItem();
				item.setDataSource(dataSource);
				item.setTask(task);
				JSONObject video = arrays.getJSONObject(i);
				String tv_name = video.getString("tv_name");
				int rank = i + 1;
				int count = video.getInt("tv_count");
				String mainActor = video.getString("MAIN_ACTOR");
				String director = video.getString("DIRECTOR");
				item.setIndex(count);
				item.setTitle(tv_name);
				item.setRank(rank);
				DimensionParser<Person> dp = new DimensionParser<Person>();
				item.setActors(dp.parseFromStringTokens(mainActor, ";",
						personDao));
				item.setDirector(dp.parseFromStringTokens(director, ";",
						personDao).get(0));
				item.setCategories(new DimensionParser<Category>()
						.parseFromJsonArray(video.getJSONArray("tv_cont_cats"),
								categoryDao, null));
				item.setArea(video.getString("area"));
				ret1.add(item);
			}
			return ret1;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
}

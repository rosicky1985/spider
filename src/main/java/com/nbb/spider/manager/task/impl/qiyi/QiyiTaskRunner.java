package com.nbb.spider.manager.task.impl.qiyi;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
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
import com.nbb.spider.manager.task.impl.DimensionParser;
import com.nbb.spider.util.Utils;

/**
 * 从奇邑的Json api中获取 <br/>
 * <a href="http://top.iqiyi.com/top50_1.html">ref</a>
 * @author rosicky
 * 
 */
public abstract class QiyiTaskRunner extends AbstractTaskRunner implements TaskRunner {
	protected static final String INDEX_YESTERDAY = "album_count_yesterday";
	protected static final String INDEX_LASTWEEK = "album_count_lastweek";
	public static String TVDAILY= "http://top.inter.qiyi.com/index/front?cid=2&dim=day&len=50&area=top&time=1380693814851";
	public static String TVWEEKLY ="http://top.inter.qiyi.com/index/front?cid=2&dim=wee&len=50&area=top&time=1380693814851";
	public static String MVWEEKLY = "http://top.inter.qiyi.com/index/front?cid=1&dim=wee&len=50&area=top&time=1380693546402";
	public static String MVDAILY = "http://top.inter.qiyi.com/index/front?cid=1&dim=day&len=50&area=top&time=1380693546402";
	@Autowired
	private PersonDao personDao;
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<FullItem> run(DataSource dataSource, Task task) {
		try {
			String response = Utils.httpGet(dataSource.getUrl(),"utf-8");
			JSONObject root = new JSONObject(response);
			JSONArray jarray = root.getJSONArray("data");
			List<FullItem> ret = new ArrayList<FullItem>();
			for (int i = 0; i < jarray.length(); i++) {
				FullItem item = new FullItem();
				item.setDataSource(dataSource);
				item.setTask(task);
				JSONObject obj = jarray.getJSONObject(i);
				item.setTitle(obj.getString("album_name"));
				item.setIndex(obj.getInt(getIndexTag()));
				item.setRank(i + 1);
				item.setActors(new DimensionParser<Person>()
						.parseFromJsonArray(
								obj.getJSONArray("album_main_actor"),
								personDao, "tag_name"));
				item.setCategories(new DimensionParser<Category>()
						.parseFromJsonArray(obj.getJSONArray("album_tags"),
								categoryDao, "tag_name"));
				ret.add(item);
			}
			return ret;
		} catch (Exception e) {
			return null;
		}
	}

	abstract protected String getIndexTag();

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

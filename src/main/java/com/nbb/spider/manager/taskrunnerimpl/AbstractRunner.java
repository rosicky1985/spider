package com.nbb.spider.manager.taskrunnerimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nbb.spider.entity.Task;
import com.nbb.spider.entity.full.DataSource;
import com.nbb.spider.entity.full.FullItem;

public abstract class AbstractRunner {
	protected DataSource dataSource;
	protected Task task;

	protected AbstractRunner(DataSource dataSource, Task task) {
		this.dataSource = dataSource;
		this.task = task;
	}

	protected List<FullItem> loopAllLine(Element ele, String lineTag,
			boolean skipFirst) {
		List<FullItem> ranks = new ArrayList<FullItem>();
		Elements trs = ele.getElementsByTag(lineTag);// 所有的行
		Iterator<Element> itr = trs.iterator();
		boolean first = true;
		while (itr.hasNext()) {
			Element tr = itr.next();
			if (first) {
				first = false;
				if (skipFirst) {
					continue;
				}
			}
			FullItem rank = parseOneline(tr);
			if (rank != null)
				ranks.add(rank);
		}
		return ranks;
	}

	abstract protected FullItem parseOneline(Element ele);

	protected FullItem getFullItem() {
		FullItem item = new FullItem();
		item.setDataSource(dataSource);
		item.setTask(task);
		return item;
	}
}

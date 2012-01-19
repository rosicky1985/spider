package com.nbb.spider.manager.webspider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.nbb.spider.entity.AbstractItem;
import com.nbb.spider.entity.Item;

public class YoukuSpider extends AbstractSpider implements Spider {
	private String targetUrl;
	public static final String YOUKU_MOVIE = "http://index.youku.com/vr_top/type_dy.html";
	public static final String YOUKU_TV = "http://index.youku.com/vr_top/type_tv.html";

	@Override
	public List<? extends Item> extract() {
		try {
			Document doc = getRootDocument(targetUrl);
			Element tabb = doc.getElementsByClass("rank").first()
					.getElementsByTag("tbody").first();
			List<Item> allLine = loopAllLine(tabb, "tr", false);
			AbstractItem.sort(allLine);
			return allLine;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected Item parseOneline(Element ele) {
		Youku item = new Youku();
		item.setRank(ele.getElementsByClass("order").first().text());
		item.setTitle(ele.getElementsByClass("key").first().text());
		item.setTrend(ele.getElementsByClass("status").first().text());
		List<String> actors = new ArrayList<String>();
		Iterator<Element> itr = ele.getElementsByClass("intro").first()
				.getElementsByTag("a").iterator();
		while (itr.hasNext()) {
			actors.add(itr.next().text());
		}
		item.setActors(actors);
		return item;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

}

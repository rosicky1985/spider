package com.nbb.spider.manager.webspider.impl;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.nbb.spider.entity.AbstractItem;
import com.nbb.spider.entity.Item;
import com.nbb.spider.entity.impl.Baidu;
import com.nbb.spider.manager.webspider.AbstractSpider;
import com.nbb.spider.manager.webspider.Spider;

public class BaiduSpider extends AbstractSpider implements Spider {
	public static final String BUIDU_MOVIE = "http://top.baidu.com/buzz.php?p=movie";
	public static final String BUIDU_TV = "http://top.baidu.com/buzz.php?p=tv";
	private String targetUrl;

	public List<? extends Item> extract() {
		try {
			Document doc = getRootDocument(this.targetUrl);
			Element tabb = doc.getElementsByClass("list-table").first();
			List<Item> allLine = loopAllLine(tabb, "tr", true);
			AbstractItem.sort(allLine);
			return allLine;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected Item parseOneline(Element ele) {
		if (ele.hasClass("item-tr"))
			return null;
		Baidu item = new Baidu();
		item.setRank(ele.getElementsByClass("first").first().text());
		item.setTitle(ele.getElementsByClass("keyword").first().text());
		item.setIndex(ele.getElementsByClass("last").first()
				.getElementsByTag("span").first().text());
		return item;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public static void main(String[] args) {
		BaiduSpider baidu = new BaiduSpider();
		baidu.setTargetUrl(BaiduSpider.BUIDU_MOVIE);
		List<? extends Item> baiduList = baidu.extract();
		System.out.println(baiduList.size());
	}
}

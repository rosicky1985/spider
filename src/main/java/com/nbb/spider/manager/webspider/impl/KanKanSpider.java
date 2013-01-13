package com.nbb.spider.manager.webspider.impl;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.nbb.spider.entity.AbstractItem;
import com.nbb.spider.entity.Item;
import com.nbb.spider.entity.impl.KanKan;
import com.nbb.spider.manager.webspider.AbstractSpider;
import com.nbb.spider.manager.webspider.Spider;

public class KanKanSpider extends AbstractSpider implements Spider {
	public static final String KANKAN_MOVIE = "http://movie.kankan.com/top/movie.html";
	public static final String KANKAN_TV = "http://movie.kankan.com/top/teleplay.html";
	private String targetUrl;

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public List<? extends Item> extract() {
		try {
			Document doc = getRootDocument(this.targetUrl);
			Element tabb = doc.getElementsByClass("ranklist_wide")
					.first();
			List<Item> allLine = loopAllLine(tabb, "li", false);
			AbstractItem.sort(allLine);
			return allLine;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected Item parseOneline(Element ele) {
		KanKan kankan = new KanKan();
		kankan.setRank(ele.getElementsByTag("em").first().text());
		kankan.setTitle(ele.getElementsByTag("p").first().getElementsByTag("a")
				.first().text());
		Element amc = ele.getElementsByTag("div").first();
		String amctext = amc.text();
		String[] amcsplit = amctext.split("\\|");
		if (amcsplit != null) {
			if (amcsplit.length >= 1)
				kankan.setArea(amcsplit[0]);
			if (amcsplit.length >= 2)
				kankan.setMainActor(amcsplit[1]);
			if (amcsplit.length >= 3)
				kankan.setCategory(amcsplit[2]);
		}
		kankan.setIndex(ele.getElementsByTag("span").first().text());
		return kankan;
	}
	
	public static void main(String[] args) {
		KanKanSpider s = new KanKanSpider();
		s.setTargetUrl(KANKAN_TV);
		List<? extends Item> list = s.extract();
		System.out.println(list.size());
	}
}

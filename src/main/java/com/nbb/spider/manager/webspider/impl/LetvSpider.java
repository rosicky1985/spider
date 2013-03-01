package com.nbb.spider.manager.webspider.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nbb.spider.entity.AbstractItem;
import com.nbb.spider.entity.Item;
import com.nbb.spider.entity.impl.Letv;
import com.nbb.spider.manager.webspider.AbstractSpider;
import com.nbb.spider.manager.webspider.Spider;

public class LetvSpider extends AbstractSpider implements Spider {
	private String targetUrl;

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public List<? extends Item> extract() {
		try {
			Element tabb = itr.next();
			List<Item> allLine = loopAllLine(tabb, "li", true);
			AbstractItem.sort(allLine);
			return allLine;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Item parseOneline(Element ele) {
		Letv l = new Letv();
		l.setRank(ele.getElementsByClass("s1").first().text());
		l.setTitle(ele.getElementsByClass("s2").first().text());
		l.setActors(ele.getElementsByClass("s3").first().text());
		l.setDirector(ele.getElementsByClass("s4").first().text());
		l.setCategory(ele.getElementsByClass("s5").first().text());
		return l;
	}

	public void movie() {
		this.targetUrl = "http://top.letv.com/filmhp.html";
	}

	private Iterator<Element> itr;

	public void start() throws IOException {
		Document doc = getRootDocument(this.targetUrl);
		Elements j_fors = doc.getElementsByClass("j-for");
		itr = j_fors.iterator();
	}

	public void tv() {
		this.targetUrl = "http://top.letv.com/tvhp.html";
	}

}

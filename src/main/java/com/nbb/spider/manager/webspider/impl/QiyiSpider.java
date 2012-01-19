package com.nbb.spider.manager.webspider.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nbb.spider.entity.AbstractItem;
import com.nbb.spider.entity.Item;
import com.nbb.spider.entity.impl.QiyiRank;
import com.nbb.spider.manager.webspider.AbstractSpider;
import com.nbb.spider.manager.webspider.Spider;

public class QiyiSpider extends AbstractSpider implements Spider {
	final static String QIYIURL = "http://www.iqiyi.com/common/ranking_mv.html";

	@Override
	protected Item parseOneline(Element tr) {
		QiyiRank qiyirank = new QiyiRank();
		String rank = tr.getElementsByClass("table1Num").first().text();
		qiyirank.setRank(rank);
		String title = tr.getElementsByClass("f14").first()
				.getElementsByTag("a").first().text();
		qiyirank.setTitle(title);
		Element keyworks_td = tr.getElementsByTag("td").get(2);
		Elements keyworklinks = keyworks_td.getElementsByTag("a");
		Iterator<Element> linkItr = keyworklinks.iterator();
		List<String> keywords = new ArrayList<String>();
		while (linkItr.hasNext()) {
			Element keywordLink = linkItr.next();
			String keyword = keywordLink.text();
			keywords.add(keyword);
		}
		qiyirank.setKeyworks(keywords);
		String increaseStr = tr.getElementsByTag("td").get(3)
				.getElementsByClass("fRed").first().text();
		qiyirank.setLastDayIncreased(increaseStr);
		return qiyirank;
	}

	@Override
	public List<? extends Item> extract() {
		try {
			Document doc = getRootDocument(QIYIURL);
			Element tabb = findTabByAttribute(doc, "pfb", "j-tab-cnt", "hot");// 热播榜
			List<Item> allLine = loopAllLine(tabb, "tr", false);
			AbstractItem.sort(allLine);
			return allLine;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @author chen.wenhui 2012-1-17 下午05:56:58
	 * @param doc
	 *            根dom节点
	 * @param TabClass
	 *            tab的class表示
	 * @param tabAttributeKey
	 *            tab的属性字段key
	 * @param tagAttributeValue
	 *            tab的属性字段value
	 * @return
	 */
	protected Element findTabByAttribute(Element doc, String TabClass,
			String tabAttributeKey, String tagAttributeValue) {
		Elements tabs = doc.getElementsByClass(TabClass);
		Iterator<Element> tabItr = tabs.iterator();
		while (tabItr.hasNext()) {
			Element tab = tabItr.next();
			if (tab.attr(tabAttributeKey).equals(tagAttributeValue)) {
				return tab;
			}
		}
		return null;
	}

}

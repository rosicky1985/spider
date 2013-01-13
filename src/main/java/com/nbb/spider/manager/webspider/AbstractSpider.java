package com.nbb.spider.manager.webspider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nbb.spider.entity.Item;

public abstract class AbstractSpider {

	public AbstractSpider() {
		super();
	}

	protected List<Item> loopAllLine(Element ele, String lineTag,
			boolean skipFirst) {
		List<Item> ranks = new ArrayList<Item>();
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
			Item rank = parseOneline(tr);
			if (rank != null)
				ranks.add(rank);
		}
		return ranks;
	}

	/**
	 * 通过url得到根dom节点
	 * 
	 * @author chen.wenhui 2012-1-17 下午05:56:36
	 * @param url
	 * @return
	 * @throws IOException
	 */
	protected Document getRootDocument(String url) throws IOException {
		Document doc;
		try {
			doc = Jsoup.connect(url).timeout(1000000).get();
		} catch (IOException e) {
			try {
				doc = Jsoup.connect(url).timeout(1000000).post();
			} catch (IOException ee) {
				throw ee;
			}
		}
		return doc;
	}

	protected abstract Item parseOneline(Element ele);

}
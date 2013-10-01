package com.nbb.spider.manager.task;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class AbstractTaskRunner {
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
}

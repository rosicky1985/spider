package com.nbb.spider.manager.task;

import java.io.IOException;

public interface SpiderTask {

	/**
	 * export a spider result to some destination
	 * @throws IOException
	 */
	public abstract void run() throws IOException;

}
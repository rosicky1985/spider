package com.nbb.spider.manager.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbb.spider.dao.TaskLogDao;
import com.nbb.spider.entity.Item;
import com.nbb.spider.entity.TaskLog;
import com.nbb.spider.manager.exporter.impl.BaiduExporter;
import com.nbb.spider.manager.exporter.impl.KanKanExporter;
import com.nbb.spider.manager.exporter.impl.LetvExporter;
import com.nbb.spider.manager.exporter.impl.QiyiExporter;
import com.nbb.spider.manager.exporter.impl.QiyiTop50Exporter;
import com.nbb.spider.manager.exporter.impl.SohuExporter;
import com.nbb.spider.manager.exporter.impl.YoukuExporter;
import com.nbb.spider.manager.webspider.impl.BaiduSpider;
import com.nbb.spider.manager.webspider.impl.KanKanSpider;
import com.nbb.spider.manager.webspider.impl.LetvSpider;
import com.nbb.spider.manager.webspider.impl.QiyiSpider;
import com.nbb.spider.manager.webspider.impl.QiyiTop50Spider;
import com.nbb.spider.manager.webspider.impl.SohuSpider;
import com.nbb.spider.manager.webspider.impl.YoukuSpider;

@Service("spiderTask")
public class SpiderTaskImpl implements SpiderTask {
	private static final Logger logger = LoggerFactory
			.getLogger(SpiderTask.class);
	private static final String KEY = "spided";
	private static final String EXTENSION = "xls";
	private String destination = "spidered/";

	@Autowired
	private TaskLogDao taskLogDao;

	public TaskLogDao getTaskLogDao() {
		return this.taskLogDao;
	}

	public void setTaskLogDao(TaskLogDao taskLogDao) {
		this.taskLogDao = taskLogDao;
	}

	@Transactional
	public void run() throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("spider task starting... @ " + sdf.format(new Date()));
		String realPath = getDestinationFullPathWithEncoding();
		String webRootPath = getWebRootPath();
		String fullPath = webRootPath + "resources/" + realPath;
		runAndSaveToWorkbook(fullPath);
		TaskLog log = new TaskLog();
		log.setCreated(new Date());
		log.setDestination(realPath);
		this.taskLogDao.save(log);
		logger.info("spider task ended @ " + sdf.format(new Date()));
	}

	public void runAndSaveToWorkbook(String fullPath) throws IOException,
			FileNotFoundException {
		QiyiExporter export = new QiyiExporter();
		Workbook wb = export.createWorkBook();
		qiyi(wb);
		baidu(wb);
		youku(wb);
		kankan(wb);
		letv(wb);
		sohu(wb);
		export.saveWorkBook(wb, fullPath);
	}

	protected String getDestinationFullPathWithEncoding() {
		StringBuffer sb = new StringBuffer();
		Long current = Long.valueOf(System.currentTimeMillis());
		int code = (int) (current.longValue() % 10000L);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String yyyyMMdd = sdf.format(new Date());

		sb.append(this.destination).append(KEY).append(".").append(yyyyMMdd)
				.append(".").append(code).append(".").append(EXTENSION);
		return sb.toString();
	}

	protected String getWebRootPath() {
		URL url = SpiderTaskImpl.class.getResource("SpiderTaskImpl.class");
		String temppath = url.toString();
		int startIndex = temppath.indexOf("file:");
		int index = temppath.indexOf("WEB-INF");
		String deployWarPath = temppath.substring(startIndex + "file:".length()
				+ 1, index);

		if ((System.getProperty("os.name").indexOf("Linux") != -1)
				&& (!deployWarPath.startsWith(File.separator))) {
			deployWarPath = File.separator + deployWarPath;
		}

		return deployWarPath;
	}

	private void kankan(Workbook wb) throws IOException {
		KanKanExporter exporter = new KanKanExporter();
		KanKanSpider spider = new KanKanSpider();
		spider.setTargetUrl(KanKanSpider.KANKAN_MOVIE);
		List<? extends Item> list = spider.extract();
		exporter.export(wb, list, "kankanMovie");
		spider.setTargetUrl(KanKanSpider.KANKAN_TV);
		list = spider.extract();
		exporter.export(wb, list, "kankan_tv");
	}

	protected void qiyi(Workbook wb) throws IOException {
		QiyiExporter export = new QiyiExporter();
		QiyiSpider spider = new QiyiSpider();
		List<? extends Item> oldQiyiRank1 = spider.extract();
		QiyiTop50Spider t50Spider = new QiyiTop50Spider();
		t50Spider.setTargetUrl("http://top.iqiyi.com/top50_1.html");
		List<? extends Item> qiyiMovie = t50Spider.extract();
		QiyiTop50Exporter t50Export = new QiyiTop50Exporter();
		t50Export.export(wb, qiyiMovie, "qiyiMoive");
		t50Spider.setTargetUrl("http://top.iqiyi.com/top50_2.html");
		List<? extends Item> qiyiTV = t50Spider.extract();
		t50Export.export(wb, qiyiTV, "qiyiTV");
		List<? extends Item> oldQiyiRank = oldQiyiRank1;
		export.export(wb, oldQiyiRank, "oldQiyis");
	}

	protected void baidu(Workbook wb) throws IOException {
		BaiduSpider spider = new BaiduSpider();
		spider.setTargetUrl(BaiduSpider.BUIDU_MOVIE);
		List<? extends Item> movieItems = spider.extract();
		BaiduExporter export = new BaiduExporter();
		export.export(wb, movieItems, "baiduMovie");
		spider.setTargetUrl(BaiduSpider.BUIDU_TV);
		List<? extends Item> tvItems = spider.extract();
		export.export(wb, tvItems, "baiduTV");
	}

	protected void letv(Workbook wb) throws IOException {
		LetvSpider ls = new LetvSpider();
		ls.movie();
		ls.start();
		List<? extends Item> movie_day = ls.extract();
		LetvExporter ex = new LetvExporter();
		ex.export(wb, movie_day, "letv-movie-day");
		List<? extends Item> movie_week = ls.extract();
		ex.export(wb, movie_week, "letv-movie-week");
		List<? extends Item> movie_month = ls.extract();
		ex.export(wb, movie_month, "letv-movie-month");
		ls.tv();
		ls.start();
		List<? extends Item> tv_day = ls.extract();
		ex.export(wb, tv_day, "letv-tv-day");
		List<? extends Item> tv_week = ls.extract();
		ex.export(wb, tv_week, "letv-tv-week");
		List<? extends Item> tv_month = ls.extract();
		ex.export(wb, tv_month, "letv-tv-month");
	}

	protected void sohu(Workbook wb) throws IOException {
		SohuSpider sohu = new SohuSpider();
		sohu.movie();
		sohu.start();
		List<? extends Item> movie_day = sohu.extract();
		SohuExporter ex = new SohuExporter();
		ex.export(wb, movie_day, "sohu-movie-day");
		List<? extends Item> movie_week = sohu.extract();
		ex.export(wb, movie_week, "sohu-movie-week");
		List<? extends Item> movie_month = sohu.extract();
		ex.export(wb, movie_month, "sohu-movie-month");
		sohu.tv();
		sohu.start();
		List<? extends Item> tv_day = sohu.extract();
		ex.export(wb, tv_day, "sohu-tv-day");
		List<? extends Item> tv_week = sohu.extract();
		ex.export(wb, tv_week, "sohu-tv-week");
		List<? extends Item> tv_month = sohu.extract();
		ex.export(wb, tv_month, "sohu-tv-month");
	}

	protected void youku(Workbook wb) throws IOException {
		YoukuSpider spider = new YoukuSpider();
		spider.setTargetUrl("http://index.youku.com/protop/2");
		List<? extends Item> movieItems = spider.extract();
		YoukuExporter export = new YoukuExporter();
		export.export(wb, movieItems, "youkuMovie");
		spider.setTargetUrl("http://index.youku.com/protop/0");
		List<? extends Item> tvItems = spider.extract();
		export.export(wb, tvItems, "youkuTV");
	}

	public static void main(String[] args) throws IOException {
		SpiderTaskImpl spiderTask = new SpiderTaskImpl();
		spiderTask.runAndSaveToWorkbook(args[0]);
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}

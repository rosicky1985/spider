 package com.nbb.spider.manager.task;
 
 import com.nbb.spider.dao.TaskLogDao;
 import com.nbb.spider.entity.TaskLog;
 import com.nbb.spider.manager.exporter.impl.BaiduExporter;
 import com.nbb.spider.manager.exporter.impl.QiyiExporter;
 import com.nbb.spider.manager.exporter.impl.QiyiTop50Exporter;
 import com.nbb.spider.manager.exporter.impl.YoukuExporter;
 import com.nbb.spider.manager.webspider.impl.BaiduSpider;
 import com.nbb.spider.manager.webspider.impl.QiyiSpider;
 import com.nbb.spider.manager.webspider.impl.QiyiTop50Spider;
 import com.nbb.spider.manager.webspider.impl.YoukuSpider;
 import java.io.File;
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
 
 @Service("spiderTask")
 public class SpiderTaskImpl
   implements SpiderTask
 {
   private static final Logger logger = LoggerFactory.getLogger(SpiderTask.class);
   private static final String KEY = "spided";
   private static final String EXTENSION = "xls";
   private String destination = "spidered/";
 
   @Autowired
   private TaskLogDao taskLogDao;
 
   public TaskLogDao getTaskLogDao() { return this.taskLogDao; }
 
   public void setTaskLogDao(TaskLogDao taskLogDao)
   {
     this.taskLogDao = taskLogDao;
   }
 
   @Transactional
   public void run()
     throws IOException
   {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     logger.info("spider task starting... @ " + sdf.format(new Date()));
     QiyiExporter export = new QiyiExporter();
     Workbook wb = export.createWorkBook();
     qiyi(wb);
     baidu(wb);
     youku(wb);
     String realPath = getDestinationFullPathWithEncoding();
     String webRootPath = getWebRootPath();
     String fullPath = webRootPath + "resources/" + realPath;
     export.saveWorkBook(wb, fullPath);
     TaskLog log = new TaskLog();
     log.setCreated(new Date());
     log.setDestination(realPath);
     this.taskLogDao.save(log);
     logger.info("spider task ended @ " + sdf.format(new Date()));
   }
 
   protected String getDestinationFullPathWithEncoding() {
     StringBuffer sb = new StringBuffer();
     Long current = Long.valueOf(System.currentTimeMillis());
     int code = (int)(current.longValue() % 10000L);
     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
     String yyyyMMdd = sdf.format(new Date());
 
     sb.append(this.destination).append("spided").append(".").append(yyyyMMdd).append(".").append(code)
       .append(".").append("xls");
     return sb.toString();
   }
 
   protected String getWebRootPath() {
     URL url = SpiderTaskImpl.class.getResource("SpiderTaskImpl.class");
     String temppath = url.toString();
     int startIndex = temppath.indexOf("file:");
     int index = temppath.indexOf("WEB-INF");
     String deployWarPath = temppath.substring(startIndex + "file:".length() + 1, index);
 
     if ((System.getProperty("os.name").indexOf("Linux") != -1) && 
       (!deployWarPath.startsWith(File.separator))) {
       deployWarPath = File.separator + deployWarPath;
     }
 
     return deployWarPath;
   }
 
   protected void qiyi(Workbook wb) throws IOException {
     QiyiExporter export = new QiyiExporter();
     QiyiSpider spider = new QiyiSpider();
     List oldQiyiRank1 = spider.extract();
     QiyiTop50Spider t50Spider = new QiyiTop50Spider();
     t50Spider.setTargetUrl("http://top.iqiyi.com/top50_1.html");
     List qiyiMovie = t50Spider.extract();
     QiyiTop50Exporter t50Export = new QiyiTop50Exporter();
     t50Export.export(wb, qiyiMovie, "qiyiMoive");
     t50Spider.setTargetUrl("http://top.iqiyi.com/top50_2.html");
     List qiyiTV = t50Spider.extract();
     t50Export.export(wb, qiyiTV, "qiyiTV");
     List oldQiyiRank = oldQiyiRank1;
     export.export(wb, oldQiyiRank, "oldQiyis");
   }
 
   protected void baidu(Workbook wb) throws IOException {
     BaiduSpider spider = new BaiduSpider();
     spider.setTargetUrl("http://top.baidu.com/buzz.php?p=movie");
     List movieItems = spider.extract();
     BaiduExporter export = new BaiduExporter();
     export.export(wb, movieItems, "baiduMovie");
     spider.setTargetUrl("http://top.baidu.com/buzz.php?p=tv");
     List tvItems = spider.extract();
     export.export(wb, tvItems, "baiduTV");
   }
 
   protected void youku(Workbook wb) throws IOException {
     YoukuSpider spider = new YoukuSpider();
     spider.setTargetUrl("http://index.youku.com/protop/2");
     List movieItems = spider.extract();
     YoukuExporter export = new YoukuExporter();
     export.export(wb, movieItems, "youkuMovie");
     spider.setTargetUrl("http://index.youku.com/protop/0");
     List tvItems = spider.extract();
     export.export(wb, tvItems, "youkuTV");
   }
 
   public static void main(String[] args) throws IOException {
     SpiderTask spiderTask = new SpiderTaskImpl();
     spiderTask.run();
   }
 
   public String getDestination() {
     return this.destination;
   }
 
   public void setDestination(String destination) {
     this.destination = destination;
   }
 }


 package com.nbb.spider.manager.webspider.impl;
 
 import com.nbb.spider.entity.AbstractItem;
 import com.nbb.spider.entity.Item;
 import com.nbb.spider.entity.impl.Baidu;
 import com.nbb.spider.manager.webspider.AbstractSpider;
 import com.nbb.spider.manager.webspider.Spider;
 import java.io.IOException;
 import java.util.List;
 import org.jsoup.nodes.Document;
 import org.jsoup.nodes.Element;
 import org.jsoup.select.Elements;
 
 public class BaiduSpider extends AbstractSpider
   implements Spider
 {
   public static final String BUIDU_MOVIE = "http://top.baidu.com/buzz.php?p=movie";
   public static final String BUIDU_TV = "http://top.baidu.com/buzz.php?p=tv";
   private String targetUrl;
 
   public List<? extends Item> extract()
   {
     try
     {
       Document doc = getRootDocument(this.targetUrl);
       Element tabb = doc.getElementsByClass("list").first();
       List<Item> allLine = loopAllLine(tabb, "tr", true);
       AbstractItem.sort(allLine);
       return allLine;
     } catch (IOException e) {
       e.printStackTrace();
     }return null;
   }
 
   protected Item parseOneline(Element ele)
   {
     Baidu item = new Baidu();
     item.setRank(ele.getElementsByTag("th").first().text());
     item.setTitle(ele.getElementsByClass("key").first().text());
     Elements tds = ele.getElementsByTag("td");
     item.setToday(tds.get(3).text());
     item.setLastWeek(tds.get(4).text());
     return item;
   }
 
   public void setTargetUrl(String targetUrl) {
     this.targetUrl = targetUrl;
   }
 }


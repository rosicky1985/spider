 package com.nbb.spider.manager.webspider.impl;
 
 import com.nbb.spider.entity.AbstractItem;
 import com.nbb.spider.entity.Item;
 import com.nbb.spider.entity.impl.QiyiTop50;
 import com.nbb.spider.manager.webspider.AbstractSpider;
 import com.nbb.spider.manager.webspider.Spider;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.List;
 import org.jsoup.nodes.Document;
 import org.jsoup.nodes.Element;
 import org.jsoup.select.Elements;
 
 public class QiyiTop50Spider extends AbstractSpider
   implements Spider
 {
   public static final String QIYIURL_MOVIE = "http://top.iqiyi.com/top50_1.html";
   public static final String QIYIURL_TV = "http://top.iqiyi.com/top50_2.html";
   private String targetUrl;
 
   public List<? extends Item> extract()
   {
     try
     {
       Document doc = getRootDocument(this.targetUrl);
       Element tabb = findTabByAttribute(doc);
       List<Item> allLine = loopAllLine(tabb, "li", false);
       AbstractItem.sort(allLine);
       return allLine;
     } catch (IOException e) {
       e.printStackTrace();
     }return null;
   }
 
   protected Item parseOneline(Element ele)
   {
     QiyiTop50 item = new QiyiTop50();
     item.setRank(ele.getElementsByTag("em").first().text());
     item.setTitle(ele.getElementsByTag("a").first().text());
     item.setActors(parseActors(ele));
     item.setHistory(ele.getElementsByClass("history").first().text());
     item.setLastWeek(ele.getElementsByClass("Last_week").first().text());
     item.setYesterDay(ele.getElementsByClass("Yesterday").first().text());
     return item;
   }
 
   protected List<String> parseActors(Element ele) {
     List<String> actors = new ArrayList<String>();
     Element actorSpan = ele.getElementsByClass("Actor").first();
     Elements actorAchor = actorSpan.getElementsByTag("a");
     Iterator<Element> itr = actorAchor.iterator();
     while (itr.hasNext()) {
       actors.add(((Element)itr.next()).text());
     }
     return actors;
   }
 
   protected Element findTabByAttribute(Element doc)
   {
     Element tab_top50 = doc.getElementById("tab_top50");
     Elements tabs = tab_top50.getElementsByTag("div");
     Iterator<Element> tabItr = tabs.iterator();
     while (tabItr.hasNext()) {
       Element tab = (Element)tabItr.next();
       if (tab.attr("j-tab-cnt").equals("t1")) {
         return tab;
       }
     }
     return null;
   }
 
   public void setTargetUrl(String targetUrl) {
     this.targetUrl = targetUrl;
   }
 }


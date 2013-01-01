 package com.nbb.spider.manager.webspider.impl;
 
 import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.nbb.spider.entity.AbstractItem;
import com.nbb.spider.entity.Item;
import com.nbb.spider.entity.impl.Youku;
import com.nbb.spider.manager.webspider.AbstractSpider;
import com.nbb.spider.manager.webspider.Spider;
 
 public class YoukuSpider extends AbstractSpider
   implements Spider
 {
   private String targetUrl;
   public static final String YOUKU_MOVIE = "http://index.youku.com/protop/2";
   public static final String YOUKU_TV = "http://index.youku.com/protop/0";
 
   public List<? extends Item> extract()
   {
     try
     {
       Document doc = getRootDocument(this.targetUrl);
       Element tabb = doc.getElementsByClass("rank").first()
         .getElementsByTag("tbody").first();
       List<Item> allLine = loopAllLine(tabb, "tr", false);
       AbstractItem.sort(allLine);
       return allLine;
     } catch (IOException e) {
       e.printStackTrace();
     }return null;
   }
 
   protected Item parseOneline(Element ele)
   {
     Youku item = new Youku();
     item.setRank(ele.getElementsByClass("order").first().text());
     item.setTitle(ele.getElementsByClass("key").first().text());
     item.setTrend(ele.getElementsByClass("status").first().text());
     List<String> actors = new ArrayList<String>();
     Iterator<Element> itr = ele.getElementsByClass("intro").first()
       .getElementsByTag("a").iterator();
     while (itr.hasNext()) {
       actors.add(((Element)itr.next()).text());
     }
     item.setActors(actors);
     return item;
   }
 
   public void setTargetUrl(String targetUrl) {
     this.targetUrl = targetUrl;
   }
 
   public static void main(String[] agrs) {
     YoukuSpider spider = new YoukuSpider();
     spider.setTargetUrl("http://index.youku.com/protop/2");
     List<? extends Item> items = spider.extract();
     System.out.println(items.size());
   }
 }


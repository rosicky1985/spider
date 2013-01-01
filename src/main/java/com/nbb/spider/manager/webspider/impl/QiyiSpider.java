 package com.nbb.spider.manager.webspider.impl;
 
 import com.nbb.spider.entity.AbstractItem;
 import com.nbb.spider.entity.Item;
 import com.nbb.spider.entity.impl.QiyiRank;
 import com.nbb.spider.manager.webspider.AbstractSpider;
 import com.nbb.spider.manager.webspider.Spider;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.List;
 import org.jsoup.nodes.Document;
 import org.jsoup.nodes.Element;
 import org.jsoup.select.Elements;
 
 public class QiyiSpider extends AbstractSpider
   implements Spider
 {
   static final String QIYIURL = "http://www.iqiyi.com/common/ranking_mv.html";
 
   protected Item parseOneline(Element tr)
   {
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
       Element keywordLink = (Element)linkItr.next();
       String keyword = keywordLink.text();
       keywords.add(keyword);
     }
     qiyirank.setKeyworks(keywords);
     String increaseStr = tr.getElementsByTag("td").get(3)
       .getElementsByClass("fRed").first().text();
     qiyirank.setLastDayIncreased(increaseStr);
     return qiyirank;
   }
 
   public List<? extends Item> extract()
   {
     try {
       Document doc = getRootDocument("http://www.iqiyi.com/common/ranking_mv.html");
       Element tabb = findTabByAttribute(doc, "pfb", "j-tab-cnt", "hot");
       List<Item> allLine = loopAllLine(tabb, "tr", false);
       AbstractItem.sort(allLine);
       return allLine;
     } catch (IOException e) {
       e.printStackTrace();
     }return null;
   }
 
   protected Element findTabByAttribute(Element doc, String TabClass, String tabAttributeKey, String tagAttributeValue)
   {
     Elements tabs = doc.getElementsByClass(TabClass);
     Iterator<Element> tabItr = tabs.iterator();
     while (tabItr.hasNext()) {
       Element tab = (Element)tabItr.next();
       if (tab.attr(tabAttributeKey).equals(tagAttributeValue)) {
         return tab;
       }
     }
     return null;
   }
 }


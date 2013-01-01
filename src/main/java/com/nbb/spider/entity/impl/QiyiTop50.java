 package com.nbb.spider.entity.impl;
 
 import com.nbb.spider.entity.AbstractItem;
 import com.nbb.spider.entity.Item;
 import java.util.List;
 
 public class QiyiTop50 extends AbstractItem
   implements Item
 {
   private List<String> actors;
   private Long yesterDay;
   private Long lastWeek;
   private Long history;
 
   public String getTitle()
   {
     return this.title;
   }
 
   public Integer getRank()
   {
     return this.rank;
   }
 
   public List<String> getActors() {
     return this.actors;
   }
 
   public void setActors(List<String> paramList) {
     this.actors = paramList;
   }
 
   public Long getYesterDay() {
     return this.yesterDay;
   }
 
   public void setYesterDay(Long paramLong) {
     this.yesterDay = paramLong;
   }
 
   public Long getLastWeek() {
     return this.lastWeek;
   }
 
   public void setLastWeek(Long paramLong) {
     this.lastWeek = paramLong;
   }
 
   public Long getHistory() {
     return this.history;
   }
 
   public void setHistory(Long paramLong) {
     this.history = paramLong;
   }
 
   public void setHistory(String paramString) {
     paramString = parseNumeric(paramString);
     this.history = Long.valueOf(Long.parseLong(paramString));
   }
 
   public void setLastWeek(String paramString) {
     paramString = parseNumeric(paramString);
     this.lastWeek = Long.valueOf(Long.parseLong(paramString));
   }
 
   public void setYesterDay(String paramString) {
     paramString = parseNumeric(paramString);
     this.yesterDay = Long.valueOf(0L);
     try {
       this.yesterDay = Long.valueOf(Long.parseLong(paramString));
     }
     catch (Exception localException) {
     }
   }
 
   public String getActorsString() {
     return makeString(this.actors, ",");
   }
 
   public int compareRankto(Item paramItem) {
     return 0;
   }
 }


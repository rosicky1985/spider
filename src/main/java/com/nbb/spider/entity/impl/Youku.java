 package com.nbb.spider.entity.impl;
 
 import com.nbb.spider.entity.AbstractItem;
 import com.nbb.spider.entity.Item;
 import java.util.List;
 
 public class Youku extends AbstractItem
   implements Item
 {
   private List<String> actors;
   private Long trend;
 
   public List<String> getActors()
   {
     return this.actors;
   }
 
   public void setActors(List<String> actors) {
     this.actors = actors;
   }
 
   public Long getTrend() {
     return this.trend;
   }
 
   public void setTrend(Long trend) {
     this.trend = trend;
   }
 
   public void setTrend(String text) {
     this.trend = Long.valueOf(Long.parseLong(parseNumeric(text)));
   }
 
   public String getActorsList() {
     return makeString(this.actors, ",");
   }
 }


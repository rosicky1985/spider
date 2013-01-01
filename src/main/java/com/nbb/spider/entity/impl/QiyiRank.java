 package com.nbb.spider.entity.impl;
 
 import com.nbb.spider.entity.AbstractItem;
 import com.nbb.spider.entity.Item;
 import java.util.List;
 
 public class QiyiRank extends AbstractItem
   implements Item
 {
   private List<String> keyworks;
   private Double lastDayIncreased;
 
   public List<String> getKeyworks()
   {
     return this.keyworks;
   }
 
   public void setKeyworks(List<String> keyworks) {
     this.keyworks = keyworks;
   }
 
   public Double getLastDayIncreased() {
     return this.lastDayIncreased;
   }
 
   public void setLastDayIncreased(String lastDayIncreased) {
     lastDayIncreased = parseNumeric(lastDayIncreased);
     this.lastDayIncreased = Double.valueOf(Double.parseDouble(lastDayIncreased));
   }
 
   public String getKeywords() {
     return makeString(this.keyworks, ",");
   }
 
   public int compareRankto(Item arg1) {
     return 0;
   }
 }


 package com.nbb.spider.entity;
 
 import java.util.Date;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.Id;
 import javax.persistence.Table;
 
 @Entity
 @Table(name="task")
 public class TaskLog
 {
   private Integer id;
   private Date created;
   private String destination;
 
   @Column(name="created")
   public Date getCreated()
   {
     return this.created;
   }
 
   public void setCreated(Date created) {
     this.created = created;
   }
 
   @Column(name="destination")
   public String getDestination() {
     return this.destination;
   }
 
   public void setDestination(String destination) {
     this.destination = destination;
   }
 
   @Id
   @GeneratedValue
   @Column(name="id")
   public Integer getId() {
     return this.id;
   }
 
   public void setId(Integer id) {
     this.id = id;
   }
 }


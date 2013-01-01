 package com.nbb.spider.dao;
 
 import org.hibernate.Session;
 import org.hibernate.SessionFactory;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.util.Assert;
 
 public class AbstractDao<T>
 {
   protected Logger logger = LoggerFactory.getLogger(super.getClass());
 
   @Autowired
   private SessionFactory sessionFactory;
 
   public SessionFactory getSessionFactory() { return this.sessionFactory; }
 
   public void setSessionFactory(SessionFactory sessionFactory)
   {
     this.sessionFactory = sessionFactory;
   }
 
   public Session getSession()
   {
     return this.sessionFactory.getCurrentSession();
   }
 
   public void save(T entity)
   {
     Assert.notNull(entity, "entity涓��涓虹┖");
     getSession().saveOrUpdate(entity);
     this.logger.debug("save entity: {}", entity);
   }
 
   public void delete(T entity)
   {
     Assert.notNull(entity, "entity涓��涓虹┖");
     getSession().delete(entity);
     this.logger.debug("delete entity: {}", entity);
   }
 }


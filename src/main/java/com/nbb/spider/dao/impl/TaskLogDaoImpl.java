 package com.nbb.spider.dao.impl;
 
 import com.nbb.spider.dao.AbstractDao;
 import com.nbb.spider.dao.TaskLogDao;
 import com.nbb.spider.entity.TaskLog;
 import java.util.List;
 import org.hibernate.Query;
 import org.hibernate.Session;
 import org.springframework.stereotype.Repository;
 import org.springframework.transaction.annotation.Transactional;
 
 @Repository
 public class TaskLogDaoImpl extends AbstractDao<TaskLog>
   implements TaskLogDao
 {
   @Transactional
   public TaskLog get(Integer id)
   {
     return get(id);
   }
 
   @Transactional
   public List<TaskLog> getAllTasks()
   {
     return getSession().createQuery("from TaskLog order by created desc").list();
   }
 }


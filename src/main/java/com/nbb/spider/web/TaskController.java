 package com.nbb.spider.web;
 
 import com.nbb.spider.entity.TaskLog;
import com.nbb.spider.manager.task.SpiderTask;
 import com.nbb.spider.manager.task.TaskLogService;
 import java.io.IOException;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
 @Controller
 public class TaskController
 {
 
   @Autowired
   private SpiderTask spiderTask;
 
   @Autowired
   private TaskLogService taskLogService;
 
   @RequestMapping({"/task/run.do"})
   public String run()
     throws IOException
   {
     this.spiderTask.run();
     return "redirect:/task/list.do";
   }
 
   @RequestMapping({"/task/list.do"})
   public ModelAndView list() {
     List<TaskLog> list = this.taskLogService.list();
     Map<String,List<TaskLog>> model = new HashMap<String,List<TaskLog>>();
     model.put("list", list);
     return new ModelAndView("task/list", model);
   }
 }


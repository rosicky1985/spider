 package com.nbb.spider;
 
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 
 @Controller
 public class HomeController
 {
   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
 
   @RequestMapping(value={"/"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String home(Model model)
   {
     logger.info("Welcome home!");
     model.addAttribute("controllerMessage", 
       "This is the message from the controller!");
     return "home";
   }
 }


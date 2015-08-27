package com.example.sdn4rc2.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.sdn4rc2.service.Neo4jDatabaseCleaner;

@Controller
public class IndexController {
  final static Logger logger = LoggerFactory.getLogger(IndexController.class);

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String topRedirect() {
    return "redirect:/customer/";
  }

  @Transactional
  @RequestMapping(value = "/_cleanup", method = RequestMethod.GET)
  public String cleanup() {
    new Neo4jDatabaseCleaner().cleanDb();
    return "redirect:/customer/";
  }

}

package com.example.sdn4rc2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.sdn4rc2.helper.MyDialect;

@SpringBootApplication
public class App {
  final static Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  //THYMELEAF Utility Object
  @Bean
  MyDialect myDialect(){
    return new MyDialect();
  }

}

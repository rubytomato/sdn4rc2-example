package com.example.sdn4rc2;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.event.AfterDeleteEvent;
import org.springframework.data.neo4j.event.AfterSaveEvent;
import org.springframework.data.neo4j.event.BeforeDeleteEvent;
import org.springframework.data.neo4j.event.BeforeSaveEvent;
import org.springframework.data.neo4j.event.Neo4jDataManipulationEvent;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.sdn4rc2.domain.Entity;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.example.sdn4rc2.repository", queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {
  final static Logger logger = LoggerFactory.getLogger(Neo4jConfig.class);

  private static final String NEO4J_HOST = "http://localhost:";
  private static final String NEO4J_PORT = "7474";

  @Autowired
  private Environment env;

  @Override
  @Bean
  public SessionFactory getSessionFactory() {
    String username = env.getProperty("neo4j.username");
    String password = env.getProperty("neo4j.password");
    System.setProperty("username", username);
    System.setProperty("password", password);
    SessionFactory sessionFactory = new SessionFactory("com.example.sdn4rc2.domain");
    return sessionFactory;
  }

  @Override
  @Bean
  public Neo4jServer neo4jServer() {
    Neo4jServer neo4jServer = new RemoteServer(NEO4J_HOST + NEO4J_PORT);
    return neo4jServer;
  }

  @Override
  @Bean
  @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
  public Session getSession() throws Exception {
    Session session = super.getSession();
    return session;
  }

  @Override
  @Bean
  public Neo4jOperations neo4jTemplate() throws Exception {
    return new Neo4jTemplate(getSession());
  }

  @PostConstruct
  public void initApplication() throws IOException {
    if (env.getActiveProfiles().length == 0) {
      logger.warn("No Spring profile configured, running with default configuration");
    } else {
      logger.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
    }
  }


  /*
   * Neo4j Template utilises Spring’s event mechanism through ApplicationListener and
   * ApplicationEvent to notify interested parties about certain data manipulations performed through it.
   * The following hooks are available in the form of types of application event:
   */

  @Bean
  ApplicationListener<BeforeSaveEvent> beforeSaveEventApplicationListener() {
    return new ApplicationListener<BeforeSaveEvent>() {
      @Override
      public void onApplicationEvent(BeforeSaveEvent event) {
        logger.info("BeforeSaveEvent:{}", event.toString());
        Entity entity = (Entity)event.getEntity();
        logger.info("NodeId:{}", entity.id);
      }
    };
  }

  @Bean
  ApplicationListener<AfterSaveEvent> afterSaveEventApplicationListener() {
    return new ApplicationListener<AfterSaveEvent>() {
      @Override
      public void onApplicationEvent(AfterSaveEvent event) {
        logger.info("AfterSaveEvent:{}", event.toString());
        Entity entity = (Entity)event.getEntity();
        logger.info("NodeId:{}", entity.id);
      }
    };
  }

  @Bean
  ApplicationListener<BeforeDeleteEvent> beforeDeleteEventApplicationListener() {
    return new ApplicationListener<BeforeDeleteEvent>() {
      @Override
      public void onApplicationEvent(BeforeDeleteEvent event) {
        logger.info("BeforeDeleteEvent:{}", event.toString());
      }
    };
  }

  @Bean
  ApplicationListener<AfterDeleteEvent> afterDeleteEventApplicationListener() {
    return new ApplicationListener<AfterDeleteEvent>() {
      @Override
      public void onApplicationEvent(AfterDeleteEvent event) {
        logger.info("AfterDeleteEvent:{}", event.toString());
      }
    };
  }

  @Bean
  ApplicationListener<Neo4jDataManipulationEvent> ManipulationEventApplicationListener() {
    return new ApplicationListener<Neo4jDataManipulationEvent>() {
      @Override
      public void onApplicationEvent(Neo4jDataManipulationEvent event) {
        logger.info("Neo4jDataManipulationEvent:{}", event.toString());
      }
    };
  }

}

package com.example.sdn4rc2.service;

import java.util.Collections;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sdn4rc2.domain.Category;
import com.example.sdn4rc2.domain.Customer;
import com.example.sdn4rc2.domain.Entity;
import com.example.sdn4rc2.domain.Order;
import com.example.sdn4rc2.domain.Product;
import com.example.sdn4rc2.domain.Supplier;

@Service
public class Neo4jDatabaseCleaner {
  final static Logger logger = LoggerFactory.getLogger(Neo4jDatabaseCleaner.class);

  @Autowired
  Session session;

  @Autowired
  Neo4jOperations neo4jOperations;

  private static final String ALL_DELETE = "MATCH (n) OPTIONAL MATCH (n)-[r]-(m) DELETE n,r,m";

  private static final String ALL_COUNT = "MATCH (n) RETURN COUNT(n)";

  @Transactional
  public void cleanDb() {

    Result deleteResult = session.query(ALL_DELETE, Collections.emptyMap());

    debugDump(deleteResult);

    Result countResult = session.query(ALL_COUNT, Collections.emptyMap(), true);

    debugDump(countResult);

  }

  @Transactional
  public void cleanDbByOp() {

    long ca = count(Category.class);
    long cu = count(Customer.class);
    long o = count(Order.class);
    long p = count(Product.class);
    long s = count(Supplier.class);

    logger.info("Category:{} Customer:{} Order:{} Product:{} Supplier:{}", ca, cu, o, p, s);

    Result deleteResult = neo4jOperations.query(ALL_DELETE, Collections.emptyMap());

    debugDump(deleteResult);

    Result countResult = neo4jOperations.query(ALL_COUNT, Collections.emptyMap(), true);

    debugDump(countResult);

  }

  private long count(Class<? extends Entity> clazz) {
    return neo4jOperations.count(clazz);
  }

  private void debugDump(Result result) {

    if (result != null) {
      logger.info("result not null");
    } else {
      logger.info("result null");
    }

    result.forEach(v -> {
      v.entrySet().forEach(x -> logger.info("Key:{}, Value:{}", x.getKey(), x.getValue().toString()));
    });

  }

}

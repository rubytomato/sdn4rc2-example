package com.example.sdn4rc2.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Service;

import com.example.sdn4rc2.domain.Product;

@Service
public class RecommendService {
  final static Logger logger = LoggerFactory.getLogger(RecommendService.class);

  @Autowired
  Neo4jOperations neo4jOperations;

  final static String RECOMMEND =
  "MATCH (uc:Customer)-[:PURCHASED]->(uo:Order)-[:ORDERS]->(up:Product) " +
  "WHERE uc.customerID = {customerID} AND uo.orderID = {orderID} " +
  "WITH uc, up " +
  //同じ商品を注文したことがある他のカスタマーが注文した商品の一覧
  "MATCH (up)<-[:ORDERS]-(:Order)<-[:PURCHASED]-(ac:Customer)-[:PURCHASED]->(aco:Order)-[:ORDERS]->(acp:Product) " +
  //その商品から注文したことのある商品は除外
  "WHERE NOT (uc)-[:PURCHASED]->(:Order)-[:ORDERS]->(acp) " +
  "RETURN DISTINCT acp AS product, COUNT(*) AS cnt ORDER BY cnt LIMIT 5";

  public Iterable<Product> recommendProducts(final String customerID, final Integer orderID) {
    Map<String, Object> params = new HashMap<>();
    params.put("customerID", customerID);
    params.put("orderID", orderID);
    Iterable<Product> result = neo4jOperations.queryForObjects(Product.class, RECOMMEND, params);
    return result;
  }

}

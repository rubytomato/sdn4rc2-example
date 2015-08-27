package com.example.sdn4rc2.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.example.sdn4rc2.domain.Order;

@Repository
public interface OrderRepository extends GraphRepository<Order> {

  @Query("MATCH (o:Order) WHERE o.shipName =~ {0} RETURN COUNT(o)")
  Long countByShipNameLike(String name);

  @Query("MATCH (o:Order) WHERE o.shipName =~ {0} RETURN o SKIP {1} LIMIT {2}")
  Iterable<Order> findByShipNameLike(String name, int skip, int size);

  @Query("MATCH (o:Order) RETURN MAX(o.orderID)")
  Integer maxOrderID();

}
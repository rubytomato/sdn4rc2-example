package com.example.sdn4rc2.repository;

import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.example.sdn4rc2.domain.Customer;

@Repository
public interface CustomerRepository extends GraphRepository<Customer> {

  @Query("MATCH (c:Customer) WHERE c.customerID = {0} RETURN c LIMIT 1")
  Customer findByCustomerID(String customerID);

  @Query("MATCH (c:Customer) RETURN c.customerID AS customerID, c.contactName AS contactName ORDER BY c.contactName ASC")
  Iterable<Map<String, Object>> customerIDs();

}

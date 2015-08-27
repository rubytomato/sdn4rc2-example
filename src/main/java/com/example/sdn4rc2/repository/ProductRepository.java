package com.example.sdn4rc2.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.example.sdn4rc2.domain.Product;

@Repository
public interface ProductRepository extends GraphRepository<Product> {

  @Query("MATCH (p:Product) WHERE p.productName =~ {0} RETURN COUNT(p)")
  Long countByNameLike(String name);

  @Query("MATCH (p:Product) WHERE p.productName =~ {0} RETURN p")
  Iterable<Product> findByNameLike(String name);

  @Query("MATCH (p:Product) RETURN MAX(p.productID)")
  Integer maxProductID();

}

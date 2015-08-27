package com.example.sdn4rc2.repository;

import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.example.sdn4rc2.domain.Category;
import com.example.sdn4rc2.domain.CategoryQueryResult;

@Repository
public interface CategoryRepository extends GraphRepository<Category> {

  @Query("MATCH (c:Category) WHERE c.categoryID = {0} RETURN c LIMIT 1")
  Category findByCategoryID(Integer categoryID);

  @Query("MATCH (c:Category) RETURN c.categoryID AS categoryID, c.categoryName AS categoryName ORDER BY c.categoryName ASC")
  Iterable<Map<String, Object>> categoryIDs();

  @Query("MATCH (c:Category) RETURN MAX(c.categoryID)")
  Integer maxCategoryID();

  @Query("MATCH (c:Category)<-[:PART_OF]-(p:Product) WHERE c.categoryID = {0} RETURN c.categoryID AS categoryID, c.categoryName AS categoryName, c.description AS description, c.picture AS picture, COUNT(p) AS count")
  CategoryQueryResult getCategoryWithProductNum(Integer categoryID);

}


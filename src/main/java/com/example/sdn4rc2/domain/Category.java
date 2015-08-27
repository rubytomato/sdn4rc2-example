package com.example.sdn4rc2.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Category")
public class Category extends Entity {

  @Property(name = "categoryID")
  public Integer categoryID;
  @Property(name = "categoryName")
  public String categoryName;
  @Property(name = "description")
  public String description;
  @Property(name = "picture")
  public String picture;

  /**
   * (Category)<-[PART_OF]-(Product)
   */
  @Relationship(type = "PART_OF", direction = Relationship.INCOMING)
  public Set<Product> products = new HashSet<>();

  public Category() {
  }

  public Category(Long id, Integer categoryID, String categoryName, String description,
      String picture) {
    this.id = id;
    this.categoryID = categoryID;
    this.categoryName = categoryName;
    this.description = description;
    this.picture = picture;
  }

}


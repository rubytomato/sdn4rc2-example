package com.example.sdn4rc2.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Product")
public class Product extends Entity {

  @Property(name = "productID")
  public Integer productID;

  public String productName;
  public String quantityPerUnit;
  public Double unitPrice;
  public Integer unitsInStock;
  public Integer unitsOnOrder;
  public Integer reorderLevel;
  public Boolean discontinued;
  public Integer supplierID;
  public Integer categoryID;

  /**
   * (Product)<-[ORDERS]-(Order)
   */
  @Relationship(type = "ORDERS", direction = Relationship.INCOMING)
  public Set<Order> orders = new HashSet<>();

  /**
   * (Product)<-[SUPPLIES]-(Supplier)
   */
  @Relationship(type = "SUPPLIES", direction = Relationship.INCOMING)
  public Supplier supplier;

  /**
   * (Product)-[PART_OF]->(Category)
   */
  @Relationship(type = "PART_OF", direction = Relationship.OUTGOING)
  public Category category;

  public Product() {
  }

  public Product(Long id, Integer productID, String productName, Integer supplierID,
      Integer categoryID, String quantityPerUnit, Double unitPrice, Integer unitsInStock,
      Integer unitsOnOrder, Integer reorderLevel, Boolean discontinued) {
    this.id = id;
    this.productID = productID;
    this.productName = productName;
    this.supplierID = supplierID;
    this.categoryID = categoryID;
    this.quantityPerUnit = quantityPerUnit;
    this.unitPrice = unitPrice;
    this.unitsInStock = unitsInStock;
    this.unitsOnOrder = unitsOnOrder;
    this.reorderLevel = reorderLevel;
    this.discontinued = discontinued;
  }

}
package com.example.sdn4rc2.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

@NodeEntity(label = "Order")
public class Order extends Entity {

  @Property(name = "orderID")
  public Integer orderID;

  @DateString(value = "yyyy-MM-dd HH:mm:ss.SSS")
  public Date orderDate;
  public String shipName;
  public String shipCountry;
  public String shipRegion;
  public String shipCity;
  public String shipAddress;
  public String shipPostalCode;
  @DateString(value = "yyyy-MM-dd HH:mm:ss.SSS")
  public Date shippedDate;
  @DateString(value = "yyyy-MM-dd HH:mm:ss.SSS")
  public Date requiredDate;
  public String shipVia;
  public String freight;
  public String customerID;
  public Integer employeeID;

  /**
   * (Order)-[ORDERS]->(Product)
   */
  @Relationship(type = "ORDERS", direction = Relationship.OUTGOING)
  public Set<Product> products = new HashSet<>();

  /**
   * (Order)<-[PURCHASED]-(Customer)
   */
  @Relationship(type = "PURCHASED", direction = Relationship.INCOMING)
  public Customer customer;

  public Order() {
  }

  public Order(Long id, Integer orderID, Date orderDate, String shipAddress,
      String shipRegion, String freight, String shipCity, String shipCountry,
      String shipName, Date shippedDate, Date requiredDate, String shipPostalCode,
      String shipVia, String customerID, Integer employeeID) {
    this.id = id;
    this.orderID = orderID;
    this.orderDate = orderDate;
    this.shipAddress = shipAddress;
    this.shipRegion = shipRegion;
    this.freight = freight;
    this.shipCity = shipCity;
    this.shipCountry = shipCountry;
    this.shipName = shipName;
    this.shippedDate = shippedDate;
    this.requiredDate = requiredDate;
    this.shipPostalCode = shipPostalCode;
    this.shipVia = shipVia;
    this.customerID = customerID;
    this.employeeID = employeeID;
  }

}

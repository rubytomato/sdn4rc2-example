package com.example.sdn4rc2.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Customer")
public class Customer extends Entity {

  @Property(name = "customerID")
  public String customerID;

  public String contactName;
  public String contactTitle;
  public String country;
  public String region;
  public String city;
  public String address;
  public String postalCode;
  public String phone;
  public String fax;

  /**
   * (Customer)-[PURCHASED]->(Order)
   */
  @Relationship(type = "PURCHASED", direction = Relationship.OUTGOING)
  public Set<Order> orders = new HashSet<>();

  public Customer() {
  }

  public Customer(Long id, String customerID, String contactTitle, String contactName,
      String address, String city, String postalCode, String country, String region,
      String phone, String fax) {
    this.id = id;
    this.customerID = customerID;
    this.contactTitle = contactTitle;
    this.contactName = contactName;
    this.address = address;
    this.city = city;
    this.postalCode = postalCode;
    this.country = country;
    this.region = region;
    this.phone = phone;
    this.fax = fax;
  }

}
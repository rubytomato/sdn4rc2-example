package com.example.sdn4rc2.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Supplier")
public class Supplier extends Entity {

  @Property(name = "supplierID")
  public Integer supplierID;

  public String companyName;
  public String contactName;
  public String contactTitle;
  public String homePage;
  public String country;
  public String region;
  public String city;
  public String address;
  public String postalCode;
  public String phone;
  public String fax;

  /**
   * (Supplier)-[SUPPLIES]->(Product)
   */
  @Relationship(type = "SUPPLIES", direction = Relationship.OUTGOING)
  public Set<Product> products = new HashSet<>();

  public Supplier() {
  }

  public Supplier(Long id, Integer supplierID, String contactTitle, String contactName,
      String homePage, String city, String postalCode, String country, String phone,
      String fax, String companyName, String region, String address) {
    this.id = id;
    this.supplierID = supplierID;
    this.contactTitle = contactTitle;
    this.contactName = contactName;
    this.homePage = homePage;
    this.city = city;
    this.postalCode = postalCode;
    this.country = country;
    this.phone = phone;
    this.fax = fax;
    this.companyName = companyName;
    this.region = region;
    this.address = address;
  }

}

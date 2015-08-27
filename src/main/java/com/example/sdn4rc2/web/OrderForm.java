package com.example.sdn4rc2.web;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

public class OrderForm implements Serializable {

  private static final long serialVersionUID = -1268572984219044466L;

  //NodeID
  private String id;

  //EntityID
  @DecimalMin("0")
  @DecimalMax("999999")
  private String orderID;

  @NotNull
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private Date orderDate;
  @NotNull
  @Size(min=1, max=256)
  private String shipName;
  @NotNull
  private String shipCountry;
  @NotNull
  private String shipRegion;
  @NotNull
  private String shipCity;
  @NotNull
  private String shipAddress;
  private String shipPostalCode;
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private Date shippedDate;
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private Date requiredDate;
  @NotNull
  @Min(1)
  @Max(3)
  private String shipVia;
  @NotNull
  private String freight;
  @NotNull
  @Size(min=5, max=5)
  private String customerID;
  private String employeeID;

  private Map<Integer, String> products = new HashMap<Integer, String>();

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getOrderID() {
    return orderID;
  }
  public void setOrderID(String orderID) {
    this.orderID = orderID;
  }
  public Date getOrderDate() {
    return orderDate;
  }
  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }
  public String getShipName() {
    return shipName;
  }
  public void setShipName(String shipName) {
    this.shipName = shipName;
  }
  public String getShipCountry() {
    return shipCountry;
  }
  public void setShipCountry(String shipCountry) {
    this.shipCountry = shipCountry;
  }
  public String getShipRegion() {
    return shipRegion;
  }
  public void setShipRegion(String shipRegion) {
    this.shipRegion = shipRegion;
  }
  public String getShipCity() {
    return shipCity;
  }
  public void setShipCity(String shipCity) {
    this.shipCity = shipCity;
  }
  public String getShipAddress() {
    return shipAddress;
  }
  public void setShipAddress(String shipAddress) {
    this.shipAddress = shipAddress;
  }
  public String getShipPostalCode() {
    return shipPostalCode;
  }
  public void setShipPostalCode(String shipPostalCode) {
    this.shipPostalCode = shipPostalCode;
  }
  public Date getShippedDate() {
    return shippedDate;
  }
  public void setShippedDate(Date shippedDate) {
    this.shippedDate = shippedDate;
  }
  public Date getRequiredDate() {
    return requiredDate;
  }
  public void setRequiredDate(Date requiredDate) {
    this.requiredDate = requiredDate;
  }
  public String getShipVia() {
    return shipVia;
  }
  public void setShipVia(String shipVia) {
    this.shipVia = shipVia;
  }
  public String getFreight() {
    return freight;
  }
  public void setFreight(String freight) {
    this.freight = freight;
  }
  public String getCustomerID() {
    return customerID;
  }
  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }
  public String getEmployeeID() {
    return employeeID;
  }
  public void setEmployeeID(String employeeID) {
    this.employeeID = employeeID;
  }

  public Map<Integer, String> getProducts() {
    return products;
  }
  public void setProducts(Map<Integer, String> products) {
    this.products = products;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
  }

}
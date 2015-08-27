package com.example.sdn4rc2.web;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProductForm implements Serializable {

  private static final long serialVersionUID = 6731626047624461251L;

  //NodeID
  private String id;

  //EntityID
  @DecimalMin("0")
  @DecimalMax("999999")
  private String productID;

  @NotNull
  @Size(min = 1, max = 256)
  private String productName;
  @NotNull
  @Size(min = 1, max = 256)
  private String quantityPerUnit;
  @NotNull
  @Digits(integer = 4, fraction = 2)
  private String unitPrice;
  @NotNull
  @DecimalMin("0")
  @DecimalMax("999999")
  private String unitsInStock;
  @NotNull
  @DecimalMin("0")
  @DecimalMax("999999")
  private String unitsOnOrder;
  @NotNull
  @DecimalMin("0")
  @DecimalMax("999999")
  private String reorderLevel;
  @NotNull
  @Pattern(regexp = "true|false")
  private String discontinued;
  @NotNull
  private String supplierID;
  @NotNull
  private String categoryID;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getProductID() {
    return productID;
  }
  public void setProductID(String productID) {
    this.productID = productID;
  }
  public String getProductName() {
    return productName;
  }
  public void setProductName(String productName) {
    this.productName = productName;
  }
  public String getQuantityPerUnit() {
    return quantityPerUnit;
  }
  public void setQuantityPerUnit(String quantityPerUnit) {
    this.quantityPerUnit = quantityPerUnit;
  }
  public String getUnitPrice() {
    return unitPrice;
  }
  public void setUnitPrice(String unitPrice) {
    this.unitPrice = unitPrice;
  }
  public String getUnitsInStock() {
    return unitsInStock;
  }
  public void setUnitsInStock(String unitsInStock) {
    this.unitsInStock = unitsInStock;
  }
  public String getUnitsOnOrder() {
    return unitsOnOrder;
  }
  public void setUnitsOnOrder(String unitsOnOrder) {
    this.unitsOnOrder = unitsOnOrder;
  }
  public String getReorderLevel() {
    return reorderLevel;
  }
  public void setReorderLevel(String reorderLevel) {
    this.reorderLevel = reorderLevel;
  }
  public String getDiscontinued() {
    return discontinued;
  }
  public void setDiscontinued(String discontinued) {
    this.discontinued = discontinued;
  }
  public String getSupplierID() {
    return supplierID;
  }
  public void setSupplierID(String supplierID) {
    this.supplierID = supplierID;
  }
  public String getCategoryID() {
    return categoryID;
  }
  public void setCategoryID(String categoryID) {
    this.categoryID = categoryID;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
  }

}
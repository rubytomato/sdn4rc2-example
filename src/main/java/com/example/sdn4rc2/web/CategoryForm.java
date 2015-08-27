package com.example.sdn4rc2.web;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CategoryForm implements Serializable {

  private static final long serialVersionUID = 9168336542950240873L;

  //NodeID
  private String id;

  //EntityID
  @DecimalMin("0")
  @DecimalMax("999999")
  private String categoryID;

  @NotNull
  @Size(min=1, max=256)
  private String categoryName;
  private String description;
  private String picture;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getCategoryID() {
    return categoryID;
  }
  public void setCategoryID(String categoryID) {
    this.categoryID = categoryID;
  }
  public String getCategoryName() {
    return categoryName;
  }
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getPicture() {
    return picture;
  }
  public void setPicture(String picture) {
    this.picture = picture;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
  }

}
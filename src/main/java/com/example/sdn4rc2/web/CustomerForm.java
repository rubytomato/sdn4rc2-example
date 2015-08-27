package com.example.sdn4rc2.web;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CustomerForm implements Serializable {

  private static final long serialVersionUID = -8517696470399982702L;

  //NodeID
  private String id;

  @NotNull
  private String customerID;

  @NotNull
  @Size(min=1, max=256)
  private String contactName;
  @Size(min=1, max=256)
  private String contactTitle;
  @NotNull
  private String country;
  @NotNull
  private String region;
  @NotNull
  private String city;
  @NotNull
  private String address;
  private String postalCode;
  @NotNull
  private String phone;
  private String fax;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getCustomerID() {
    return customerID;
  }
  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }
  public String getContactName() {
    return contactName;
  }
  public void setContactName(String contactName) {
    this.contactName = contactName;
  }
  public String getContactTitle() {
    return contactTitle;
  }
  public void setContactTitle(String contactTitle) {
    this.contactTitle = contactTitle;
  }
  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getPostalCode() {
    return postalCode;
  }
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getFax() {
    return fax;
  }
  public void setFax(String fax) {
    this.fax = fax;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
  }

}

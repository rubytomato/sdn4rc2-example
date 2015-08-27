package com.example.sdn4rc2.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import com.example.sdn4rc2.domain.Customer;
import com.example.sdn4rc2.repository.CustomerRepository;
import com.example.sdn4rc2.web.CustomerForm;

@Service
public class CustomerService extends GenericCRUDService<Customer, CustomerForm> {
  final static Logger logger = LoggerFactory.getLogger(CustomerService.class);

  @Autowired
  CustomerRepository customerRepository;

  @Override
  public GraphRepository<Customer> getRepository() {
    return customerRepository;
  }

  @Override
  public Iterable<Map<String, Object>> entityIDs() {
    return customerRepository.customerIDs();
  }

  @Override
  public void convertToForm(Customer customer, CustomerForm form) {
    form.setId(customer.id.toString());
    form.setCustomerID(customer.customerID);
    form.setContactName(customer.contactName);
    form.setContactTitle(customer.contactTitle);
    form.setCountry(customer.country);
    form.setRegion(customer.region);
    form.setCity(customer.city);
    form.setAddress(customer.address);
    form.setPostalCode(customer.postalCode);
    form.setPhone(customer.phone);
    form.setFax(customer.fax);
  }

  @Override
  public Customer convertToEntity(CustomerForm form) {
    Customer customer = new Customer();
    if (StringUtils.isNotEmpty(form.getId())) {
      customer.id = Long.valueOf(form.getId());
    } else {
      customer.id = null; //new node
    }
    customer.customerID = form.getCustomerID();
    customer.contactName = form.getContactName();
    customer.contactTitle = ServiceUtils.nvl(form.getContactTitle());
    customer.country = form.getCountry();
    customer.region = form.getRegion();
    customer.city = form.getCity();
    customer.address = form.getAddress();
    customer.postalCode = ServiceUtils.nvl(form.getPostalCode());
    customer.phone = form.getPhone();
    customer.fax = ServiceUtils.nvl(form.getFax());
    return customer;
  }

  @Override
  public Integer maxEntityID() {
    return null;
  }

}
package com.example.sdn4rc2.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import com.example.sdn4rc2.domain.Order;
import com.example.sdn4rc2.domain.Product;
import com.example.sdn4rc2.repository.CustomerRepository;
import com.example.sdn4rc2.repository.OrderRepository;
import com.example.sdn4rc2.repository.ProductRepository;
import com.example.sdn4rc2.web.OrderForm;

@Service
public class OrderService extends GenericCRUDService<Order, OrderForm> {
  final static Logger logger = LoggerFactory.getLogger(OrderService.class);

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  ProductRepository productRepository;

  @Override
  public GraphRepository<Order> getRepository() {
    return orderRepository;
  }

  @Override
  public Iterable<Map<String, Object>> entityIDs() {
    return null;
  }

  @Override
  public void convertToForm(Order order, OrderForm form) {
    form.setId(order.id.toString());
    form.setOrderID(order.orderID.toString());
    form.setOrderDate(order.orderDate);
    form.setShipName(order.shipName);
    form.setShipCountry(order.shipCountry);
    form.setShipRegion(order.shipRegion);
    form.setShipCity(order.shipCity);
    form.setShipAddress(order.shipAddress);
    form.setShipPostalCode(order.shipPostalCode);
    form.setShippedDate(order.shippedDate);
    form.setRequiredDate(order.requiredDate);
    form.setShipVia(order.shipVia);
    form.setFreight(order.freight);
    form.setEmployeeID(ServiceUtils.nvl(order.employeeID));
    form.setCustomerID(order.customerID);

    if (order.products != null) {
      order.products.stream().forEach(p ->{
        form.getProducts().put(p.productID, p.productName);
      });
    }

  }

  @Override
  public Order convertToEntity(OrderForm form) {
    Order order = new Order();
    if (StringUtils.isNotEmpty(form.getId())) {
      order.id = Long.valueOf(form.getId());
    } else {
      order.id = null; //new node
    }
    if (StringUtils.isNotEmpty(form.getOrderID())) {
      order.orderID = Integer.valueOf(form.getOrderID());
    } else {
      order.orderID = maxEntityID() + 1; //new node
    }
    order.orderDate = form.getOrderDate();
    order.shipName = form.getShipName();
    order.shipCountry = form.getShipCountry();
    order.shipRegion = form.getShipRegion();
    order.shipCity = form.getShipCity();
    order.shipAddress = form.getShipAddress();
    order.shipPostalCode = ServiceUtils.nvl(form.getShipPostalCode());
    order.shippedDate = form.getShippedDate();
    order.requiredDate = form.getRequiredDate();
    order.shipVia = form.getShipVia();
    order.freight = form.getFreight();
    order.employeeID = ServiceUtils.nvlToInt(form.getEmployeeID());

    //retrieve customer
    if (StringUtils.isNotEmpty(form.getCustomerID())) {
      order.customerID = form.getCustomerID();
      order.customer = customerRepository.findByCustomerID(order.customerID);
    } else {
      throw new RuntimeException("Customer not found");
    }

    //retrieve products
    if (form.getProducts() != null) {
      Set<Product> p = new HashSet<>();
      form.getProducts().forEach((key,value)->{
        p.add(productRepository.findOne(Long.valueOf(key), 0));
      });
      order.products = p;
    } else {
      order.products = null;
    }

    return order;
  }

  public long countByShipNameLike(String name) {
    return orderRepository.countByShipNameLike(ServiceUtils.nameLike(name));
  }

  public Iterable<Order> findByShipNameLike(String name, int page, int size) {
    int skip = (page - 1) * size;
    return orderRepository.findByShipNameLike(ServiceUtils.nameLike(name), skip, size);
  }

  @Override
  public Integer maxEntityID() {
    return orderRepository.maxOrderID();
  }

}
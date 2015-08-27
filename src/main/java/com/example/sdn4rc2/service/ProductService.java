package com.example.sdn4rc2.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import com.example.sdn4rc2.domain.Product;
import com.example.sdn4rc2.repository.CategoryRepository;
import com.example.sdn4rc2.repository.ProductRepository;
import com.example.sdn4rc2.repository.SupplierRepository;
import com.example.sdn4rc2.web.ProductForm;

@Service
public class ProductService extends GenericCRUDService<Product, ProductForm> {
  final static Logger logger = LoggerFactory.getLogger(ProductService.class);

  @Autowired
  ProductRepository productRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  SupplierRepository supplierRepository;

  @Override
  public GraphRepository<Product> getRepository() {
    return productRepository;
  }

  @Override
  public Iterable<Map<String, Object>> entityIDs() {
    return null;
  }

  @Override
  public void convertToForm(Product product, ProductForm form) {
    form.setId(product.id.toString());
    form.setProductID(product.productID.toString());
    form.setProductName(product.productName);
    form.setQuantityPerUnit(product.quantityPerUnit);
    form.setUnitPrice(product.unitPrice.toString());
    form.setUnitsInStock(product.unitsInStock.toString());
    form.setUnitsOnOrder(product.unitsOnOrder.toString());
    form.setReorderLevel(product.reorderLevel.toString());
    form.setDiscontinued(product.discontinued.toString());
    form.setSupplierID(product.supplierID.toString());
    form.setCategoryID(product.categoryID.toString());
  }

  @Override
  public Product convertToEntity(ProductForm form) {
    Product product = new Product();
    if (StringUtils.isNotEmpty(form.getId())) {
      product.id = Long.valueOf(form.getId());
    } else {
      product.id = null; //new node
    }
    if (StringUtils.isNotEmpty(form.getProductID())) {
      product.productID = Integer.valueOf(form.getProductID());
    } else {
      product.productID = maxEntityID() + 1; //new node
    }
    product.productName = form.getProductName();
    product.quantityPerUnit = form.getQuantityPerUnit();
    product.unitPrice = Double.valueOf(form.getUnitPrice());
    product.unitsInStock = Integer.valueOf(form.getUnitsInStock());
    product.unitsOnOrder = Integer.valueOf(form.getUnitsOnOrder());
    product.reorderLevel = Integer.valueOf(form.getReorderLevel());
    product.discontinued = Boolean.valueOf(form.getDiscontinued());

    //retrieve supplier
    if (StringUtils.isNotEmpty(form.getSupplierID())) {
      product.supplierID = Integer.valueOf(form.getSupplierID());
      product.supplier = supplierRepository.findBySupplierID(product.supplierID);
    } else {
      throw new RuntimeException("supplier not found");
    }

    //retrieve category
    if (StringUtils.isNotEmpty(form.getCategoryID())) {
      product.categoryID = Integer.valueOf(form.getCategoryID());
      product.category = categoryRepository.findByCategoryID(product.categoryID);
    } else {
      throw new RuntimeException("category not found");
    }

    return product;
  }

  public long countByNameLike(String name) {
    return productRepository.countByNameLike(ServiceUtils.nameLike(name));
  }

  public Iterable<Product> findByNameLike(String name, int page, int size) {
    return productRepository.findByNameLike(ServiceUtils.nameLike(name));
  }

  @Override
  public Integer maxEntityID() {
    return productRepository.maxProductID();
  }

}
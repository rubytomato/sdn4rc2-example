package com.example.sdn4rc2.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import com.example.sdn4rc2.domain.Category;
import com.example.sdn4rc2.domain.CategoryQueryResult;
import com.example.sdn4rc2.repository.CategoryRepository;
import com.example.sdn4rc2.web.CategoryForm;

@Service
public class CategoryService extends GenericCRUDService<Category, CategoryForm> {
  final static Logger logger = LoggerFactory.getLogger(CategoryService.class);

  @Autowired
  CategoryRepository categoryRepository;

  @Override
  public GraphRepository<Category> getRepository() {
    return categoryRepository;
  }

  @Override
  public Iterable<Map<String, Object>> entityIDs() {
    return categoryRepository.categoryIDs();
  }

  @Override
  public void convertToForm(Category category, CategoryForm form) {
    form.setId(category.id.toString());
    form.setCategoryID(category.categoryID.toString());
    form.setCategoryName(category.categoryName);
    form.setDescription(category.description);
    form.setPicture(category.picture);
  }

  @Override
  public Category convertToEntity(CategoryForm form) {
    Category category = new Category();
    if (StringUtils.isNotEmpty(form.getId())) {
      category.id = Long.valueOf(form.getId());
    } else {
      category.id = null; //new node
    }
    if (StringUtils.isNotEmpty(form.getCategoryID())) {
      category.categoryID = Integer.valueOf(form.getCategoryID());
    } else {
      category.categoryID = maxEntityID() + 1; //new node
    }
    category.categoryName = form.getCategoryName();
    category.description = ServiceUtils.nvl(form.getDescription());
    category.picture = ServiceUtils.nvl(form.getPicture());
    return category;
  }

  @Override
  public Integer maxEntityID() {
    return categoryRepository.maxCategoryID();
  }

  public CategoryQueryResult getCategoryWithProductNum(final Integer categoryID) {
    return categoryRepository.getCategoryWithProductNum(categoryID);
  }

}
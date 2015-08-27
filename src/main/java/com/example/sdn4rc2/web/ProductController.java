package com.example.sdn4rc2.web;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sdn4rc2.domain.Product;
import com.example.sdn4rc2.service.CategoryService;
import com.example.sdn4rc2.service.ProductService;
import com.example.sdn4rc2.service.SupplierService;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController {
  final static Logger logger = LoggerFactory.getLogger(ProductController.class);

  final static int PAGE_SIZE = 20;

  @Autowired
  ProductService productService;

  @Autowired
  CategoryService categoryService;

  @Autowired
  SupplierService supplierService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model model) {
    return index(1, null, model);
  }

  @RequestMapping(value = "/{pageNo}", method = RequestMethod.GET)
  public String index(
    @PathVariable Integer pageNo,
    @RequestParam(required = true) String searchName,
    Model model) {
    int totalCount = 0;
    Iterable<Product> result;
    if (StringUtils.isNotEmpty(searchName)) {
      result = productService.findByNameLike(searchName, pageNo, PAGE_SIZE);
      totalCount = (int)productService.countByNameLike(searchName);
    } else {
      result = productService.findAll(pageNo, PAGE_SIZE, "productID");
      totalCount = (int)productService.count();
    }
    model.addAttribute("result", result);
    addPageAttr(productService.calcPage(totalCount, pageNo, PAGE_SIZE), model);
    model.addAttribute("searchName", searchName);
    return "Product/index";
  }

  @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
  public String detail(
    @PathVariable Long id,
    Model model) {
    Product product = productService.findOne(id);
    model.addAttribute("product", product);
    return "Product/detail";
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String create(
    ProductForm form,
    Model model) {
    Iterable<Map<String, Object>> categoryIDs = categoryService.entityIDs();
    addListIDsAttr(categoryIDs, "selectCategoryIDs", "categoryName" ,"categoryID", model);
    Iterable<Map<String, Object>> supplierIDs = supplierService.entityIDs();
    addListIDsAttr(supplierIDs, "selectSupplierIDs", "companyName" ,"supplierID", model);
    return "Product/create";
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public String edit(
    @PathVariable Long id,
    ProductForm form,
    Model model) {
    Iterable<Map<String, Object>> categoryIDs = categoryService.entityIDs();
    addListIDsAttr(categoryIDs, "selectCategoryIDs", "categoryName" ,"categoryID", model);
    Iterable<Map<String, Object>> supplierIDs = supplierService.entityIDs();
    addListIDsAttr(supplierIDs, "selectSupplierIDs", "companyName" ,"supplierID", model);
    productService.findOneToForm(id, form);
    return "Product/create";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(
    @Validated @ModelAttribute ProductForm form,
    BindingResult result,
    Model model) {
    if (result.hasErrors()) {
      model.addAttribute("errorMessage", "validation error");
      return create(form, model);
    }
    Product product = productService.save(form, 1);
    model.addAttribute("product", product);
    return "Product/detail";
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public String delete(
    @RequestParam(required = true) Long id) {
    productService.delete(id);
    return "redirect:/product/";
  }

}


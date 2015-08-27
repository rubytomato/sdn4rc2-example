package com.example.sdn4rc2.web;

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

import com.example.sdn4rc2.domain.Category;
import com.example.sdn4rc2.domain.CategoryQueryResult;
import com.example.sdn4rc2.service.CategoryService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController extends BaseController {
  final static Logger logger = LoggerFactory.getLogger(CategoryController.class);

  final static int PAGE_SIZE = 5;

  @Autowired
  CategoryService categoryService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model model) {
    return index(1, model);
  }

  @RequestMapping(value = "/{pageNo}", method = RequestMethod.GET)
  public String index(
    @PathVariable Integer pageNo,
    Model model) {
    Iterable<Category> result = categoryService.findAll(pageNo, PAGE_SIZE, "categoryID");
    model.addAttribute("result", result);
    int totalCount = (int)categoryService.count();
    addPageAttr(categoryService.calcPage(totalCount, pageNo, PAGE_SIZE), model);
    return "Category/index";
  }

  @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
  public String detail(
    @PathVariable Long id,
    Model model) {
    Category category = categoryService.findOne(id);
    model.addAttribute("category", category);
    CategoryQueryResult result = categoryService.getCategoryWithProductNum(category.categoryID);
    model.addAttribute("result", result);
    return "Category/detail";
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String create(
    CategoryForm form,
    Model model) {
    return "Category/create";
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public String edit(
    @PathVariable Long id,
    CategoryForm form,
    Model model) {
    categoryService.findOneToForm(id, form);
    return "Category/create";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(
    @Validated @ModelAttribute CategoryForm form,
    BindingResult result,
    Model model) {
    if (result.hasErrors()) {
      model.addAttribute("errorMessage", "validation error");
      return create(form, model);
    }
    Category category = categoryService.save(form, 0);
    model.addAttribute("category", category);
    return "Category/detail";
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public String delete(
    @RequestParam(required = true) Long id) {
    categoryService.delete(id);
    return "redirect:/category/";
  }

}

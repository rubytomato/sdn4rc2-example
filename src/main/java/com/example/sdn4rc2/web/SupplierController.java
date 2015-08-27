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

import com.example.sdn4rc2.domain.Supplier;
import com.example.sdn4rc2.service.SupplierService;

@Controller
@RequestMapping(value = "/supplier")
public class SupplierController extends BaseController {
  final static Logger logger = LoggerFactory.getLogger(SupplierController.class);

  final static int PAGE_SIZE = 10;

  @Autowired
  SupplierService supplierService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model model) {
    return index(1, model);
  }

  @RequestMapping(value = "/{pageNo}", method = RequestMethod.GET)
  public String index(
    @PathVariable Integer pageNo,
    Model model) {
    Iterable<Supplier> result = supplierService.findAll(pageNo, PAGE_SIZE, "supplierID");
    model.addAttribute("result", result);
    int totalCount = (int)supplierService.count();
    addPageAttr(supplierService.calcPage(totalCount, pageNo, PAGE_SIZE), model);
    return "Supplier/index";
  }

  @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
  public String detail(
    @PathVariable Long id,
    Model model) {
    Supplier supplier = supplierService.findOne(id);
    model.addAttribute("supplier", supplier);
    return "Supplier/detail";
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String create(
    SupplierForm form,
    Model model) {
    return "Supplier/create";
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public String edit(
    @PathVariable Long id,
    SupplierForm form,
    Model model) {
    supplierService.findOneToForm(id, form);
    return "Supplier/create";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(
    @Validated @ModelAttribute SupplierForm form,
    BindingResult result,
    Model model) {
    if (result.hasErrors()) {
      model.addAttribute("errorMessage", "validation error");
      return create(form, model);
    }
    Supplier supplier = supplierService.save(form, 0);
    model.addAttribute("supplier", supplier);
    return "Supplier/detail";
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public String delete(
    @RequestParam(required = true) Long id) {
    supplierService.delete(id);
    return "redirect:/supplier/";
  }

}
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

import com.example.sdn4rc2.domain.Customer;
import com.example.sdn4rc2.service.CustomerService;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController {
  final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

  final static int PAGE_SIZE = 10;

  @Autowired
  CustomerService customerService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model model) {
    return index(1, model);
  }

  @RequestMapping(value = "/{pageNo}", method = RequestMethod.GET)
  public String index(
    @PathVariable Integer pageNo,
    Model model) {
    Iterable<Customer> result = customerService.findAll(pageNo, PAGE_SIZE, "customerID");
    model.addAttribute("result", result);
    int totalCount = (int)customerService.count();
    addPageAttr(customerService.calcPage(totalCount, pageNo, PAGE_SIZE), model);
    return "Customer/index";
  }

  @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
  public String detail(
    @PathVariable Long id,
    Model model) {
    Customer customer = customerService.findOne(id);
    model.addAttribute("customer", customer);
    return "Customer/detail";
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String create(
    CustomerForm form,
    Model model) {
    return "Customer/create";
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public String edit(
    @PathVariable Long id,
    CustomerForm form,
    Model model) {
    customerService.findOneToForm(id, form);
    return "Customer/create";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(
    @Validated @ModelAttribute CustomerForm form,
    BindingResult result,
    Model model) {
    if (result.hasErrors()) {
      model.addAttribute("errorMessage", "validation error");
      return create(form, model);
    }
    Customer customer = customerService.save(form, 0);
    model.addAttribute("customer", customer);
    return "Customer/detail";
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public String delete(
    @RequestParam(required = true) Long id) {
    customerService.delete(id);
    return "redirect:/customer/";
  }


}
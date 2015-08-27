package com.example.sdn4rc2.web;

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

import com.example.sdn4rc2.domain.Order;
import com.example.sdn4rc2.domain.Product;
import com.example.sdn4rc2.service.OrderService;
import com.example.sdn4rc2.service.RecommendService;

@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {
  final static Logger logger = LoggerFactory.getLogger(OrderController.class);

  final static int PAGE_SIZE = 50;

  @Autowired
  OrderService orderService;

  @Autowired
  RecommendService recommendService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model model) {
    return index(1, null, model);
  }

  @RequestMapping(value = "/{pageNo}", method = RequestMethod.GET)
  public String index(
    @PathVariable Integer pageNo,
    @RequestParam(required = false, defaultValue = "") String searchName,
    Model model) {
    int totalCount = 0;
    Iterable<Order> result;
    if (StringUtils.isNotEmpty(searchName)) {
      result = orderService.findByShipNameLike(searchName, pageNo, PAGE_SIZE);
      totalCount = (int)orderService.countByShipNameLike(searchName);
    } else {
      result = orderService.findAll(pageNo, PAGE_SIZE, "orderID");
      totalCount = (int)orderService.count();
    }
    model.addAttribute("result", result);
    addPageAttr(orderService.calcPage(totalCount, pageNo, PAGE_SIZE), model);
    model.addAttribute("searchName", searchName);
    return "Order/index";
  }

  @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
  public String detail(
    @PathVariable Long id,
    Model model) {
    Order order = orderService.findOne(id);
    model.addAttribute("order", order);
    Iterable<Product> recommend = recommendService.recommendProducts(order.customerID, order.orderID);
    model.addAttribute("recommend", recommend);
    return "Order/detail";
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String create(
    OrderForm form,
    Model model) {
    return "Order/create";
  }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public String edit(
    @PathVariable Long id,
    OrderForm form,
    Model model) {
    orderService.findOneToForm(id, form);
    return "Order/create";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(
    @Validated @ModelAttribute OrderForm form,
    BindingResult result,
    Model model) {
    if (result.hasErrors()) {
      model.addAttribute("errorMessage", "validation error");
      return create(form, model);
    }
    Order order = orderService.save(form, 0);
    model.addAttribute("order", order);
    return "Order/detail";
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public String delete(
    @RequestParam(required = true) Long id) {
    orderService.delete(id);
    return "redirect:/order/";
  }

}


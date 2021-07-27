package com.mamta.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.mamta.food.entity.Order;
import com.mamta.food.entity.OrderRequest;
import com.mamta.food.service.OrderDishesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicOrderDishesController implements OrderDishesController {
  
  @Autowired
  private OrderDishesService orderDishesService;
  
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    log.debug("Controller layer, Order = {} ", orderRequest);
    return orderDishesService.createOrder(orderRequest);
  }

}

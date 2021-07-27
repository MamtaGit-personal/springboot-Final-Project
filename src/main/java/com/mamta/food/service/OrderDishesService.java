package com.mamta.food.service;

import com.mamta.food.entity.Order;
import com.mamta.food.entity.OrderRequest;

public interface OrderDishesService {

  Order createOrder(OrderRequest orderRequest);
  //Boolean saveDishOrderWithQuantity(Order order, Long orderPK);
}

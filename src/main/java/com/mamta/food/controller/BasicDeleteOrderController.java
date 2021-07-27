package com.mamta.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.mamta.food.entity.DeletedOrder;
import com.mamta.food.service.DeleteOrderService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicDeleteOrderController implements DeleteOrderController {

  @Autowired
  private DeleteOrderService deleteOrderService;
  
  @Override
  public DeletedOrder deleteOrder(Long id) {
    log.debug("Service layer: ID = {}", id);
    return deleteOrderService.deleteOrder(id);
  }

}

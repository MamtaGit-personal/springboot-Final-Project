package com.mamta.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
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
    if(id <=0 ) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
          String.format("Order Id should be Greater than Zero", id));
    }
    log.debug("Service layer: ID = {}", id);
    return deleteOrderService.deleteOrder(id);
  }

}

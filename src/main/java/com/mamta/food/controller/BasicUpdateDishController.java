package com.mamta.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.mamta.food.entity.UpdateDish;
import com.mamta.food.service.UpdateDishService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class BasicUpdateDishController implements UpdateDishController {
  
  @Autowired
  private UpdateDishService updateDishService;
  
  @Override
  public UpdateDish updateDishName(Long dishId, double newPrice) {
    
    UpdateDish updateDish = new UpdateDish(dishId, newPrice, false);
    log.debug("Service layer: The dish id = {}", updateDish.getDishId());
    
    Boolean isSuccessful = updateDishService.updateDishName(updateDish);
    
    updateDish.setIsSuccessful(isSuccessful);
    updateDish.setDishId(dishId);
    updateDish.setNewPrice(newPrice);
    
    return updateDish;
  }

}

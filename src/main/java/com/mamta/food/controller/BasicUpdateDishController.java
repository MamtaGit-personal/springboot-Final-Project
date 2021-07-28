package com.mamta.food.controller;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
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
    if(dishId <=0 ) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
          String.format("Dish Id should be Greater than Zero", dishId));
    }
    else if(newPrice <= 0.0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
          String.format("Price should be positive", newPrice));
    } 
    
    UpdateDish updateDish = new UpdateDish(dishId, newPrice, false);
    log.debug("Service layer: The dish id = {}", updateDish.getDishId());
        
    Boolean isSuccessful = updateDishService.updateDishName(updateDish);
    if(isSuccessful == false) {
      throw new NoSuchElementException(String.format("Dish Id was not found!"));
    }
    else {  
    updateDish.setIsSuccessful(isSuccessful);
    updateDish.setDishId(dishId);
    updateDish.setNewPrice(newPrice);
    
    return updateDish;
    }
  }

}

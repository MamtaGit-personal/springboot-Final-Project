package com.mamta.food.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.mamta.food.entity.Dish;
import com.mamta.food.service.GetDishesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicGetDishesController implements GetDishesController{

  @Autowired
  private GetDishesService getDisheService;
  
  @Override
  public List<Dish> fetchDishes(Long restaurantId) {
    
    if(restaurantId <=0 ) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
          String.format("Dish Id should be Greater than Zero", restaurantId));
    }
    
    log.debug("Service layer: restaurant ID = {}", restaurantId);
    return getDisheService.fetchDishes(restaurantId);
  }

}

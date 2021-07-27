package com.mamta.food.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
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
    log.debug("Service layer: restaurant ID = {}", restaurantId);
    return getDisheService.fetchDishes(restaurantId);
  }

}

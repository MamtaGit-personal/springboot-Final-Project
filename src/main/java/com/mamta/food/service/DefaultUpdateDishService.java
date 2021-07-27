package com.mamta.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mamta.food.dao.UpdateDishDao;
import com.mamta.food.entity.Dish;
import com.mamta.food.entity.DishType;
import com.mamta.food.entity.UpdateDish;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultUpdateDishService implements UpdateDishService {
  
  @Autowired
  private UpdateDishDao updateDishDao;
  
  @Transactional
  @Override
  //public Dish updateDishName(UpdateDish updateDish) {
  public Boolean updateDishName(UpdateDish updateDish) {  
    log.debug("Service layer: old dish name is = {}", updateDish.getDishId());
    
    Boolean isSuccessful = updateDishDao.updateDishName(updateDish);
    log.debug("Service layer: update dishName success = {}", isSuccessful);
    
    /*
     * Dish getDishDetails = updateDishDao.getDishDetailsWithTheNewDishPrice(updateDish.getDishId(),
     * updateDish.getNewPrice());
     */
    return isSuccessful;
    
    
      
  //@formatter:off
   /* return Dish.builder()
      .dishId(updateDish.getDishId())
      .dishName("dishName")
      .price(6.75)
      .dishType(DishType.Vegetarian)
      .description("yummy food")
      .build();*/
  //@formatter:off;
    
  }
  
}

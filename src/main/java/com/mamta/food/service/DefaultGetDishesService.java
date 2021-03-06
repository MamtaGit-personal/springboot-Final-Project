package com.mamta.food.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mamta.food.dao.GetDishesDao;
import com.mamta.food.entity.Dish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class DefaultGetDishesService implements GetDishesService{

  @Autowired
  private GetDishesDao getDishesDao;
  
  @Transactional
  @Override
  public List<Dish> fetchDishes(Long restaurantId) {
    log.debug("Service layer: restaurant ID = {}", restaurantId);
    
    List<Dish> dishes = getDishesDao.fetchDishes(restaurantId);
    
    if(dishes.isEmpty()) {
      throw new NoSuchElementException(String.format("Restuarant Id was not found!"));
    }
    return dishes;
  }

}

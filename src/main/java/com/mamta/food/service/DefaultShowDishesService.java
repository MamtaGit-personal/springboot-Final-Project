package com.mamta.food.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mamta.food.dao.ShowDishesDao;
import com.mamta.food.entity.Dish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DefaultShowDishesService implements ShowDishesService{

  @Autowired
  private ShowDishesDao showDisheDao;
  
  @Transactional
  @Override
  public List<Dish> fetchDishes(Long restaurantId) {
    log.debug("Service layer: restaurant ID = {}", restaurantId);
    return showDisheDao.fetchDishes(restaurantId);
  }

}

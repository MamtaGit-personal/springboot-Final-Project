package com.mamta.food.dao;

import com.mamta.food.entity.Dish;
import com.mamta.food.entity.UpdateDish;

public interface UpdateDishDao {
  Boolean updateDishName(UpdateDish updateDish);
  Dish getDishDetailsWithTheNewDishPrice(Long dishId, double price);
}

package com.mamta.food.dao;

import java.util.List;
import com.mamta.food.entity.Dish;

public interface ShowDishesDao {

  List<Dish> fetchDishes(Long restaurantId);

}

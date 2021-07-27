package com.mamta.food.dao;

import java.util.List;
import com.mamta.food.entity.Dish;

public interface GetDishesDao {

  List<Dish> fetchDishes(Long restaurantId);

}

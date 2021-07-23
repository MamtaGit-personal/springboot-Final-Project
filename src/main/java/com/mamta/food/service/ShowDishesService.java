package com.mamta.food.service;

import java.util.List;
import com.mamta.food.entity.Dish;

public interface ShowDishesService {

  List<Dish> fetchDishes(Long restaurantId);

}

package com.mamta.food.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dish {
  private Long dishId;
  private Long restId;
  private String restaurant;
  private String dishName; 
  private double price; 
  private DishType dishType;  //veg or non-veg
  private String description;
  
}

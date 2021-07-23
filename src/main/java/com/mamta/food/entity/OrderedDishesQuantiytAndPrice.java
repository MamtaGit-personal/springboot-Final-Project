package com.mamta.food.entity;

import lombok.Data;

@Data
public class OrderedDishesQuantiytAndPrice {
  private Long dishId;
  private String dishName;
  private int quantity;
  private double price;

}

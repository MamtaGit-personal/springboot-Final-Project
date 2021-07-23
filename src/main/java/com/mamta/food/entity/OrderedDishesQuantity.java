package com.mamta.food.entity;

import lombok.Data;

@Data
public class OrderedDishesQuantity {
  private String dishName;
  private int quantity;
  
  /*
   * public OrderedDishes() {};
   * 
   * public OrderedDishes( String dishName, int number) { this.dishName = dishName; this.quantity =
   * number; };
   * 
   * public String getDishName() { return dishName; } public void setDishName(String dishName) {
   * this.dishName = dishName; } public int getQuantity() { return quantity; } public void
   * setQuantity(int quantity) { this.quantity = quantity; }
   */
  
}

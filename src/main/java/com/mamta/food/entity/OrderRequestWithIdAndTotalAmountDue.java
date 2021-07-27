package com.mamta.food.entity;

import java.util.Map;
import lombok.Data;

@Data
public class OrderRequestWithIdAndTotalAmountDue {
  private Restaurant restaurant;
  private OrderType orderType;  //PICKUP or DELIVERY
  private Customer customer;
  private Map<String, Integer> dishNameAndQuantity; 
  private Map<Long, Integer> dishIdAndQuantity;
  private double totalAmoutDue;
  
  public OrderRequestWithIdAndTotalAmountDue() {};
  
  public OrderRequestWithIdAndTotalAmountDue(Customer customer, Restaurant restaurant, Map<String, Integer> dishNameAndQuantity) {
    this.customer = customer;
    this.restaurant = restaurant;
    this.dishNameAndQuantity = dishNameAndQuantity;
  };
}

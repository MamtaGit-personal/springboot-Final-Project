package com.mamta.food.entity;

import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequestWithIdAndTotalAmountDue {
  private Customer customer;
  private Restaurant restaurant;
  private OrderType orderType;  //PICKUP or DELIVERY
  private Map<String, Integer> dishNameAndQuantity; 
  private Map<Long, Integer> dishIdAndQuantity;
  private double totalAmoutDue;
  
  /*
   * public void setDishNameAndQuantity(Map<String, Integer> dishNameAndQuantity) { Set<String>
   * dishNames = dishNameAndQuantity.keySet(); for(String dishName: dishNames) {
   * this.dishNameAndQuantity.put(dishName, dishNameAndQuantity.get(dishName)); } }
   */
  
  /*
   * public void setDishNameAndQuantity(String dishName, Integer quantity) {
   * this.dishNameAndQuantity.put(dishName, quantity); }
   * 
   * public void setDishIdAndQuantity(Long dishId, Integer quantity) {
   * this.dishIdAndQuantity.put(dishId, quantity); }
   * /////////////////////////////////////////////////////////////////////
   * 
   * public OrderRequestWithIdAndTotalAmountDue() {};
   * 
   * public OrderRequestWithIdAndTotalAmountDue(Map<String, Integer> dishNameAndQuantity) {
   * Set<String> dishNames = dishNameAndQuantity.keySet(); for(String dishName: dishNames) {
   * this.dishNameAndQuantity.put(dishName, dishNameAndQuantity.get(dishName)); } };
   * 
   * public OrderRequestWithIdAndTotalAmountDue(Customer customer, Restaurant restaurant, OrderType
   * orderType, Map<String, Integer> dishNameAndQuantity) { this.customer = customer;
   * this.restaurant = restaurant; this.orderType = orderType;
   * 
   * Set<String> dishNames = dishNameAndQuantity.keySet(); for(String dishName: dishNames) {
   * this.dishNameAndQuantity.put(dishName, dishNameAndQuantity.get(dishName)); } };
   * 
   * public OrderRequestWithIdAndTotalAmountDue(Customer customer, Restaurant restaurant, OrderType
   * orderType, Map<String, Integer> dishNameAndQuantity, Map<Long, Integer> dishIdAndQuantity) {
   * this.customer = customer; this.restaurant = restaurant; this.orderType = orderType;
   * 
   * Set<String> dishNames = dishNameAndQuantity.keySet(); for(String dishName: dishNames) {
   * this.dishNameAndQuantity.put(dishName, dishNameAndQuantity.get(dishName)); }
   * 
   * Set<Long> dishIds = dishIdAndQuantity.keySet(); for(Long dishId: dishIds) {
   * this.dishIdAndQuantity.put(dishId, dishIdAndQuantity.get(dishId)); } };
   */
}

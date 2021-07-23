package com.mamta.food.entity;

import java.util.List;
import lombok.Data;

@Data
public class CustomerOrderList {
  private Long restId;
  private String restaurantName;
  private String customerName;
  private String customerPhone;
  OrderType orderType;
  private List<OrderedDishesQuantity> dishAndQuantity;
  private double totalAmountDue;
  
}

package com.mamta.food.entity;

import java.sql.Date;
import java.sql.Time;
//import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
  private Long restId;
  private String restaurantName;
  private String customerName;
  private String customerPhone;
  OrderType orderType;
  private String date;
  private String pickupOrDeliveryTime;
  private List<OrderedDishesQuantity> dishAndQuantity;
  private double totalAmountDue;
  
}

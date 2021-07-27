package com.mamta.food.entity;

import java.util.Map;
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
  private Map<String, Integer> dishNameAndQuantity;
  private double totalAmountDue;
  
}

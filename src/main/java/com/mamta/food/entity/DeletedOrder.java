package com.mamta.food.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeletedOrder {
  private Long orderId;
  private Long restaurantId;
  private String customerPhone;
}

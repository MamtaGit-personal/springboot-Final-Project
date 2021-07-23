package com.mamta.food.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
  private Long customerId;
  private String name; 
  private String phone; 
}

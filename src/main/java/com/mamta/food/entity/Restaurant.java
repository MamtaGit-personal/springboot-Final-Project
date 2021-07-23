package com.mamta.food.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Restaurant {
  private Long restId;
  private String name;
  private String address;
  private String restPhone;
}

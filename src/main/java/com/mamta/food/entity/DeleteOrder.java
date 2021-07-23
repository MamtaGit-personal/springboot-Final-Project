package com.mamta.food.entity;

import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteOrder {
  @Positive
  private Long orderId;
}

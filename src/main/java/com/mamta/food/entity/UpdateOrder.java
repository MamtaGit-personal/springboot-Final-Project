package com.mamta.food.entity;

import java.util.Map;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateOrder {
  @Positive
  private Long orderId;
 
  private OrderedDishesQuantity dishIdWithNewQuantity;
}

package com.mamta.food.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order4Test {
  private Long orderPK;
  private Customer customer; 
  private Restaurant restaurant; 
  private OrderType orderType;
  private List<OrderedDishesQuantity> dishAndQuantity;
  private double totalAmountDue;
  
  @JsonIgnore 
  public Long getOrderPK() { 
    return orderPK; 
    }
}

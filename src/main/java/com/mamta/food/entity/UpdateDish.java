package com.mamta.food.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import lombok.Builder;
import lombok.Data;

@Data
public class UpdateDish {
  
  @Positive
  private Long dishId;
  
  @NotNull
  private double newPrice;
  
  private Boolean isSuccessful;
  
 public UpdateDish() {};
 public UpdateDish(Long dishId, double newPrice, Boolean isSuccessful) { 
   this.dishId = dishId;
   this.newPrice = newPrice;
   this.isSuccessful = isSuccessful;
   };
 }

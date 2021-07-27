package com.mamta.food.entity;

import java.util.Map;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest {
  
  @Positive
  private Long restId;
 
  @NotNull
  private OrderType orderType;  //PICKUP or DELIVERY
  
  @NotNull
  @Length(max=12)
  @Pattern(regexp = "[0-9-]*") 
  private String customerPhone;
  
  private Map<@NotNull @Length(max=30) @Pattern(regexp = "[\\w\\s]*") String,
    @Positive Integer> dishNameAndQuantity;
 
  /*
   * public OrderRequest() {};
   * 
   * public OrderRequest(Long restId, OrderType orderType, String customerPhone, Map<String,
   * Integer> dishNameAndQuantity) { this.restId = restId; this.customerPhone = customerPhone;
   * this.orderType = orderType; Set<String> dishNames = dishNameAndQuantity.keySet(); for(String
   * dishName: dishNames) { this.dishNameAndQuantity.put(dishName,
   * dishNameAndQuantity.get(dishName)); } };
   * 
   * public OrderRequest(Map<String, Integer> dishNameAndQuantity) { Set<String> dishNames =
   * dishNameAndQuantity.keySet(); for(String dishName: dishNames) {
   * this.dishNameAndQuantity.put(dishName, dishNameAndQuantity.get(dishName)); } };
   */
  //private Map<String, Integer> dishNameAndQuantity; 
    
}

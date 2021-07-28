package com.mamta.food.entity;

import java.util.Map;
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
  
}

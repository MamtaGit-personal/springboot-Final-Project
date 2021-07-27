package com.mamta.food.dao;

import com.mamta.food.entity.DeletedOrder;

public interface DeleteOrderDao {

  Boolean deleteFromOrderedDishesTableForAGivenOrderId(Long id);

  Boolean deleteFromOrdersTableForAGivenOrderId(Long orderId);
  
  DeletedOrder getDeletedOrderDetailsBeforeDeletion(Long orderId);
}

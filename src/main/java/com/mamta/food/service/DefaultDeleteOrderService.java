package com.mamta.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mamta.food.dao.DeleteOrderDao;
import com.mamta.food.entity.Customer;
import com.mamta.food.entity.DeletedOrder;
import com.mamta.food.entity.Restaurant;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultDeleteOrderService implements DeleteOrderService {

  @Autowired
  private DeleteOrderDao deleteOrderDao;
  
  @Transactional
  @Override
  public DeletedOrder deleteOrder(Long orderId) {
    log.debug("Service layer: ID = {}", orderId);
    
    DeletedOrder deletedOrder = 
        deleteOrderDao.getDeletedOrderDetailsBeforeDeletion(orderId);
    
    Boolean success = deleteOrderDao.deleteFromOrderedDishesTableForAGivenOrderId(orderId);
    success = deleteOrderDao.deleteFromOrdersTableForAGivenOrderId(orderId);
    
    return deletedOrder;
  }
 
}

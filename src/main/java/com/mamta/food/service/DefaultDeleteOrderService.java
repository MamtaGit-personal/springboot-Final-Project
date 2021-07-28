package com.mamta.food.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mamta.food.dao.DeleteOrderDao;
import com.mamta.food.entity.DeletedOrder;
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
    
    DeletedOrder orderToBeDeleted = 
        deleteOrderDao.getDeletedOrderDetailsBeforeDeletion(orderId);
       
    /*
     * First Delete all the rows from the ordered_dishes table where order_id = order_id
     */
    Boolean success = deleteOrderDao.deleteFromOrderedDishesTableForAGivenOrderId(orderId);
    
    /*
     * Then delete the row from the orders table where id = order_id
     */
    success = deleteOrderDao.deleteFromOrdersTableForAGivenOrderId(orderId);
    if(success == false) {
      throw new NoSuchElementException(String.format("Order Id was not found!"));
    }
    return orderToBeDeleted;
  }
  
}

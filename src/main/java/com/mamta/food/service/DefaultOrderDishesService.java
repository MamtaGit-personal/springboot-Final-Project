package com.mamta.food.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mamta.food.dao.OrderDishesDao;
import com.mamta.food.entity.Customer;
import com.mamta.food.entity.Dish;
import com.mamta.food.entity.Order;
import com.mamta.food.entity.OrderRequest;
import com.mamta.food.entity.OrderRequestWithIdAndTotalAmountDue;
import com.mamta.food.entity.Restaurant;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultOrderDishesService implements OrderDishesService{
  
  @Autowired
  private OrderDishesDao orderDishesDao;
  
  private double totalAmount = 0.0;
  
  @Transactional
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    log.debug("Service layer, Order = {} ", orderRequest);
    
    Customer customer = fetchCustomer(orderRequest);
    Restaurant restaurant = fetchRestaurant(orderRequest);
    List<Dish> dish = fetchDishListWithPriceDetails(orderRequest, restaurant.getName());
    
    System.out.println("Service layer, Customer Name is: " + customer.getName());
    System.out.println("Service layer, restaurant Name is: " + restaurant.getName());
    System.out.println("Service layer, dishes ordered are: " + dish.get(0));
    
    OrderRequestWithIdAndTotalAmountDue orderRequestWithIdAndTotalAmountDue = 
        getOrderRequestWithIdAndTotalAmountDue(customer, restaurant, dish, orderRequest);
         
    return orderDishesDao.saveCustomerOrder(orderRequestWithIdAndTotalAmountDue);
  }
  
  /////////////////////////////////////////////////////////////////////////////////////////
  private OrderRequestWithIdAndTotalAmountDue getOrderRequestWithIdAndTotalAmountDue(Customer customer,
      Restaurant restaurant, List<Dish> dish, OrderRequest orderRequest) {
    
    OrderRequestWithIdAndTotalAmountDue orderRequestWithIdAndTotalAmountDue = 
        new OrderRequestWithIdAndTotalAmountDue(customer, restaurant, orderRequest.getDishNameAndQuantity());
     
    double price;
    int quantity = 0; 
    orderRequestWithIdAndTotalAmountDue.setOrderType(orderRequest.getOrderType());
    
    Set<String> dishNames = orderRequest.getDishNameAndQuantity().keySet();
    
    for(int index =0; index < dish.size(); index++) {
     for(String dishName: dishNames) {
        if(dishName == dish.get(index).getDishName()) {
          orderRequestWithIdAndTotalAmountDue.getDishIdAndQuantity()
          .put(dish.get(index).getDishId(), orderRequest.getDishNameAndQuantity().get(dishName));
          
          quantity = orderRequest.getDishNameAndQuantity().get(dishName);
          price = dish.get(index).getPrice();
          price = price*quantity;
          
          totalAmount = totalAmount + price;
          orderRequestWithIdAndTotalAmountDue.setTotalAmoutDue(totalAmount);
        }
      }
    }
    
    return orderRequestWithIdAndTotalAmountDue;
  }
 
  //////////////////////////////////////////////////////////////////////////
  private List<Dish> fetchDishListWithPriceDetails(OrderRequest orderRequest, String restaurant) {
    return orderDishesDao.getDishListWithPriceDetails(orderRequest, restaurant);
  }

  //////////////////////////////////////////////////////////////////////////
  protected Restaurant fetchRestaurant(OrderRequest orderRequest) {
    
    return orderDishesDao.getRestaurant(orderRequest.getRestId())
        .orElseThrow(() -> new NoSuchElementException("Restaurant with ID = " 
        + orderRequest.getRestId()+ " was NOT found"));
  }
  
  /////////////////////////////////////////////////////////////////////////
  protected Customer fetchCustomer(OrderRequest orderRequest) {
    
    return orderDishesDao.getCustomer(orderRequest.getCustomerPhone())
        .orElseThrow(() -> new NoSuchElementException("Customer with ID = " 
        + orderRequest.getCustomerPhone()+ " was NOT found"));
  }
  
}
